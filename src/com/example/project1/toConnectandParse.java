package com.example.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class toConnectandParse {
	public static String getInternetData(String zipcode) throws IOException, 
	URISyntaxException {
		BufferedReader in = null;
		String html = null;
		try{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet("http://m.wund.com/cgi-bin/findweather/getForecast?brand=mobile&query="+ zipcode);
			
			HttpResponse response = client.execute(request);
			
			try {
				response = client.execute(request);
			} catch (IOException e) {
				System.err.println("IOexception called");
			} finally {
				System.out.println("response returned");
			}
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = null;
			String lineSeperator = System.getProperty("line.separator");
			
			while ((line=in.readLine()) !=null){
				sb.append(line + lineSeperator);
			}
			in.close();
			html = sb.toString();
			return html;
		} finally{
			if (in != null){
				try{
					in.close();
					return html;
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
