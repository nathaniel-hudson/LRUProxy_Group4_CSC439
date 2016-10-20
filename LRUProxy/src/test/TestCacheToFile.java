import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
/**
 * Created by hensleyl4 on 9/22/2016.
 */
/**
 * parameterized test class which tests the CacheToFile class
 * */
@RunWith(value=Parameterized.class)
public class TestCacheToFile{
    CacheToFile toFile;
    String directory = "./LRUProxy/src/test/test_data";
    String url;
    String urlFilename;
    String urlContains;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * returns sets of parameters to run the test cases with
     * */
    @Parameters
    public static Collection<String[]> getTestParameters(){
        return Arrays.asList(new String[][]{
                {"google.com", "google.com", "search engine"},
                {"weather.com/weather/today", "weather.com.weather.today", "weather"},
                {"yahoo.com", "yahoo.com", "search"},
                {"https://www.google.com/#q=american+sign+language", "https...www.google.com.#q=american+sign+language", "asl"}
        });
    }

    /**
     * constructor accepts url, filename, and the info at that url
     * */
    public TestCacheToFile(String url, String urlFilename, String urlContains){
        this.url = url;
        this.urlFilename = urlFilename;
        this.urlContains = urlContains;
    }

    /**
     * creates a new CacheToFile object before each test is run
     * */
    @Before
    public void initialize(){
        toFile = new CacheToFile(directory);
    }

    /**
     * method tests the isCached method, which should return true
     * if the url is already cached
     * */
    @Test
    public void testIsCached(){
        //url shouldn't be cached initially
        assertFalse(toFile.isCached(url));
        toFile.write(url, new StringBuffer(urlContains));
        //url should be cached after it's written in CacheToFile
        assertTrue(toFile.isCached(url));
        assertEquals(toFile.isCached(url), new File(directory + urlFilename).exists());
    }
    /**method tests the remove method, which should delete
     * the file containing the url info
     * */
    @Test
    public void testRemove(){
        toFile.write(url, new StringBuffer(urlContains));
        toFile.remove(url);
        //check to make sure url is no longer considered cached and a file doesn't exist for the url
        assertFalse(toFile.isCached(url));
        assertTrue(!(new File(directory+urlFilename)).exists());
    }

    /**
     * tests the write method, which should write the info contained by url
     * to a file named 'urlFilename'
     * */
    @Test
    public void testWrite(){
        toFile.write(url, new StringBuffer(urlContains));
        File tmp = new File(directory + urlFilename);
        assertTrue(tmp.exists());
        //check if contents of file match urlContains string
        try{
            BufferedReader read = new BufferedReader(new FileReader(tmp));
            assertEquals(read.readLine(), urlContains);
            read.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * tests the read method, which outputs what the info the url contains
     * */
    @Test
    public void testRead(){
        toFile.write(url, new StringBuffer(urlContains));

        //check to see if what is read matches urlContains
        System.setOut(new PrintStream(outContent));
        toFile.read(url);
        assertEquals(urlContains, outContent.toString());

    }
    /**
     * removes cached file so that next test can be run from clean slate
     * */
    @After
    public void tearDown(){
        toFile.remove(url);
    }


}
