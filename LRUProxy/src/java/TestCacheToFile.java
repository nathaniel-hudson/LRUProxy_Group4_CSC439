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
@RunWith(value=Parameterized.class)
public class TestCacheToFile {
    CacheToFile toFile;
    String directory = "C:/Users/hensleyl4/IdeaProjects/CSC439Project/dir/";
    String url;
    String urlFilename;
    String urlContains;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Parameters
    public static Collection<String[]> getTestParameters(){
        return Arrays.asList(new String[][]{
                {"google.com", "google.com", "search engine"},
                {"weather.com/weather/today", "weather.com.weather.today", "weather"},
                {"yahoo.com", "yahoo.com", "search"},
                {"https://www.google.com/#q=american+sign+language", "https:..www.google.com.#q=american+sign+language", "asl"}
        });
    }
    public TestCacheToFile(String url, String urlFilename, String urlContains){
        this.url = url;
        this.urlFilename = urlFilename;
        this.urlContains = urlContains;
    }
    @Before
    public void initialize(){
        toFile = new CacheToFile(directory);
    }
    @Test
    public void testIsCached(){
        assertFalse(toFile.isCached(url));
        toFile.write(url, new StringBuffer(urlContains));
        assertTrue(toFile.isCached(url));
        assertEquals(toFile.isCached(urlFilename), new File(directory+urlFilename).exists());
    }
    @Test
    public void testRemove(){
        toFile.remove(url);
        toFile.write(url, new StringBuffer(urlContains));
        toFile.remove(url);
        assertFalse(toFile.isCached(url));                  //returns true if I don't close the BufferedReader
        assertTrue(!(new File(directory+urlFilename)).exists());
    }

    @Test
    public void testWrite(){
        toFile.write(url, new StringBuffer(urlContains));
        File tmp = new File(directory+urlFilename);
        assertTrue(tmp.exists());
        //check if contents match urlContains string
        try {
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

    @Test
    public void testRead(){
        toFile.write(url, new StringBuffer(urlContains));

        //does this implementation work?
        System.setOut(new PrintStream(outContent));
        toFile.read(url);
        assertEquals(urlContains, outContent.toString());

    }
    @After
    public void tearDown(){
        toFile.remove(url);
    }


}
