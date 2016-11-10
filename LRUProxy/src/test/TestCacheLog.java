import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static org.junit.Assert.*;

/**
 * TestCacheLog is responsible for testing the CacheLog java file. This class contains tests for the following methods:
 * logRemoval
 * logHit
 * logMiss
 */
public class TestCacheLog {

    /**
     * Class variables.
     */
    private String slash = File.separator;
    private String filename = "." + slash + "LRUProxy" + slash + "testdata" + slash + "output.log";
    private CacheLog cacheLog;
    private final String URL = "www.yahoo.com";
    private BufferedReader reader;
    private BufferedWriter writer;
    private SimpleDateFormat format;
    private TestHelperClass testHelper = new TestHelperClass();

    /**
     * This method deletes the old output.log file in the testdata directory and creates a new one.
     * It also creates a new CacheLog object, SimpleDataFormat object,
     * BufferedReader object & BufferedWriter object.
     */
    @Before
    public void setup(){
        try{

            File file = new File(testHelper.makeOSRelativePath(filename));
            file.delete();
            File file2 = new File(testHelper.makeOSRelativePath(filename));
            cacheLog = new CacheLog("." + slash + "LRUProxy" + slash + "testdata" + slash);
            format = new SimpleDateFormat("EEE MMMM dd HH:mm:ss yyyy");
            writer = new BufferedWriter(new FileWriter(testHelper.makeOSRelativePath(filename), false));
            reader = new BufferedReader(new FileReader(testHelper.makeOSRelativePath(filename)));
        }catch(Exception e){ e.printStackTrace();}
    }

    /**
     * This method closes BufferedReader & BufferedWriter.
     */
    @After
    public void destroy(){
        try{
            reader.close();
            writer.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is a test for logRemoval that compares a dummy String (outStringExpected) to a String that is written to the
     * output.log file via cacheLog.
     */
    @Test
    public void logRemovalTest() throws Exception {

        System.out.println("Testing logRemoval...");
        Calendar cal = Calendar.getInstance();
        format = new SimpleDateFormat("EEE MMMM dd HH:mm:ss yyyy");
        String dateString = format.format(cal.getTime());
        String outStringExpected = dateString + " " + URL + " the cached page is evicted";
        /** Read string from dummy output file and compare to expected output. */
        try{

            cacheLog.openLogForAppend();
            cacheLog.logRemoval(URL);
            String outString = readln();
            reader.close();
            assertEquals(outStringExpected, outString);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is a test for logHit that compares a dummy String (outStringExpected) to a String that is written to the
     * output.log file via cacheLog.
     */
    @Test
    public void logHitTest() throws Exception {
        System.out.println("Testing logHit...");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMMM dd HH:mm:ss yyyy");
        String dateS = format.format(calendar.getTime());
        String StringExpected = dateS + " " + URL + " cache hit";
        /** Read string from dummy output file and compare to expected output. */
        try {
            cacheLog.openLogForAppend();
            cacheLog.logHit(URL);
            String outString = readln();
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

    /**
     * This method is a test for logMiss that compares a dummy String (outStringExpected) to a String that is written to the
     * output.log file via cacheLog.
     */
    @Test
    public void logMissTest() throws Exception {
        System.out.println("Testing logMiss...");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMMM dd HH:mm:ss yyyy");
        String dateS = format.format(calendar.getTime());
        String StringExpected = dateS + " " + URL + " cache miss";
        /** Read string from dummy output file and compare to expected output. */
        try {
            cacheLog.openLogForAppend();
            cacheLog.logMiss(URL);
            String outString = readln();
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


    /**
     * This method reads in the next line of a file.
     */
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