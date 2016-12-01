

/**
 * Test Suite created to run the following tests for Project Phase 1:
 * TestLFUCacheList
 * TestMRUCacheList
 * TestRRCacheList
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestLFUCacheList.class,
        TestMRUCacheList.class,
        TestRRCacheList.class
})

public class TestCacheListSuite {}