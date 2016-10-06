import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;

public class CacheLogTest {

    private CacheLog cacheLog;
    private final String URL = "www.yahoo.com";
    private BufferedReader reader;
    private BufferedWriter writer;
    private SimpleDateFormat format;

    @Before
    public void setup(){
        cacheLog = new CacheLog("./testdata/");
        format = new SimpleDateFormat("EEE MMMM dd HH:mm:ss yyyy");
        try{
            writer = new BufferedWriter(new FileWriter("./LRUProxy/testdata/output.log", false));
            reader = new BufferedReader(new FileReader("./LRUProxy/testdata/output.log"));
        }catch(Exception e){ e.printStackTrace(); }
    }

    @After
    public void destroy(){
        try{
            reader.close();
            writer.close();
        }catch (Exception e){ e.printStackTrace(); }
    }

    @Test
    public void logRemoval() throws Exception {

    }

    @Test
    public void logHit() throws Exception {

    }

    @Test
    public void logMiss() throws Exception {

    }

    @Test
    public void countLines() throws Exception{

    }

}