# BCerT Compatibility Test Suite Detailed Results

Persistent tracking log for BCerT model-transformation test execution.

## Summary

| Suite | Total Tests | Passed | Failed | Pass Rate |
| :--- | :--- | :--- | :--- | :--- |
| **IncrementalForward** | 8 | 8 | 0 | 100% |
| **IncrementalBackward** | 8 | 7 | 1 (in progress) | 87.5% |

---

## Detailed Test Case Log

### `IncrementalForward` Test Suite (100% GREEN)

1. `testIncrementalInsertsFixedConfig` — **PASSED GREEN**
2. `testIncrementalInsertsDynamicConfig` — **PASSED GREEN**
3. `testIncrementalDeletions` — **PASSED GREEN**
4. `testIncrementalRename` — **PASSED GREEN**
5. `testIncrementalMove` — **PASSED GREEN**
6. `testIncrementalMixedDynamic` — **PASSED GREEN**
7. `testStability` — **PASSED GREEN**
8. `testHippocraticness` — **PASSED GREEN**

---

### `IncrementalBackward` Test Suite

1. `testIncrementalInsertsFixedConfig` — **PASSED GREEN**
2. `testIncrementalInsertsDynamicConfig` — **PASSED GREEN** *(Resolved: Role-specific birthday mapping & setupModel cleanup)*
3. `testIncrementalDeletions` — **PASSED GREEN** *(Resolved: Native BWD member deletion in Classical B)*
4. `testIncrementalMixedDynamic` — **PASSED GREEN**
5. `testStability` — **PASSED GREEN**
6. `testHippocraticness` — **PASSED GREEN**
7. `testIncrementalRenamingDynamic` — **IN PROGRESS** *(Classical B `RegisterMapping` integration)*
