package com.github.summercash.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

class Transaction {

    private static CloseableHttpClient ignoreSSL()
        throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        SSLContext sslContext = SSLContextBuilder
            .create()
            .loadTrustMaterial(new TrustSelfSignedStrategy())
            .build();


        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);
        
        return HttpClients
            .custom()
            .setSSLSocketFactory(connectionFactory)
            .build();
    }


    public String NewTransaction(String to, String from, double amount) {

//        try (CloseableHttpClient httpclient = ignoreSSL()) {
//x
//            String requestData = new GeneralRequest(to, from, amount).getData();
//            StringEntity entity = new StringEntity(requestData, ContentType.APPLICATION_FORM_URLENCODED);
//
//            HttpClient httpClient = HttpClientBuilder.create().build();
//            HttpPost request = new HttpPost("https://108.41.124.60:8080/twirp/transaction.Transaction/NewTransaction/");
//            request.setEntity(entity);
//
//            HttpResponse response;
//            try {
//                response = httpClient.execute(request);
//                System.out.println(response.toString());
//                System.out.println(response.getStatusLine().getStatusCode());
//                return "";
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        
//            // HttpGet httpget = new HttpGet("https://example.com");
//            // System.out.println("Executing request " + httpget.getRequestLine());
//            
//            // httpclient.execute(httpget);
//            // System.out.println("----------------------------------------");
//        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    	
		return "";
    }
    
}




// def GeneralRequest(address, privateKey):
//     obj = {
//         "address": address,
//         "privateKey": privateKey
//     }

//     return obj

// def CallMethod(method, address, privateKey):
//     response = requests.post(main.provider + "/twirp/accounts.Accounts/" + method, data = json.dumps(GeneralRequest(address, privateKey)),
//         headers=common.RequestHeaders, verify=common.RequestShouldVerify) # Send request
//     return common.GetRequestResponse(response) # Return response # Return response