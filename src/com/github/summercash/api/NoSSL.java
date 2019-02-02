package com.github.summercash.api;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



public class NoSSL {
	public String sendPost(final String request, final String postData) throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException  {
	    String result = null;
	    SSLContext sslContext = SSLContext.getInstance("SSL");

	    // set up a TrustManager that trusts everything
	    sslContext.init(null, new TrustManager[] { new X509TrustManager() {
	                public X509Certificate[] getAcceptedIssuers() {
	                        System.out.println("getAcceptedIssuers =============");
	                        return null;
	                }

	                public void checkClientTrusted(X509Certificate[] certs,
	                                String authType) {
	                        System.out.println("checkClientTrusted =============");
	                }

	                public void checkServerTrusted(X509Certificate[] certs,
	                                String authType) {
	                        System.out.println("checkServerTrusted =============");
	                }
	    } }, new SecureRandom());

//        @SuppressWarnings("deprecation")
        
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory( new SSLConnectionSocketFactory(sslContext)).build();
	    HttpPost httpPost = new HttpPost(request);
	    
	    StringEntity reqentity = new StringEntity(postData, ContentType.APPLICATION_FORM_URLENCODED);
	   
	    httpPost.setEntity(reqentity);
	    CloseableHttpResponse response = httpclient.execute(httpPost);
	    try {
	        HttpEntity entity = response.getEntity();
	        result = EntityUtils.toString(entity);
	        EntityUtils.consume(entity);
	    } finally {
	        response.close();
	    }
	    return result;

	}
	
	public static void main(String[] args) {
		String url = "https://108.41.124.60:8080/twirp/transaction.Transaction/NewTransaction/";
		String req = new GeneralRequest("0x040028d536d5351e83fbbec320c194629ace", "0x0400bb6659813faa43c57e8799c9e9806b2b", 5.0).getData();
		NoSSL fd = new NoSSL();
		String res;
		try {
			res = fd.sendPost(url, req);
			System.out.println(res.toString());
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
