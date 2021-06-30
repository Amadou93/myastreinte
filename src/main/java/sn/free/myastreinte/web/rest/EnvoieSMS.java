

package sn.free.myastreinte.web.rest;


import org.springframework.boot.configurationprocessor.json.JSONException;


import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
@RequestMapping("/api")
@RestController
public class EnvoieSMS {

public static void main(String[] args){}

    private boolean sendSms(String messageTosend, String sender, String receiverMsisdn, int type){
        //ignoring ssl certificatee
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON) ;
        JSONObject sendSmsRequest = new JSONObject();
        try {
            sendSmsRequest.put("Envoie sms", messageTosend);
            sendSmsRequest.put("221761995807", receiverMsisdn);
            sendSmsRequest.put("free", sender);
            sendSmsRequest.put("1", type);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("REQUEST BODY :" + sendSmsRequest.toString());
        HttpEntity<String> request = new HttpEntity<String>(sendSmsRequest.toString(), headers);
        String sendSmsResultStr = "";
        try{
            sendSmsResultStr = restTemplate.postForObject( "https://192.168.41.165:9040/services" +"/SendNotifications.aspx", request, String.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        System.out.println("RESPONSE BODY :" + sendSmsResultStr);
        return sendSmsResultStr.equalsIgnoreCase("0");
    }





}


