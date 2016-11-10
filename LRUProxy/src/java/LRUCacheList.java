import java.util.LinkedList;
import java.util.List;

/**
 * Created by jansens2 on 11/10/16.
 */
public class LruCacheList extends CacheList<LinkedList<String>> {

    public LruCacheList(List cacheList, String directory, int maxsize) {
        super(directory, maxsize);
    }

    @Override
    public String addNewObject(String url, boolean hit) {
        String removedURL="";

        if (hit) {
            getCache().remove(url);
        }

        // If size is MAXSIZE, remove last link
        if (getCache().size()==getCacheSize()) {
            removedURL=(String)getCache().getLast();
            getLog().logRemoval(removedURL);
            getCache().removeLast();
        }

        // Newest is always the first.
        getCache().addFirst(url);

        //System.out.println("Added "+URL);
        //traverseTest();

        return removedURL;
    }
}
