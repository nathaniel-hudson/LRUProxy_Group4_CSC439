import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shane Jansen on 11/14/16.
 */
public class LFUCacheList implements CacheList {
    private CacheLog mCacheLog;
    private HashMap<String, Integer> mCacheList;
    private int mMaxSize;

    public LFUCacheList(String directory, int maxSize) {
        mCacheLog = new CacheLog(directory);
        mCacheList = new HashMap<>();

        if (maxSize < 1) mMaxSize = 1;
        else mMaxSize = maxSize;
    }

    @Override
    public String addNewObject(String url, boolean hit) {
        String removedUrl = "";

        if (!mCacheList.containsKey(url) && getCacheSize() == mMaxSize) {
            // Remove the least frequently used item
            removedUrl = findLfuItem();
            if (!removedUrl.isEmpty()) mCacheList.remove(removedUrl);
            mCacheLog.logRemoval(removedUrl);
        }

        // Increment the hit count
        int hitCount = mCacheList.getOrDefault(url, 0);
        hitCount++;
        mCacheList.put(url, hitCount);

        return removedUrl;
    }

    @Override
    public int getCacheSize() {
        return mCacheList.size();
    }

    @Override
    public String getHead() {
        return findLfuItem();
    }

    private String findLfuItem() {
        int lfuCount = -1;
        String lfuKey = "";
        for (Map.Entry<String, Integer> entry : mCacheList.entrySet()) {
            if (lfuCount == -1 || entry.getValue() < lfuCount) {
                lfuCount = entry.getValue();
                lfuKey = entry.getKey();
            }
        }
        return lfuKey;
    }
}
