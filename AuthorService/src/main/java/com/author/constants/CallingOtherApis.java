package com.author.constants;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;

import com.author.entitys.BookDetails;

public class CallingOtherApis {

	public static String getUrlCall(String url) {
		CloseableHttpResponse httpResponse = null;
		StringBuffer response = new StringBuffer();
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			httpResponse = httpClient.execute(httpGet);
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			reader.close();
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.toString();
	}

	public static String postUrlCall(String url, BookDetails bookDetails) {
		CloseableHttpResponse httpResponse = null;
		StringBuffer response = new StringBuffer();
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/json");
			HttpEntity<BookDetails> entity = new HttpEntity<BookDetails>(bookDetails);
			httpPost.setEntity((org.apache.http.HttpEntity) entity);
//			HttpPost postUrl = new HttpPost(url);
			httpResponse = httpClient.execute(httpPost);
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			reader.close();
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.toString();
	}
}
