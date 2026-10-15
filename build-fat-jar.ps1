<#
.SYNOPSIS
    Builds a standalone executable Fat JAR for Benchmarx FamiliesToPersons scalability tests.

.DESCRIPTION
    This script collects all compiled classes, resources, local dependencies,
    and Eclipse PDE plugin libraries, strips digital signatures to prevent
    SecurityExceptions, and packages everything into an executable Fat JAR.

.PARAMETER OutputFile
    Path where the output Fat JAR will be saved. Default: .\benchmarx-scalability.jar

.PARAMETER EclipsePluginsDir
    Path to the Eclipse plugins folder. Default: C:\ES\Dev\eclipse\eclipse-241026\plugins

.PARAMETER BenchmarxRoot
    Root directory of the benchmarx repository. Default: current directory / script parent.

.EXAMPLE
    .\build-fat-jar.ps1
    .\build-fat-jar.ps1 -OutputFile C:\benchmarks\benchmarx-fat.jar
#>

[CmdletBinding()]
param(
    [string]$OutputFile = "",
    [string]$EclipsePluginsDir = "",
    [string]$BenchmarxRoot = ""
)

$ErrorActionPreference = "Stop"

# ---------------------------------------------------------------------------
# 1. Resolve Paths & Tools
# ---------------------------------------------------------------------------

# Resolve Benchmarx root
if (-not $BenchmarxRoot) {
    if (Test-Path "$PSScriptRoot\examples\familiestopersons") {
        $BenchmarxRoot = $PSScriptRoot
    } elseif (Test-Path "C:\ES\Dev\workspaces\benchmarx\examples\familiestopersons") {
        $BenchmarxRoot = "C:\ES\Dev\workspaces\benchmarx"
    } else {
        $BenchmarxRoot = (Get-Location).Path
    }
}
$BenchmarxRoot = (Resolve-Path $BenchmarxRoot).Path

# Resolve Output File
if (-not $OutputFile) {
    $OutputFile = Join-Path $BenchmarxRoot "benchmarx-scalability.jar"
} else {
    $OutputFile = [System.IO.Path]::GetFullPath($OutputFile)
}

# Resolve Eclipse Plugins Dir
if (-not $EclipsePluginsDir) {
    if ($env:ECLIPSE_PLUGINS_DIR -and (Test-Path $env:ECLIPSE_PLUGINS_DIR)) {
        $EclipsePluginsDir = $env:ECLIPSE_PLUGINS_DIR
    } elseif (Test-Path "C:\ES\Dev\eclipse\eclipse-241026\plugins") {
        $EclipsePluginsDir = "C:\ES\Dev\eclipse\eclipse-241026\plugins"
    } else {
        Write-Error "Eclipse plugins directory not found! Specify -EclipsePluginsDir <path>."
        exit 1
    }
}
$EclipsePluginsDir = (Resolve-Path $EclipsePluginsDir).Path

# Resolve JDK tools
$jarExe = $null
if ($env:JAVA_HOME -and (Test-Path "$env:JAVA_HOME\bin\jar.exe")) {
    $jarExe = "$env:JAVA_HOME\bin\jar.exe"
} elseif (Test-Path "C:\Program Files\Java\jdk-21.0.2\bin\jar.exe") {
    $jarExe = "C:\Program Files\Java\jdk-21.0.2\bin\jar.exe"
} else {
    $command = Get-Command jar -ErrorAction SilentlyContinue
    if ($command) {
        $jarExe = $command.Source
    }
}

if (-not $jarExe -or -not (Test-Path $jarExe)) {
    Write-Error "JDK 'jar.exe' not found! Please set JAVA_HOME or ensure jar.exe is in PATH."
    exit 1
}

Write-Host "==========================================================" -ForegroundColor Cyan
Write-Host "   Benchmarx Scalability Fat JAR Builder                  " -ForegroundColor Cyan
Write-Host "==========================================================" -ForegroundColor Cyan
Write-Host "Benchmarx Root:     $BenchmarxRoot"
Write-Host "Eclipse Plugins:    $EclipsePluginsDir"
Write-Host "Jar Tool:           $jarExe"
Write-Host "Output Target:      $OutputFile"
Write-Host "=========================================================="

# ---------------------------------------------------------------------------
# 2. Check / Download OR-Tools Linux native jar (for Linux execution)
# ---------------------------------------------------------------------------
$eneoLibDir = Join-Path $BenchmarxRoot "examples\familiestopersons\BenchmarxFamiliesToPersons\lib\eneo"
$linuxOrtoolsJar = Join-Path $eneoLibDir "ortools-linux-x86-64-9.12.4544.jar"

if (-not (Test-Path $linuxOrtoolsJar)) {
    Write-Host "`n[1/6] Downloading Google OR-Tools Linux native JAR..." -ForegroundColor Yellow
    if (-not (Test-Path $eneoLibDir)) {
        New-Item -ItemType Directory -Path $eneoLibDir -Force | Out-Null
    }
    $url = "https://repo1.maven.org/maven2/com/google/ortools/ortools-linux-x86-64/9.12.4544/ortools-linux-x86-64-9.12.4544.jar"
    Invoke-WebRequest -Uri $url -OutFile $linuxOrtoolsJar
    Write-Host "Downloaded: ortools-linux-x86-64-9.12.4544.jar ($([math]::Round((Get-Item $linuxOrtoolsJar).Length / 1MB, 2)) MB)" -ForegroundColor Green
} else {
    Write-Host "`n[1/6] OR-Tools Linux native JAR is present." -ForegroundColor Green
}

# ---------------------------------------------------------------------------
# 3. Setup Staging Directory
# ---------------------------------------------------------------------------
$buildStagingDir = Join-Path $BenchmarxRoot "build\fatjar_staging"
Write-Host "`n[2/6] Preparing staging directory: $buildStagingDir" -ForegroundColor Yellow

if (Test-Path $buildStagingDir) {
    Remove-Item -Recurse -Force $buildStagingDir
}
New-Item -ItemType Directory -Path $buildStagingDir -Force | Out-Null

# ---------------------------------------------------------------------------
# 4. Copy Compiled Project Classes
# ---------------------------------------------------------------------------
Write-Host "`n[3/6] Copying compiled project classes and resources..." -ForegroundColor Yellow

$f2p = "$BenchmarxRoot\examples\familiestopersons\BenchmarxFamiliesToPersons"
$ibexDir = "$BenchmarxRoot\examples\familiestopersons\implementationArtefacts\ibex-integrate"
$eneoDir = "$BenchmarxRoot\examples\familiestopersons\implementationArtefacts\eNeo\ENeoFamiliesToPersons"
$bcertDir = "$BenchmarxRoot\examples\familiestopersons\implementationArtefacts\bcert\pivot"

$projectDirs = @(
    "$BenchmarxRoot\core\Benchmarx\bin",
    "$BenchmarxRoot\examples\familiestopersons\metamodels\Families\bin",
    "$BenchmarxRoot\examples\familiestopersons\metamodels\Persons\bin",
    "$f2p\bin",
    "$ibexDir\FamiliesSmartEMF\bin",
    "$ibexDir\PersonsSmartEMF\bin",
    "$ibexDir\FamiliesToPersonsIBeXTGG\bin",
    "$eneoDir\bin",
    "$bcertDir\bin"
)

foreach ($pDir in $projectDirs) {
    if (Test-Path $pDir) {
        $parentName = Split-Path (Split-Path $pDir -Parent) -Leaf
        Write-Host "  + Copying $parentName"
        Copy-Item -Path "$pDir\*" -Destination $buildStagingDir -Recurse -Force
    } else {
        Write-Warning "Directory not found (may need Eclipse build): $pDir"
    }
}

# Copy model files required by IBeX
$tggModelDir = Join-Path $ibexDir "FamiliesToPersonsIBeXTGG\model"
if (Test-Path $tggModelDir) {
    $destModelDir = Join-Path $buildStagingDir "model"
    New-Item -ItemType Directory -Path $destModelDir -Force | Out-Null
    Copy-Item -Path "$tggModelDir\*" -Destination $destModelDir -Recurse -Force
}

# ---------------------------------------------------------------------------
# 5. Collect and Unpack Dependency JARs
# ---------------------------------------------------------------------------
Write-Host "`n[4/6] Collecting dependency JARs..." -ForegroundColor Yellow

$jars = [System.Collections.Generic.List[string]]::new()

# Local libs
$jars.Add("$f2p\lib\emf-standalone-2.23.0.jar")
$jars.Add("$f2p\lib\BXtendFamilies2Persons.jar")
$jars.Add("$f2p\lib\UBTBxTendFamilies2Persons.jar")

# ENeo libs
Get-ChildItem -Path "$f2p\lib\eneo" -Filter "*.jar" | ForEach-Object { $jars.Add($_.FullName) }

# IBeX libs
Get-ChildItem -Path "$ibexDir\FamiliesToPersonsIBeXTGG\lib" -Filter "*.jar" | ForEach-Object { $jars.Add($_.FullName) }

# BCerT libs & plugins (Meeduse / B4MSecure)
$bcertDepsJar = "$bcertDir\lib\fr.lig.vasco.banim.dependencies-1.0.0-all.jar"
if (Test-Path $bcertDepsJar) {
    $jars.Add($bcertDepsJar)
}
Get-ChildItem -Path $EclipsePluginsDir -Filter "fr.lig.vasco.banim_*.jar" | Where-Object { $_.Name -notmatch "source" -and $_.Name -notmatch "ui" } | Sort-Object Name | Select-Object -Last 1 | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "fr.lig.vasco.becore_*.jar" | Where-Object { $_.Name -notmatch "source" } | Sort-Object Name | Select-Object -Last 1 | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "fr.liglab.vasco.b4msecure.b_*.jar" | Where-Object { $_.Name -notmatch "source" -and $_.Name -notmatch "editor" -and $_.Name -notmatch "edit" -and $_.Name -notmatch "text" } | Sort-Object Name | Select-Object -Last 1 | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "fr.liglab.vasco.b4msecure.ctm.trace_*.jar" | Where-Object { $_.Name -notmatch "source" -and $_.Name -notmatch "editor" -and $_.Name -notmatch "edit" } | Sort-Object Name | Select-Object -Last 1 | ForEach-Object { $jars.Add($_.FullName) }

# Specific Eclipse plugins (picked carefully to avoid conflicts)
$jars.Add("$EclipsePluginsDir\org.eclipse.core.resources_3.23.100.v20251106-1705.jar")
$jars.Add("$EclipsePluginsDir\org.eclipse.text_3.14.500.v20251103-0730.jar")
$jars.Add("$EclipsePluginsDir\com.google.guava_33.7.1.jre.jar")
$jars.Add("$EclipsePluginsDir\com.google.guava.failureaccess_1.0.3.jar")
$jars.Add("$EclipsePluginsDir\org.eclipse.xtext.xbase.lib_2.41.0.v20251124-0739.jar")
$jars.Add("$EclipsePluginsDir\org.antlr.runtime_3.2.0.v20230929-1400.jar")
$jars.Add("$EclipsePluginsDir\org.emoflon.neo.neo4j.adapter_1.0.0.202510231833\lib\neo4j-java-driver-4.1.1.jar")
$jars.Add("$EclipsePluginsDir\org.emoflon.neo.neo4j.adapter_1.0.0.202510231833\lib\reactive-streams-1.0.3.jar")
$jars.Add("$EclipsePluginsDir\org.emoflon.neo.engine.modules_1.0.0.202510231833\lib\MOEAFramework-2.13.jar")
$jars.Add("$EclipsePluginsDir\org.emoflon.neo.engine.modules_1.0.0.202510231833\lib\protobuf-java-2.5.0.jar")
$jars.Add("$EclipsePluginsDir\org.emoflon.neo.engine.modules_1.0.0.202510231833\lib\com.sun.jna_5.13.0.jar")
$jars.Add("$EclipsePluginsDir\org.emoflon.neo.emsl_1.0.0.202510231833\lib\javabdd-1.0b2.jar")

Get-ChildItem -Path $EclipsePluginsDir -Filter "*org.emoflon.neo*.jar" | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*org.eclipse.emf.mwe*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*inject*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*aop*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*org.eclipse.core*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*org.eclipse.text*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*org.eclipse.equinox.*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*org.eclipse.osgi*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*org.eclipse.xtext*.jar" | Where-Object { $_.Name -notmatch "2\.10\." -and $_.Name -notmatch "source" -and $_.Name -notmatch "xbase\.lib" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*org.apache.commons*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*org.apache.log4j*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*org.junit*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }
Get-ChildItem -Path $EclipsePluginsDir -Filter "*hamcrest*.jar" | Where-Object { $_.Name -notmatch "source" } | ForEach-Object { $jars.Add($_.FullName) }

# Directory-based Eclipse plugins (e.g., org.emoflon.neo.* directories)
Get-ChildItem -Path $EclipsePluginsDir -Directory -Filter "*org.emoflon.neo*" | ForEach-Object {
    if (Test-Path "$($_.FullName)\bin") {
        Write-Host "  + Copying plugin folder: $($_.Name)"
        Copy-Item -Path "$($_.FullName)\bin\*" -Destination $buildStagingDir -Recurse -Force
    }
}

# Deduplicate
$uniqueJars = $jars | Where-Object { Test-Path $_ } | Sort-Object -Unique

Write-Host "Unpacking $($uniqueJars.Count) JARs into staging directory..." -ForegroundColor Yellow

Push-Location $buildStagingDir
try {
    $count = 0
    foreach ($j in $uniqueJars) {
        $count++
        Write-Progress -Activity "Unpacking dependencies" -Status "$(Split-Path $j -Leaf)" -PercentComplete (($count / $uniqueJars.Count) * 100)
        & $jarExe xf $j
    }
    Write-Progress -Activity "Unpacking dependencies" -Completed

    # -----------------------------------------------------------------------
    # 5b. Re-copy project classes, models and merge Akka reference.conf
    # -----------------------------------------------------------------------
    Write-Host "`n  + Re-copying latest project classes and models..." -ForegroundColor Yellow
    foreach ($pDir in $projectDirs) {
        if (Test-Path $pDir) {
            Copy-Item -Path "$pDir\*" -Destination $buildStagingDir -Recurse -Force
        }
    }
    if (Test-Path $tggModelDir) {
        $destModelDir = Join-Path $buildStagingDir "model"
        New-Item -ItemType Directory -Path $destModelDir -Force | Out-Null
        Copy-Item -Path "$tggModelDir\*" -Destination $destModelDir -Recurse -Force
    }

    Write-Host "  + Merging Akka reference.conf files..." -ForegroundColor Yellow
    $akkaActorJar = "$ibexDir\FamiliesToPersonsIBeXTGG\lib\akka-actor_2.12-2.5.19.jar"
    $akkaStreamJar = "$ibexDir\FamiliesToPersonsIBeXTGG\lib\akka-stream_2.12-2.5.19.jar"
    if ((Test-Path $akkaActorJar) -and (Test-Path $akkaStreamJar)) {
        & python -c "import zipfile; z1=zipfile.ZipFile(r'$akkaActorJar'); z2=zipfile.ZipFile(r'$akkaStreamJar'); c1=z1.read('reference.conf').decode('utf-8'); c2=z2.read('reference.conf').decode('utf-8'); open('reference.conf', 'w', encoding='utf-8').write(c1 + '\n\n' + c2)"
    }

    # -----------------------------------------------------------------------
    # 6. Strip Digital Signatures & Write Manifest
    # -----------------------------------------------------------------------
    Write-Host "`n[5/6] Stripping digital signature files from META-INF..." -ForegroundColor Yellow
    if (Test-Path "META-INF") {
        Get-ChildItem -Path "META-INF" -Filter "*.SF" | Remove-Item -Force
        Get-ChildItem -Path "META-INF" -Filter "*.RSA" | Remove-Item -Force
        Get-ChildItem -Path "META-INF" -Filter "*.DSA" | Remove-Item -Force
        Get-ChildItem -Path "META-INF" -Filter "INDEX.LIST" | Remove-Item -Force
    }

    $manifestPath = Join-Path (Split-Path $buildStagingDir -Parent) "MANIFEST.MF"
    $manifestContent = @"
Manifest-Version: 1.0
Main-Class: org.benchmarx.examples.familiestopersons.scalability.ScalabilityMain

"@
    [System.IO.File]::WriteAllText($manifestPath, $manifestContent, [System.Text.Encoding]::ASCII)

    # -----------------------------------------------------------------------
    # 7. Create Fat JAR Archive
    # -----------------------------------------------------------------------
    Write-Host "`n[6/6] Packaging into $OutputFile..." -ForegroundColor Yellow
    $outParent = Split-Path $OutputFile -Parent
    if (-not (Test-Path $outParent)) {
        New-Item -ItemType Directory -Path $outParent -Force | Out-Null
    }
    if (Test-Path $OutputFile) {
        Remove-Item -Force $OutputFile
    }

    & $jarExe cfm $OutputFile $manifestPath .

} finally {
    Pop-Location
}

# Copy test resources directory alongside the output jar (for capability tests)
$resourcesSource = "$BenchmarxRoot\examples\familiestopersons\BenchmarxFamiliesToPersons\resources"
if (Test-Path $resourcesSource) {
    $destRes = Join-Path $outParent "resources"
    if (-not (Test-Path $destRes)) {
        Write-Host "  + Copying test resources directory to $destRes..." -ForegroundColor Yellow
        Copy-Item -Path $resourcesSource -Destination $outParent -Recurse -Force
    }
}

# ---------------------------------------------------------------------------
# 8. Summary
# ---------------------------------------------------------------------------
if (Test-Path $OutputFile) {
    $sizeBytes = (Get-Item $OutputFile).Length
    $sizeMB = [math]::Round($sizeBytes / 1MB, 2)
    Write-Host "`n==========================================================" -ForegroundColor Green
    Write-Host "   BUILD SUCCESSFUL!                                      " -ForegroundColor Green
    Write-Host "==========================================================" -ForegroundColor Green
    Write-Host "Artifact:    $OutputFile" -ForegroundColor Green
    Write-Host "Size:        $sizeMB MB" -ForegroundColor Green
    Write-Host "`nTo execute on Linux/Windows:" -ForegroundColor Cyan
    Write-Host "  java -jar $OutputFile --help" -ForegroundColor White
    Write-Host "  java -Xmx32G -jar $OutputFile -s ScalabilityIncrTestsBwd -r ./results" -ForegroundColor White
    Write-Host "==========================================================`n" -ForegroundColor Green
} else {
    Write-Error "Failed to produce output Fat JAR!"
    exit 1
}
