import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;

public class CacheLogTest {

    private String filename = "./LRUProxy/testdata/output.log";
    private CacheLog cacheLog;
    private final String URL = "www.yahoo.com";
    private BufferedReader reader;
    private BufferedWriter writer;
    private SimpleDateFormat format;

    /* Delete old output.log file in testdata and create a new one. Create a new CacheLog object, SimpleDataFormat,
        BufferedReader & BufferedWriter. */
    @Before
    public void setup(){
        try{
            File file = new File(filename);
            file.delete();
            File file2 = new File("./LRUProxy/testdata/");
            cacheLog = new CacheLog("./LRUProxy/testdata/");
            format = new SimpleDateFormat("EEE MMMM dd HH:mm:ss yyyy");
            writer = new BufferedWriter(new FileWriter(filename, false));
            reader = new BufferedReader(new FileReader(filename));
        }catch(Exception e){ e.printStackTrace(); }
    }

    /* Closes BufferedReader & BufferedWriter. */
    @After
    public void destroy(){
        try{
            reader.close();
            writer.close();

        }catch (Exception e){ e.printStackTrace(); }
    }

    /* A test for logRemoval that compares a dummy String (outStringExpected) to a String that is written to the
        output.log file via cacheLog. */
    @Test
    public void logRemovalTest() throws Exception {

        System.out.println("Testing logRemoval...");
        Calendar cal = Calendar.getInstance();
        format = new SimpleDateFormat("EEE MMMM dd HH:mm:ss yyyy");
        String dateString = format.format(cal.getTime());
        String outStringExpected = dateString + " " + URL + " the cached page is evicted";
        // Read string from dummy output file and compare to expected output.
        try{

            cacheLog.openLogForAppend();
            cacheLog.logRemoval(URL);
            String outString = readln();
            reader.close();
            assertEquals(outStringExpected, outString);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /* A test for logHit that compares a dummy String (outStringExpected) to a String that is written to the
        output.log file via cacheLog. */
    @Test
    public void logHitTest() throws Exception {
        System.out.println("Testing logHit...");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMMM dd HH:mm:ss yyyy");
        String dateS = format.format(calendar.getTime());
        String StringExpected = dateS + " " + URL + " cache hit";
        // Read string from dummy output file and compare to expected output.
        try {
            cacheLog.openLogForAppend();
            cacheLog.logHit(URL);
            String outString = reader.readLine();
            reader.close();
            assertEquals(StringExpected, outString);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* A test for logMiss that compares a dummy String (outStringExpected) to a String that is written to the
       output.log file via cacheLog. */
    @Test
    public void logMissTest() throws Exception {
        System.out.println("Testing logMiss...");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMMM dd HH:mm:ss yyyy");
        String dateS = format.format(calendar.getTime());
        String StringExpected = dateS + " " + URL + " cache miss";
        // Read string from dummy output file and compare to expected output.
        try {
            cacheLog.openLogForAppend();
            cacheLog.logMiss(URL);
            String outString = reader.readLine();
            reader.close();
            assertEquals(StringExpected, outString);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* Reads in the next line of a file. */
    private String readln(){
        String line = "";
        try{
            line = reader.readLine();
            if (line == null){
                line = "";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return line;
    }
}