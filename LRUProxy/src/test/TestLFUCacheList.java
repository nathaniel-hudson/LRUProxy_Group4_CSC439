import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hensleyl4 on 11/30/2016.
 */
public class TestLFUCacheList {
    private static final String[] URLS = {
            "www.google.com",
            "www.yahoo.com",
            "www.microsoft.com"
    };
    private static final String NOT_IN_URLS = "www.DoesNotExist.com";
    private LFUCacheList mCacheList;

    @Before
    public void before() {
        // Reset the cache before each test
        mCacheList = new LFUCacheList("./", URLS.length);
    }

    @Test
    public void testAddNewObject() throws Exception {
        String removedUrl;

        // Add all items so the cache is full
        for (String URL : URLS) {
            removedUrl = mCacheList.addNewObject(URL, false);
            // Nothing should have been removed
            assertEquals("", removedUrl);
        }

        // Add every item again except for the last item
        for (int i = 0; i < URLS.length - 1; i++) {
            mCacheList.addNewObject(URLS[i], false);
        }
        // Ensure the least frequently used item (last item) is at the head
        assertEquals(URLS[URLS.length - 1], mCacheList.getHead());

        // Add the previously excluded item so the cache is normalized
        mCacheList.addNewObject(URLS[URLS.length - 1], false);

        // Add an item that does not exist in the cache
        mCacheList.addNewObject(NOT_IN_URLS, false);
        // Ensure that the newly added URL is at the head
        assertEquals(NOT_IN_URLS, mCacheList.getHead());
    }

    @Test
    public void testGetCacheSize() throws Exception {
        // Add all items so the cache is full
        for (String URL : URLS) {
            mCacheList.addNewObject(URL, false);
        }

        // The cache size should be at maximum capacity
        assertEquals(URLS.length, mCacheList.getCacheSize());

        // The cache size should remain the same even if we add more items
        mCacheList.addNewObject(URLS[0], false);
        assertEquals(URLS.length, mCacheList.getCacheSize());
    }

    @Test
    public void testGetHead() throws Exception {
        // The head should be an empty string initially
        assertEquals("", mCacheList.getHead());

        // The head should be the first URL added
        mCacheList.addNewObject(URLS[0], false);
        assertEquals(URLS[0], mCacheList.getHead());
    }
}
