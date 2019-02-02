package com.github.summercash.api;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * This example demonstrates how to ignore certificate errors.
 * These errors include self signed certificate errors and hostname verification errors.
 */
public class TestNoSSL {
    
        public static void main(String[] args)  {

        try (CloseableHttpClient httpclient = createAcceptSelfSignedCertificateClient()) {
            GeneralRequest genReq = new GeneralRequest("0x040028d536d5351e83fbbec320c194629ace", "0x0400bb6659813faa43c57e8799c9e9806b2b", 5.0);
            String genReqData = genReq.getData();
            
            StringEntity entity = new StringEntity(genReqData, ContentType.APPLICATION_FORM_URLENCODED);
            
            
             HttpPost postreq = new HttpPost("https://108.41.124.60:8080/twirp/transaction.Transaction/NewTransaction");
//            HttpPost postreq = new HttpPost("https://localhost:8080/twirp/transaction.Transaction/NewTransaction");
            System.out.println("Executing request " + postreq.getRequestLine());
            postreq.setEntity(entity);
            postreq.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            HttpResponse res = httpclient.execute(postreq);
            
            String content = EntityUtils.toString(entity);
            System.out.println(content);
            System.out.println("----------------------------------------");
            System.out.println(res);
            
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CloseableHttpClient createAcceptSelfSignedCertificateClient()
            throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        // use the TrustSelfSignedStrategy to allow Self Signed Certificates
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        // we can optionally disable hostname verification. 
        // if you don't want to further weaken the security, you don't have to include this.
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();
        
        // create an SSL Socket Factory to use the SSLContext with the trust self signed certificate strategy
        // and allow all hosts verifier.
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);
        
        // finally create the HttpClient using HttpClient factory methods and assign the ssl socket factory
        return HttpClients
                .custom()
                .setSSLSocketFactory(connectionFactory)
                .build();
    }
}