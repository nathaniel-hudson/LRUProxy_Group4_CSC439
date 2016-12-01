import java.util.LinkedList;
import java.util.List;

/**
 * Created by jansens2 on 11/10/16.
 */
public class LRUCacheList implements CacheList {

    private CacheLog log; // not used yet
    private LinkedList<String> linkedList;
    private int maxSize;

    /**
     * Constructor.  The minimum cache size is 1.
     * @param directory - cache log directory for logging
     *                    objects removed from cache
     * @param maxsize - maximum number of objects to cache
     */
    public LRUCacheList(String directory, int maxsize)
    {
        log = new CacheLog(directory);
        linkedList=new LinkedList<String>();
        if (maxsize<1)
        {
            this.maxSize=1;
        }
        else
        {
            this.maxSize=maxsize;
        }
    }

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
    public String addNewObject(String URL, boolean hit)
    {
        String removedURL="";

        if (hit)
        {
            linkedList.remove(URL);
        }


        // If size is MAXSIZE, remove last link
        if (linkedList.size()==maxSize)
        {
            removedURL=(String)linkedList.getLast();
            log.logRemoval(removedURL);
            linkedList.removeLast();
        }

        // Newest is always the first.
        linkedList.addFirst(URL);

        //System.out.println("Added "+URL);

        //traverseTest();

        return removedURL;
    }

    /**
     * getCacheSize
     * Used by CacheListSizeThreeTests
     * @return the number of objects cached
     */
    public int getCacheSize()
    {
        return linkedList.size();
    }

    /**
     * getHead
     * Used by CacheListSizeThreeTests
     * @return URL at this location or empty string if
     *         linkedlist is empty.
     */
    public String getHead()
    {
        String returnedURL="";
        if (linkedList.size()>0)
        {
            returnedURL=linkedList.getFirst().toString();
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
    public String get(int i)
    {
        String returnedURL="";
        if (i<linkedList.size() && i >= 0)
        {
            returnedURL=linkedList.get(i).toString();
        }
        return returnedURL;
    }
}
