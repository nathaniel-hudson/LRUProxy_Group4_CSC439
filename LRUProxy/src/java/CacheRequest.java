import java.io.BufferedReader;
import java.io.FileReader;

/**
 * CacheRequest
 * @author Ken Cooney
 * @date 06/10/2011
 * 
 * This reads the file that has the cache requests.
 * 
 * 
 * TESTED via CacheRequestTestSuite.  All tests pass.
 */
public class CacheRequest 
{

	BufferedReader in;
	public CacheRequest(String directory)
	{
		String filename=directory+"input.txt";
		try
		{
			in = new BufferedReader(new FileReader(filename));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * read
	 * @return next request or empty string if no request.
	 */
	public String read()
	{
		String line="";
		try
		{
			line=in.readLine();
			if (line == null)
			{
				line="";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return line;
	}
}
