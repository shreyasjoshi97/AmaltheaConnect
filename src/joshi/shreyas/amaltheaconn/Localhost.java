package joshi.shreyas.amaltheaconn;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Localhost {
	public Localhost()
	{
		try {
			Socket socket = new Socket("localhost", 5000);
			String contents = "|" + readFile() + "~";
			
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			outputStreamWriter.write(contents);	
			outputStreamWriter.flush();
			outputStreamWriter.close();
			
			InputStream inputStream = socket.getInputStream();
			BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream));
			
			StringBuilder sb = new StringBuilder();
			String output = "";
			
			while(((output = buff.readLine()) != null))
			{
				sb.append(output);
			}
			buff.close();
			output = sb.toString();
			//outputStreamWriter.flush();
			//outputStreamWriter.close();
			socket.close();
			//outputStreamWriter.close();
			
			System.out.println(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
