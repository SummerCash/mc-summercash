package com.github.summercash.api;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

class Transaction {
    private class GeneralRequest {
        private JSONObject reqData;
        
        public String getData() {
        	return reqData.toJSONString();
        }
        
        GeneralRequest(String address, String address2, double amount) {
        	reqData = new JSONObject();

        	reqData.put("nonce", "");
        	reqData.put("address", address);
        	reqData.put("address2", address2);
        	reqData.put("amount", amount);
        	reqData.put("payload", "despacito ree");
        }
    }

    public String NewTransaction(String to, String from, double amount) {
    	String requestData = new GeneralRequest(to, from, amount).getData();
        StringEntity entity = new StringEntity(requestData, ContentType.APPLICATION_FORM_URLENCODED);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("https://108.41.124.60:8080/twirp/transaction.Transaction/NewTransaction/");
        request.setEntity(entity);

        HttpResponse response;
		try {
			response = httpClient.execute(request);
			System.out.println(response.toString());
			System.out.println(response.getStatusLine().getStatusCode());
			return "";
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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