package com.github.summercash.api;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


import javax.net.ssl.X509TrustManager;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;



@SuppressWarnings("deprecation")
public class NoCerts {
	
	class AlwaysTrustHostnameVerifier implements X509TrustManager 
	{
	    public void checkClientTrusted( X509Certificate[] x509 , String authType ) throws CertificateException { /* nothing */ }
	    public void checkServerTrusted( X509Certificate[] x509 , String authType ) throws CertificateException { /* nothing */ }
	    public X509Certificate[] getAcceptedIssuers() { return null; }      
	}
	
	public void sendPost(final String request, final String urlParameters) throws IOException {

	    URL url = new URL(request); 
	    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();           
	    connection.setDoOutput(true);
	    connection.setDoInput(true);
	    connection.setInstanceFollowRedirects(false);
	    connection.setHostnameVerifier(new AlwaysTrustHostnameVerifier());
	    connection.setRequestMethod("POST"); 
	    connection.setRequestProperty("Content-Type", "application/json"); 
	    connection.setRequestProperty("charset", "utf-8");
	    connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
	    connection.setUseCaches(false);

	    DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
	    wr.writeBytes(urlParameters);
	    wr.flush();
	    wr.close();
	    connection.disconnect();        

	}
	
	public static void main(String[] args) {
		NoCerts nocerts = new NoCerts();
		String data = new GeneralRequest("0x040028d536d5351e83fbbec320c194629ace", "0x0400bb6659813faa43c57e8799c9e9806b2b", 5.0).getData();
		String url = "https://108.41.124.60:8080/twirp/transaction.Transaction/NewTransaction/";
		try {
			nocerts.sendPost(url, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
