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
public class TestLFUCacheList {
    private static ArrayList<String> urls;
    private static ArrayList<Integer> hits;
    private static LFUCacheList cacheList;
    private static final String TEST_DIRECTORY = "./";
    private static String directory;
    private static final int maxSize = 2;
    private static int total = 0;
    private static int leastHits = -1;
    private static int leastHitsIndex;
    /**
     * returns sets of parameters to run the test cases with
     * */
    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters(){
        return Arrays.asList(new Object[][]{
                {new ArrayList<String>(Arrays.asList("google.com", "weather.com", "youtube.com")), new ArrayList<Integer>(Arrays.asList(2, 1, 3))}
        });
    }

    /**
     * constructor accepts url, filename, and the info at that url
     * */
    public TestLFUCacheList(ArrayList<String> urls, ArrayList<Integer> hits){
        this.urls = urls;
        this.hits = hits;
        // Determine which way slashes go for directories.
        String os = System.getProperty("os.name").toLowerCase();
        boolean windows = (os.indexOf("win") >= 0);

        // Set the directory.
        directory = TestHelperClass.makeOSRelativePath(TEST_DIRECTORY);
        if (windows && !directory.endsWith("\\")) {
            directory = directory + "\\";
        }
        if (!windows && !directory.endsWith("/")) {
            directory = directory + "/";
        }
        cacheList = new LFUCacheList(directory, maxSize);
    }

    @Test
    public void testAddNewObject() throws Exception {

        for(int i = 0; i < hits.size(); i++){

            if(total < maxSize) {
                for(int h = 0; h < hits.get(i); h++)
                    assertEquals(cacheList.addNewObject(urls.get(i), true), "");
                total++;
                if(leastHits == -1 || leastHits > hits.get(i)){
                    leastHits = hits.get(i);
                    leastHitsIndex = i;
                }
            }
            else{
                assertEquals(cacheList.addNewObject(urls.get(i), true), urls.get(leastHitsIndex));
                urls.remove(leastHitsIndex);
                hits.remove(leastHitsIndex);
                determineLeastHitsIndex();
            }
        }

    }

    @Test
    public void testGetCacheSize() throws Exception {
        assertEquals(cacheList.getCacheSize(), total);
    }

    @Test
    public void testGetHead() throws Exception {
        if(total != 0) {
            assertEquals(urls.get(leastHitsIndex), cacheList.getHead());
            //assertEquals(cacheList.getHead(), cacheList.get(0));
        }
        else
            assertEquals(cacheList.getHead(), "");
    }

    private void determineLeastHitsIndex(){
        leastHitsIndex = -1;
        for(int i = 0; i < hits.size(); i++){
            if(leastHitsIndex == -1 || hits.get(i) < leastHits){
                leastHits = hits.get(i);
                leastHitsIndex = i;
            }
        }
    }

    @Test(timeout=200)
    public void testMultipleAdds(){
        LFUCacheList tempList = new LFUCacheList(directory, 10);
        Random rand = new Random();
        for(int i = 0; i < 100; i++){
            tempList.addNewObject(String.valueOf(rand.nextInt(20)), true);
        }
    }
}