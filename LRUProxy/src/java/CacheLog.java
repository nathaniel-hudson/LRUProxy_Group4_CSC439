import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * CacheLog
 * @author Ken Cooney
 * @date 06/10/2011
 * 
 * Writes to the output.log what is going on with the cache.
 * 
 * 
 * TESTED via CacheLogTestSuite.  All tests pass.
 */
public class CacheLog 
{

	private BufferedWriter out;
	private String filename;
	private SimpleDateFormat format;
	
	private String returnString;
	
	public CacheLog(String directory)
	{
		filename=directory+"/output.log";
		returnString="\n";
		format =
            new SimpleDateFormat("EEE MMMM dd HH:mm:ss yyyy");

	}
	
	public void openLogForAppend()
	{
		try
		{
			out = new BufferedWriter(new FileWriter(filename, true));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void logRemoval(String URL)
	{
		openLogForAppend();
		Calendar cal = Calendar.getInstance();
		String dateString=format.format(cal.getTime());
		try
		{
			out.write(dateString+" "+URL+" the cached page is evicted"+returnString);
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void logHit(String URL)
	{
		openLogForAppend();
		Calendar cal = Calendar.getInstance();
		String dateString=format.format(cal.getTime());
		try
		{
			out.write(dateString+" "+URL+" cache hit"+returnString);
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void logMiss(String URL)
	{
		openLogForAppend();
		Calendar cal = Calendar.getInstance();
		String dateString=format.format(cal.getTime());
		try
		{
			out.write(dateString+" "+URL+" cache miss"+returnString);
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
