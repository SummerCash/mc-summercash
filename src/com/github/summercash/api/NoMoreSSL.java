package com.github.summercash.api;

class NoMoreSSL {
/////////////////
// Create SSL Client
/////////////////
public static void main(String[] args) {
    CloseableHttpClient httpclient = null;
    HttpHost target = new HttpHost("localhost", 8080, "https");
    
    SSLContext sslcontext = SSLContexts.createSystemDefault();
    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
            sslcontext, new String[] { "TLSv1", "SSLv3" }, null,
            SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    
    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("http", PlainConnectionSocketFactory.INSTANCE)
            .register("https", sslConnectionSocketFactory)
            .build();
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    
    httpclient = HttpClients.custom()
    .setSSLSocketFactory(sslConnectionSocketFactory)
    .setConnectionManager(cm)
    .build();
    
    /////////////////
    // Send POST
    /////////////////
    
    HttpPost httppost = new HttpPost("/route here");
    ByteArrayEntity postDataEntity = new ByteArrayEntity(postData.getBytes());
    httpPost.setEntity(postDataEntity);
    CloseableHttpResponse response = httpclient.execute(target, httpPost);
    
    /////////////////
    // Get RESPONSE
    /////////////////
    
    try {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
    } finally {
            response.close();
    }
    }
    
}
