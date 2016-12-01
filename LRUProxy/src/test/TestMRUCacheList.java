import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by hensleyl4 on 11/30/2016.
 */
@RunWith(value=Parameterized.class)
public class TestMRUCacheList {
    private static String url;
    private static MRUCacheList cacheList;
    private static final String directory = ".\\LRUProxy\\testdata\\";
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
        cacheList = new MRUCacheList(directory, maxSize);
    }
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

    @Test
    public void testGetCacheSize() throws Exception {
        assertEquals(cacheList.getCacheSize(), total);
    }

    @Test
    public void testGetHead() throws Exception {
        if(total != 0) {
            assertEquals(list.getFirst(), cacheList.getHead());
            assertEquals(cacheList.get(0), cacheList.getHead());
        }
        else
            assertEquals(cacheList.getHead(), "");
    }

    @Test
    public void testGet() throws Exception {
        if(total > 0)
            assertEquals(cacheList.get(0), list.get(0));
        else
            assertEquals(cacheList.get(0), "");
        assertEquals(cacheList.get(maxSize), "");
        assertEquals(cacheList.get(-1), "");
        //cacheList doesn't handle negative integers properly
    }

}