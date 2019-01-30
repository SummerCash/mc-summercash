package com.github.summercash.api;
import org.json.simple.JSONObject;
// import;

class Accounts {
    // private class GeneralRequest {
    //     JSONObject request;

    //     GeneralRequest(String address, String privateKey) {
    //         request = new JSONObject();
    //         request.put("privateKey", privateKey);
    //         request.put("address", address);
    //     }
    // }

    public String CallMethod(String method, String address, String privateKey) {
        return "";
        byte[] out = "{\"username\":\"root\",\"password\":\"password\"}" .getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

        try {

            HttpPost request = new HttpPost("http://yoururl");
            StringEntity params =new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            //handle response here...

        } catch (Exception ex) {

            //handle exception here

        } finally {
            //Deprecated
            //httpClient.getConnectionManager().shutdown(); 
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