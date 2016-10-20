/**
 * Test Suite created to run the following tests:
 * TestCacheLog
 * TestCacheList
 * TestCacheRequest
 * TestCacheToFile
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestCacheList.class,
        TestCacheLog.class,
        TestCacheRequest.class,
        TestCacheToFile.class
})

public class TestCacheSuite {
}
