package com.example.ldd.duongldph04549_lab3.json_parser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpHandler {
	public static final String TAG = HttpHandler.class.getSimpleName();
	
	public HttpHandler(){}
	
	public String makeServiceCall(String reqUrl){
		String respone = null;
		try {
			URL url = new URL(reqUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			InputStream inputStream = new BufferedInputStream(conn.getInputStream());
			respone = convertStreamToString(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respone;
	}

	private String convertStreamToString(InputStream inputStream) {
		BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while((line = read.readLine()) !=null){
				sb.append(line).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return sb.toString();
	}
}
