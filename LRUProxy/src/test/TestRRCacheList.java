import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by hensleyl4 on 11/30/2016.
 */
@RunWith(value=Parameterized.class)
public class TestRRCacheList {
    private static RRCacheList cacheList;
    private static final String directory = "./";
    private static final int maxSize = 2;
    private static int total = 0;
    private static String url;
    private static LinkedList<String> list = new LinkedList<String>();
    /**
     * returns sets of parameters to run the test cases with
     * */
    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters(){
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
    public TestRRCacheList(String url){
        this.url = url;
    }

    @BeforeClass
    public static void initialize(){
        cacheList = new RRCacheList(directory, maxSize);
    }

    @Test
    public void testAddNewObject() throws Exception {
        if(total < maxSize) {
            assertEquals(cacheList.addNewObject(url, true), "");
            list.add(url);
            total++;
        }
        else{
            String removedURL = cacheList.addNewObject(url, true);
            assertNotNull(removedURL);
            int i = list.indexOf(removedURL);
            list.remove(removedURL);
            list.add(i, url);
        }

    }

    @Test
    public void testGetCacheSize() throws Exception {
        assertEquals(cacheList.getCacheSize(), total);
    }

    @Test
    public void testGetHead() throws Exception {
        if(total != 0) {
            assertEquals(list.get(0), cacheList.getHead());
            assertEquals(cacheList.getHead(), cacheList.get(0));
        }
        else
            assertEquals(cacheList.getHead(), "");
    }

    @Test
    public void testGet() throws Exception {
        if(total != 0)
            assertEquals(cacheList.get(0), list.get(0));
        assertEquals(cacheList.get(maxSize), "");
        assertEquals(cacheList.get(-1), "");
    }

}