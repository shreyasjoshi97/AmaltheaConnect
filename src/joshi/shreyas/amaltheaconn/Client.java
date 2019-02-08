package joshi.shreyas.amaltheaconn;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

public class Client {
	public String serverUrl = "https://amaltheaserver.herokuapp.com/";
	//public String serverUrl = "http://localhost:5000/";
	public Client()
	{
		URL url;
		try
		{
			url = new URL(serverUrl);
			String contents = "|" + readFile() + "~";
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(60000);
			conn.setConnectTimeout(30000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(contents);
			//System.out.println("Before flush: " +Integer.toString(conn.getResponseCode()));
			writer.flush();
			//writer.close();
			
			int responseCode = conn.getResponseCode();
			System.out.println("After flush: " +Integer.toString(conn.getResponseCode()));
			
			if(responseCode == HttpsURLConnection.HTTP_OK)
			{
				String line;
				String response = "";
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while(br.ready())
				{
					if((line = br.readLine()) != null)
					{
						response += line + "\n";
						System.out.println(response);
					}
				}
			}
			//writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String readFile()
	{
		String ret = "";

        try {
            FileInputStream inputStream = new FileInputStream("permissions.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString).append("\n");
                    break;
                }

                inputStream.close();
                ret = stringBuilder.toString();                
            }
        }
        catch (Exception e) {
            
        }

        return ret;
	}
	
}
