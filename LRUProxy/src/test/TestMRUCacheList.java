import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.*;


/**
 * Created by hensleyl4 on 11/30/2016.
 */
@RunWith(value=Parameterized.class)
public class TestMRUCacheList {
    private static String url;
    private static MRUCacheList cacheList;
    private static final String directory = "./";
    private static final int maxSize = 3;
    private static int total = 0;
    private static LinkedList<String> list = new LinkedList<String>();
    /**
     * returns sets of parameters to run the test cases with
     * */
    @Parameterized.Parameters
    public static Collection<String[]> getTestParameters(){
        return Arrays.asList(new String[][]{
                {"google.com"},
                {"weather.com/weather/today"},
                {"yahoo.com"},
                {"https://www.google.com/#q=american+sign+language"}
        });
    }

    /**
     * constructor accepts url, filename, and the info at that url
     * */
    public TestMRUCacheList(String url){
        this.url = url;
    }

    @BeforeClass
    public static void initialize(){
        cacheList = new MRUCacheList(TestHelperClass.makeOSRelativePath(directory), maxSize);

    }

    /**
     *tests addNewObject() which should return an empty string if
     * the cache hasn't reached max size yet, otherwise,
     * returns the url of the object removed from the cache
     */
    @Test
    public void testAddNewObject() throws Exception {
        if(total < maxSize) {
            assertEquals(cacheList.addNewObject(url, true), "");
            list.add(0, url);
            total++;
        }
        else{
            assertEquals(list.getFirst(), cacheList.addNewObject(url, true));
            list.removeFirst();
            list.add(0, url);
        }

    }

    /**
     * tests the getCacheSize() method which should return
     * the total number of elements in cache
     */
    @Test
    public void testGetCacheSize() throws Exception {
        assertEquals(cacheList.getCacheSize(), total);
    }

    /**
     * tests the getHead() method which should return the item
     * at start of stored list. If cache is empty, should
     * return the empty string.
     */
    @Test
    public void testGetHead() throws Exception {
        if(total != 0) {
            assertEquals(list.getFirst(), cacheList.getHead());
            assertEquals(cacheList.get(0), cacheList.getHead());
        }
        else
            assertEquals(cacheList.getHead(), "");
    }

    /**
     * tests the get(i) method which should return the element
     * at index i. If i is out of bounds, then should return
     * the empty string.
     */
    @Test
    public void testGet() throws Exception {
        if(total > 0)
            assertEquals(cacheList.get(0), list.get(0));
        else
            assertEquals(cacheList.get(0), "");
        assertEquals(cacheList.get(maxSize), "");
        assertEquals(cacheList.get(-1), "");
    }

    /**
     * tests if multiple addNewObject() methods
     * can be completed within 500 ms
     */
    @Test(timeout=500)
    public void testMultipleAdds(){
        RRCacheList temp = new RRCacheList(directory, maxSize);
        Random rand = new Random();
        for(int i = 0; i < 100; i++){
            temp.addNewObject(Integer.toString(rand.nextInt(20)), true);
        }
    }
}