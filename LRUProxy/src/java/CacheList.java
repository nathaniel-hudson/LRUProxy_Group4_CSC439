import java.util.LinkedList;

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
public interface CacheList
{
	int LRU = 0;
	int  RR = 1;
	int LFU = 2;
	int MRU = 3;
	
	/**
	 * addNewObject
	 * This puts the object in the front of he queue.
	 * It removes any repeated object and trims the
	 * list if the length exceeds maxSize.
	 * 
	 * @param URL - URL that was just requested
	 * @param hit - true if it was already cached.
	 * @return - object removed, if any.  We'll need
	 *           to remove this from the hash.
	 */
	String addNewObject(String URL, boolean hit);
	
	/**
	 * getCacheSize
	 * Used by CacheListSizeThreeTests
	 * @return the number of objects cached
	 */
	int getCacheSize();
	
	/**
	 * getHead
	 * Used by CacheListSizeThreeTests
	 * @return URL at this location or empty string if 
	 *         linkedlist is empty.
	 */
	String getHead();
}
