# Benchmarx Scalability Test Suite - Standalone & Linux Execution Guide

This guide describes how to build, deploy, configure, and execute the **Benchmarx FamiliesToPersons Scalability Benchmark** outside of Eclipse as a standalone Fat JAR or distribution bundle (e.g., on a headless Linux server or external workstation).

---

## Table of Contents

1. [Architecture & Available Tools Overview](#1-architecture--available-tools-overview)
   - [Active Tools](#active-tools)
   - [Excluded / Pending Tools](#excluded--pending-tools)
   - [All Tools Summary Table](#all-tools-summary-table)
   - [Two-Tier JVM Execution Model](#two-tier-jvm-execution-model)
2. [Prerequisites](#2-prerequisites)
3. [Building the Standalone Artifacts (Windows)](#3-building-the-standalone-artifacts-windows)
4. [Deployment to Linux Server](#4-deployment-to-linux-server)
5. [Configuring Services (Neo4j for eNeo)](#5-configuring-services-neo4j-for-eneo)
6. [Running the Scalability Tests](#6-running-the-scalability-tests)
   - [Method A: Executing the Fat JAR (Recommended)](#method-a-executing-the-fat-jar-recommended)
   - [Filtering & Excluding Tools (`-T` / `-E`)](#filtering--excluding-tools--t---e)
   - [Rapid Diagnostics: Running Single Test Cases Directly](#rapid-diagnostics-running-single-test-cases-directly)
   - [Method B: Executing the Standalone Distribution Bundle](#method-b-executing-the-standalone-distribution-bundle)
7. [CLI Options & Configuration Reference](#7-cli-options--configuration-reference)
8. [Available Test Suites & Test Classes](#8-available-test-suites--test-classes)
9. [Interpreting Results & Logs](#9-interpreting-results--logs)
10. [Troubleshooting & Gotchas](#10-troubleshooting--gotchas)

---

## 1. Architecture & Available Tools Overview

### Active Tools

The active tools configured in [`FamiliesToPersonsTestCase.java`](file:///C:/ES/Dev/workspaces/benchmarx/examples/familiestopersons/BenchmarxFamiliesToPersons/src/org/benchmarx/examples/familiestopersons/testsuite/FamiliesToPersonsTestCase.java) are:

1. **BXtend Synch (`BXtendFamiliesToPersons`)**
   - **Engine**: BXtend bidirectional transformation engine.
   - **Filter Token**: `bxtend`
   - **External Dependencies**: None (pure Java/Xtend).
   - **Execution Characteristic**: Executes very quickly (e.g. ~1–4 ms for scale 5–55).

2. **BXtend Wrapped with Merge (`WrapperOverBXtendWithMerge`)**
   - **Engine**: BXtend transformation engine wrapped with 3-way merge conflict resolution.
   - **Filter Token**: `bxtend` or `merge`
   - **External Dependencies**: None.
   - **Execution Characteristic**: Very fast execution with automatic conflict merging.

3. **IBeX-TGG-Integrate (`IBeXTGGIntegrateFamiliesToPersons`)**
   - **Engine**: eMoflon::IBeX Triple Graph Grammars (TGG) integration engine powered by the HiPE incremental pattern matcher and Akka actor runtime.
   - **Filter Token**: `ibex`
   - **External Dependencies**: Self-contained. Model files (`FamiliesToPersonsIBeXTGG.tgg.xmi` and `hipe-network.xmi`) are automatically extracted to a temporary directory at runtime, and Akka `reference.conf` settings are pre-merged into the Fat JAR.
   - **Execution Characteristic**: Scalable rule matching and propagating delta synchronization.

4. **eNeo (`ENeoFamiliesToPersons`)** - *Configured to run last*
   - **Engine**: eMoflon::Neo graph transformation engine backed by Neo4j graph database and Google OR-Tools integer linear programming (ILP) solver.
   - **Filter Token**: `eneo`
   - **External Dependencies**: **Requires Neo4j database** actively listening on `bolt://localhost:7687` with credentials `neo4j/test`. Native OR-Tools Linux libraries (`ortools-linux-x86-64-9.12.4544.jar`) and Eclipse resource plugins are embedded in the Fat JAR.
   - **Order Constraint**: Must run **last** among tools because it manages database state and container cleanup.

### Excluded / Pending Tools

5. **BCerT (`BCerTFamiliesToPersons`)**
   - **Engine**: B-method Certification Tool based on B4MSecure and ProB interpreter for bidirectional model transformations.
   - **Filter Token**: `bcert`
   - **Status**: *Temporarily commented out* pending colleague PR integration.
   - **Dependencies**: Embedded `fr.lig.vasco.banim.dependencies-1.0.0-all.jar`.

### Other Legacy / Benchmark Tools (Disabled in `FamiliesToPersonsTestCase`)

| Tool | Implementation Class | Description / Reason Excluded |
| :--- | :--- | :--- |
| **BiGUL** | `BiGULFamiliesToPersons` | Bidirectional Generic Update Language (Haskell bridge; requires external setup). |
| **FunnyQT** | `FunnyQTFamiliesToPerson` | Clojure-based transformation library (excluded due to Closure dependency). |
| **NMF** | `NMFFamiliesToPersonsIncremental` | .NET Modeling Framework (requires Mono / NMF bridge). |
| **JTL** | `JTLFamiliesToPersons` | Janus Transformation Language (excluded due to Emftext issues). |
| **eMoflon** | `EMoflonFamiliesToPersons` | Classic Democles/eMoflon pattern matcher. |
| **MediniQVT** | `MediniQVTFamiliesToPersons` | QVT Relations engine (requires Medini setup). |

---

### All Tools Summary Table

| Tool Name (in Runner) | CLI Filter Match | Implementation Class | External Services | Status |
| :--- | :--- | :--- | :--- | :--- |
| `BXtend Synch` | `bxtend` | `BXtendFamiliesToPersons` | None | **Active** |
| `BXtend Wrapped with Merge` | `bxtend` / `merge` | `WrapperOverBXtendWithMerge` | None | **Active** |
| `IBeX-TGG-Integrate` | `ibex` | `IBeXTGGIntegrateFamiliesToPersons` | None (self-contained) | **Active** |
| `eNeo` | `eneo` | `ENeoFamiliesToPersons` | Neo4j on port `7687` (`neo4j/test`) | **Active (runs last)** |
| `BCerT` | `bcert` | `BCerTFamiliesToPersons` | `fr.lig.vasco.banim` lib | *Pending PR* |

---

### Two-Tier JVM Execution Model

1. **Master Runner (`ScalabilityMain`)**: Initializes EMF packages (`FamiliesPackage`, `PersonsPackage`), configures benchmark parameters, parses CLI flags (including tool include/exclude filters), and launches JUnit.
2. **Child Process Isolation (`ScalabilityTestRunner`)**: For each repetition and scale factor, the runner forks an independent child JVM process:
   ```bash
   java <jvmArgs> -cp <classpath> <testcaseClass> <args>
   ```
   This ensures:
   - Clean-slate heap and garbage collection state per measurement.
   - Strict timeout enforcement per run (default: 60 seconds).
   - Independent stdout/stderr capture with timestamped log files.

---

## 2. Prerequisites

### Build Environment (Windows Developer Machine)
- **JDK 21 or newer** (Java 21 and Java 25 supported). Ensure `JAVA_HOME` is set or `jar.exe` is in `PATH`.
- **Eclipse Modeling Tools** installed with required PDE plugins (default expected location: `C:\ES\Dev\eclipse\eclipse-241026\plugins`).
- **Eclipse Projects Compiled**: Workspace projects must be compiled (i.e. `.class` files present in each project's `bin/` directory via Eclipse automatic build).

### Remote Execution Environment (Linux Server)
- **Linux 64-bit (x86_64)** (Ubuntu 20.04+, Debian 11+, RHEL 8+, etc.).
- **Java 21 or newer** (`openjdk-21-jdk` or Oracle JDK 21+). Check with:
  ```bash
  java -version
  ```
- **Neo4j Database (v4.4.x)**: Required for the **eNeo** tool. Can be run locally via Docker (see [Section 5](#5-configuring-services-neo4j-for-eneo)).
- Minimum Recommended RAM: **32 GB** (default child JVM heap is 32G, overridable with `-m <size>`).

---

## 3. Building the Standalone Artifacts (Windows)

The repository root includes automated build scripts:
- [`build-fat-jar.bat`](file:///C:/ES/Dev/workspaces/benchmarx/build-fat-jar.bat): One-click CMD wrapper.
- [`build-fat-jar.ps1`](file:///C:/ES/Dev/workspaces/benchmarx/build-fat-jar.ps1): Automated PowerShell build engine.

### Option 1: Run the Batch File (Fastest)
Double-click `build-fat-jar.bat` or run from `cmd.exe`:
```cmd
cd C:\ES\Dev\workspaces\benchmarx
build-fat-jar.bat
```

### Option 2: Run via PowerShell
```powershell
cd C:\ES\Dev\workspaces\benchmarx
powershell -ExecutionPolicy Bypass -File build-fat-jar.ps1
```

The build script will:
1. Verify OR-Tools Linux native libraries.
2. Copy all compiled project classes from `core/`, `metamodels/`, and `examples/`.
3. Unpack Eclipse dependencies and strip stale digital signatures (`META-INF/*.SF`, `*.RSA`).
4. **Merge Akka `reference.conf` files** from `akka-actor` and `akka-stream`.
5. Package everything into a self-contained Fat JAR: `benchmarx-scalability.jar` (~192 MB).

---

## 4. Deployment to Linux Server

Transfer `benchmarx-scalability.jar` to your remote Linux server (e.g., `ikarus`):

```bash
# Example via SCP:
scp -P 50001 -i ~/.ssh/ikarus benchmarx-scalability.jar lfritsche@130.83.199.30:~
```

---

## 5. Configuring Services (Neo4j for eNeo)

The **eNeo** engine requires an active Neo4j instance reachable at `bolt://localhost:7687` with credentials `neo4j/test`.

### Starting Neo4j via Docker on Linux:
```bash
docker run -d   --name neo4j-benchmarx   -p 7687:7687 -p 7474:7474   -e NEO4J_AUTH=neo4j/test   neo4j:4.4
```

### Verifying Neo4j Connectivity:
```bash
# Check container status
docker ps

# Verify port 7687 is open and accepting connections
nc -zv localhost 7687
# or:
curl -I http://localhost:7474
```

> **Tip**: If you exclude eNeo (e.g. via `-T ibex` or `-E eneo`), Neo4j is **not** required.

---

## 6. Running the Scalability Tests

### Method A: Executing the Fat JAR (Recommended)

Run directly with `java -jar benchmarx-scalability.jar`:

#### 1. Display Help & Usage
```bash
java -jar benchmarx-scalability.jar --help
```

#### 2. Run All Active Tools (Default Suite)
Runs `FamiliesToPersonsScalabilityTestSuite` with default settings (10 repetitions, 60s timeout, 32GB child heap, results saved to `./results`):
```bash
java -jar benchmarx-scalability.jar
```

---

### Filtering & Excluding Tools (`-T` / `-E`)

You can dynamically select or exclude tools without modifying any code or rebuilding the JAR:

#### A. Exclude BXtend (Run Only IBeX and eNeo)
```bash
java -jar benchmarx-scalability.jar -E bxtend -r ./results -n 10 -t 90 -m 64G
```

#### B. Run ONLY IBeX
```bash
java -jar benchmarx-scalability.jar -T ibex -r ./results -n 10 -t 90 -m 64G
```

#### C. Run ONLY eNeo (Requires Neo4j on Port 7687)
```bash
java -jar benchmarx-scalability.jar -T eneo -r ./results -n 10 -t 90 -m 64G
```

#### D. Run Specific Combination (e.g. IBeX and eNeo explicitly)
```bash
java -jar benchmarx-scalability.jar -T ibex,eneo -r ./results -n 10 -t 90 -m 64G
```

#### E. Run Consistent Delta Conflict Synchronization for IBeX Only
```bash
java -jar benchmarx-scalability.jar -s ScalabilityConstDeltaCFCSync -T ibex -r ./results -n 5
```

---

### Rapid Diagnostics: Running Single Test Cases Directly

If you want to quickly test or debug a specific tool and scale factor **without running a full test suite**, invoke the testcase classes directly via the classpath:

```bash
# Test IBeX on Incremental Backward Transformation (scale 5):
java -cp benchmarx-scalability.jar org.benchmarx.examples.familiestopersons.scalability.cases.IncrTestsBwdTestcase "IBeX-TGG-Integrate" 5

# Test IBeX on Constant Delta Conflict Synchronization (scale 5):
java -cp benchmarx-scalability.jar org.benchmarx.examples.familiestopersons.scalability.cases.ConstDeltaCFCSyncTestcase "IBeX-TGG-Integrate" 5

# Test eNeo on Incremental Backward Transformation (scale 5, requires Neo4j):
java -cp benchmarx-scalability.jar org.benchmarx.examples.familiestopersons.scalability.cases.IncrTestsBwdTestcase "eNeo" 5

# Test BXtend Synch (scale 5):
java -cp benchmarx-scalability.jar org.benchmarx.examples.familiestopersons.scalability.cases.IncrTestsBwdTestcase "BXtend Synch" 5
```

Each direct invocation runs a single pass, prints the elapsed resolution time in seconds, and exits with code 0 on success.

---

### Running in Background / Surviving SSH Logout

If you start the benchmark directly in an interactive SSH terminal and disconnect or close the terminal, Linux will send a `SIGHUP` signal and kill the benchmark process.

To keep the benchmark running after logging out:

#### Method 1: Using `tmux` (Recommended)
`tmux` keeps your shell session alive in the background and lets you re-attach anytime:
```bash
# 1. Start a new session:
tmux new -s bench

# 2. Launch the benchmark inside tmux:
java -jar benchmarx-scalability.jar -r ./results -n 10 -t 90 -m 64G -T eneo,ibex

# 3. Detach from the session:
# Press Ctrl+B, then press D

# 4. Safe to disconnect SSH!
exit
```
When you reconnect later, reattach to see the live output:
```bash
tmux attach -t bench
```

#### Method 2: Using `nohup`
Runs completely detached and redirects all output to `benchmark.log`:
```bash
nohup java -jar benchmarx-scalability.jar -r ./results -n 10 -t 90 -m 64G -T eneo,ibex > benchmark.log 2>&1 &
```
* **Check live progress**: `tail -f benchmark.log`
* **Check if running**: `ps aux | grep benchmarx-scalability`
* **Stop/Kill**: `pkill -f benchmarx-scalability`

---

### Method B: Executing the Standalone Distribution Bundle

The companion `benchmarx-scalability-dist.zip` package contains unmerged jars in `lib/`, native model files in `model/`, and pre-configured launch scripts.

#### 1. Unzip the Package
```bash
unzip benchmarx-scalability-dist.zip -d benchmarx-scalability
cd benchmarx-scalability
chmod +x run.sh
```

#### 2. Execute via `run.sh`
```bash
# Run default suite
./run.sh

# Run specific suite with options and tool exclusion
./run.sh -s ScalabilityIncrTestsBwd -E bxtend -r ./results -n 5 -m 16G
```

---

## 7. CLI Options & Configuration Reference

### Command-Line Arguments

| Flag | Long Flag | Description | Default Value |
| :--- | :--- | :--- | :--- |
| `-s` | `--suite <name>` | Test suite or test class name to execute | `FamiliesToPersonsScalabilityTestSuite` |
| `-r` | `--results <path>` | Directory path for benchmark CSV/TXT outputs | `./results` |
| `-n` | `--repeat <num>` | Number of repetitions per scale factor | `10` |
| `-t` | `--timeout <sec>` | Maximum process execution timeout in seconds | `60` |
| `-m` | `--xmx <size>` | Max heap memory for spawned child processes (e.g. `16G`, `32G`, `64G`) | `-Xmx32G` |
| `-T` | `--tools <names>` | Comma-separated tool filter to include (e.g., `ibex`, `eneo`, `bxtend`) | All active tools |
| `-E` | `--exclude-tools <names>` | Comma-separated tool filter to exclude (e.g., `bxtend`) | None |
| `-h` | `--help` | Display CLI usage help and exit | - |

### Equivalent Java System Properties (`-D` flags)

You can also configure the runner via standard JVM system properties:

```bash
java -Dscalability.resultfolder=/custom/results      -Dscalability.repeat=5      -Dscalability.timeout=90      -Dscalability.xmx=-Xmx64G      -Dscalability.tools.exclude=bxtend      -jar benchmarx-scalability.jar -s ScalabilityIncrTestsBwd
```

| System Property | Description | Default |
| :--- | :--- | :--- |
| `scalability.resultfolder` | Output folder for benchmark data | `./results` |
| `scalability.repeat` | Repetition count per scale factor | `10` |
| `scalability.timeout` | Process timeout in seconds | `60` |
| `scalability.xmx` | Max heap memory for child runs | `-Xmx32G` |
| `scalability.tools.include` | Comma-separated tool substring filter to include | Unset (all) |
| `scalability.tools.exclude` | Comma-separated tool substring filter to exclude | Unset (none) |
| `scalability.jvmargs` | Custom JVM flags for child runs (e.g. `-Xmx16G -XX:+UseG1GC`) | Derived from `-m` |
| `scalability.classpath` | Custom classpath override for child processes | Derived from current JAR |

---

## 8. Available Test Suites & Test Classes

The `--suite` option accepts either simple class names or fully qualified names:

### 1. Composite Test Suites
- **`FamiliesToPersonsScalabilityTestSuite`** *(Default)*:
  Runs the composite benchmark suite covering backward incremental sync and consistency/conflict synchronization.

### 2. Individual Transformation Tests
- **`ScalabilityIncrTestsBwd`**: Incremental backward transformation from Person Register to Family Register.
- **`ScalabilityIncrTestsFwd`**: Incremental forward transformation from Family Register to Person Register.
- **`ScalabilityBatchTestsBwd`**: Batch backward transformation.
- **`ScalabilityBatchTestsFwd`**: Batch forward transformation.

### 3. Synchronization & Conflict Tests
- **`ScalabilityConstModelCSync`**: Consistent model consistency synchronization.
- **`ScalabilityConstModelCFCSync`**: Consistent model conflict synchronization.
- **`ScalabilityConstDeltaCSync`**: Consistent delta consistency synchronization.
- **`ScalabilityConstDeltaCFCSync`**: Consistent delta conflict synchronization.

---

## 9. Interpreting Results & Logs

### Benchmark Output Files (`./results/` or `-r <dir>`)
After a benchmark run finishes, results are saved into the configured directory:
- Format: Text and CSV files formatted per test configuration and tool.
- Content: Execution durations, scale factors, repetition measurements, and median/average timings.

### Error Logs (`./log/`)
For each spawned child process:
- A timestamped log file is created under `./log/log_<testcase>_<timestamp>.txt` (or `<results>/log/`).
- Empty log files are automatically deleted upon successful completion.
- If a child run fails or times out, the full `stderr` and log contents are printed directly to the console along with the non-zero exit code.

---

## 10. Troubleshooting & Gotchas

### 1. eNeo / Neo4j: `Connection refused`
- **Symptom**: `org.neo4j.driver.exceptions.ServiceUnavailableException: Connection to localhost:7687 refused`.
- **Cause**: Neo4j is not running on port 7687 or Bolt authentication failed.
- **Solution**: Ensure the Neo4j container is running:
  ```bash
  docker run -d --name neo4j-benchmarx -p 7687:7687 -e NEO4J_AUTH=neo4j/test neo4j:4.4
  ```

### 2. eNeo: `NoClassDefFoundError: org/eclipse/core/resources/IProject`
- **Symptom**: Guice injector crashes during `EMSLStandaloneSetupGenerated`.
- **Cause**: Missing `org.eclipse.core.resources` plugin in classpath.
- **Solution**: Embedded directly into `benchmarx-scalability.jar`. Rebuild the Fat JAR using `build-fat-jar.ps1`.

### 3. IBeX: Akka `reference.conf` or Model File Lookup
- **Symptom**: `No configuration setting found for key 'akka.version'` or `FileNotFoundException: ... FamiliesToPersonsIBeXTGG.tgg.xmi`.
- **Solution**: Pre-resolved in the Fat JAR. The build automatically merges `reference.conf` files, and `INTEGRATE_App` extracts required model and HiPE network files to temp storage dynamically.

### 4. Google OR-Tools: `UnsatisfiedLinkError: libjniortools.so`
- **Symptom**: `java.lang.UnsatisfiedLinkError: ... libjniortools.so: cannot open shared object file`.
- **Cause**: Missing Linux native binaries for OR-Tools.
- **Solution**: Embedded automatically via `ortools-linux-x86-64-9.12.4544.jar` in `benchmarx-scalability.jar`.

### 5. Child Process Memory: `Cannot allocate memory` / `OutOfMemoryError`
- **Symptom**: Host OS kills child process or JVM fails to start with `-Xmx64G`.
- **Cause**: Server has less than requested available RAM.
- **Solution**: Adjust memory allocation via `-m`:
  ```bash
  java -jar benchmarx-scalability.jar -m 32G
  # or:
  java -jar benchmarx-scalability.jar -m 16G
  ```

### 6. Process Timeout: `TIMED OUT after <sec>s`
- **Symptom**: A large scale factor takes longer than the timeout limit.
- **Solution**: Increase timeout using `-t`:
  ```bash
  java -jar benchmarx-scalability.jar -t 180
  ```
