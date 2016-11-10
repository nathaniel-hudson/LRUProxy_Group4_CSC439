import java.util.LinkedList;
import java.util.List;

/**
 * CacheList
 * @author Ken Cooney
 * @date 06/09/2011
 * 
 * The most recently requested object is the first entry in
 * the linked list.  MaxSize is variable (assigned in the 
 * constructor)
 * 
 * 
 * Cost associated with scenarios:
 * 1) adding a new cache entry, cached objects < maxSize
 *    cost: 3 (check for cached file, add to start of list, cache file)
 *    
 * 2) adding a new cache entry, cached objects = maxSize
 *    cost: 4 (check for cached file, remove last object in linked list, add to start of list, remove cached file)
 *
 * 3) finding entry in cache
 *    average cost: 2+ceiling(maxSize/2) (check for cached file, remove object in linked list, add to start of list)
 *    worst case cost: 2+maxSize (check for cached file, remove object in linked list, add to start of list)
 *    
 * TESTED via CachListTestSuite.  All tests pass.
 * 
 */
public abstract class CacheList <T extends List<String>> {
	private CacheLog mLog;
	private T mCacheList;
	private int mMaxSize;

	/**
	 * addNewObject
	 * This puts the object in the front of he queue.
	 * It removes any repeated object and trims the
	 * list if the length exceeds maxSize.
	 *
	 * @param Url - URL that was just requested
	 * @param hit - true if it was already cached.
	 * @return - object removed, if any.  We'll need
	 *           to remove this from the hash.
	 */
	public abstract String addNewObject(String Url, boolean hit);

	/**
	 * Constructor.  The minimum cache size is 1.
	 * @param directory - cache log directory for logging 
	 *                    objects removed from cache
	 * @param maxsize - maximum number of objects to cache
	 */
	public CacheList(String directory, int maxsize)
	{
		mCacheList = new List<String>;
		mLog = new CacheLog(directory);
		if (maxsize < 1) {
			mMaxSize = 1;
		}
		else {
			mMaxSize = maxsize;
		}
	}

	public CacheLog getLog() {
		return mLog;
	}

	public T getCache() {
		return mCacheList;
	}

	public int getCacheSize() {
		return mCacheList.size();
	}
	
	/**
	 * getHead
	 * Used by CacheListSizeThreeTests
	 * @return URL at this location or empty string if 
	 *         linkedlist is empty.
	 */
	public String getHead() {
		String returnedURL = "";
		if (mCacheList.size() > 0) {
			returnedURL = mCacheList.get(0);
		}
		return returnedURL;
	}

	/**
	 * get
	 * Used by CacheListSizeThreeTests
	 * @param i - index into the linklist.
	 * @return URL at this location or empty string if 
	 *         param exceeds the size of linked list
	 */
	public String get(int i) {
		String returnedURL = "";
		if (i < mCacheList.size())
		{
			returnedURL = mCacheList.get(i);
		}
		return returnedURL;
	}

	/**
	 * traverseList
	 * For testing purposes only.  This displays the 
	 * linklist of URLs.
	 */
	public void traverseTest()
	{
		for (int i=0; i<mCacheList.size();i++)
		{
			System.out.print(mCacheList.get(i) + " => ");
		}
		System.out.println("NULL");
	}
}
