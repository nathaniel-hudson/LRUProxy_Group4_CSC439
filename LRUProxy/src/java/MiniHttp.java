import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * MiniHttp
 * @author Ken Cooney
 * @date 06/11/2011
 *
 * This fetches the HTML page as defined URL
 * and returns it as a StringBuffer.
 * 
 * This can be used to save the output to a file (cache).
 * 
 */
public class MiniHttp 
{
	 HttpClient httpclient;
	 BufferedReader in;
    
	public MiniHttp()
	{
		in = null;
		httpclient = new DefaultHttpClient();
	}
	
	public StringBuffer fetch(String URL)
	{
		StringBuffer sb = new StringBuffer("");
	
		if (URL != null && URL.trim().length()>0)
		{
			// If URL doesn't start with http://, add it
			if (! URL.startsWith("http://") &&
				! URL.startsWith("https://"))
			{
				URL="http://"+URL;
			}
			
			try
			{
					HttpGet httpget = new HttpGet(URL);
					HttpResponse response = httpclient.execute(httpget);
					InputStream is = response.getEntity().getContent();
					in = new BufferedReader(new InputStreamReader(is));

					String line = "";
					while ((line = in.readLine()) != null) 
					{
						sb.append(line + "\n");
					}
					in.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (in != null)
				{
					try
					{
						in.close();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return sb;
	}
	
}
