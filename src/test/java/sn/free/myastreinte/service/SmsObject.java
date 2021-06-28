/*
package sn.free.myastreinte.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

@Service
@Transactional
public class SmsObject {
    private String DOMAIN;
    private String PORT;
    private String PATH_SMS;
    private String PATH_BALANCE;
    public final String QUALITE_STANDARD = "XXX";
    public final String QUALITE_PRO = "FR";
    public final Integer INSTANTANE = 1;
    public final Integer DIFFERE = 2;
    public final String SIMULATION = "simu";
    public final String REEL = "real";

    private String _user_login;
    private String _api_key;
    private String _sms_text;

    private ArrayList<String> _sms_recipients = new ArrayList<String>();
    private ArrayList<String> _recipients_first_names = new ArrayList<String>();
    private ArrayList<String> _recipients_last_names = new ArrayList<String>();
    private ArrayList<String> _sms_fields_1 = new ArrayList<String>();
    private ArrayList<String> _sms_fields_2 = new ArrayList<String>();
    private ArrayList<String> _sms_fields_3 = new ArrayList<String>();

    private Integer _sms_mode;

    private String _sms_type;

    private int _sms_d;
    private int _sms_m;
    private int _sms_h;
    private int _sms_i;
    private int _sms_y;

    private String _sms_sender;
    private String _request_mode;
    private String _sms_ticket;


    public SmsObject() {
        super();
    }

    public String sendSms() {
        String msgStr;
        HashMap<String, String> smsData = new HashMap<String, String>();
        smsData.put("user_login", _user_login);
        smsData.put("api_key", _api_key);
        smsData.put("sms_text", _sms_text);
        smsData.put("sms_recipients", createImplode(",", _sms_recipients));
        smsData.put("recipients_first_names",
            createImplode(",", _recipients_first_names));
        smsData.put("recipients_last_names",
            createImplode(",", _recipients_last_names));
        smsData.put("sms_fields_1", createImplode(",", _sms_fields_1));
        smsData.put("sms_fields_2", createImplode(",", _sms_fields_2));
        smsData.put("sms_fields_3", createImplode(",", _sms_fields_3));
        smsData.put("sms_mode", _sms_mode.toString());
        smsData.put("sms_type", _sms_type);
        smsData.put("sms_sender", _sms_sender);
        smsData.put("request_mode", _request_mode);

        if (_sms_mode == DIFFERE) {
            smsData.put("sms_d", String.valueOf(_sms_d));
            smsData.put("sms_m", String.valueOf(_sms_m));
            smsData.put("sms_h", String.valueOf(_sms_h));
            smsData.put("sms_i", String.valueOf(_sms_i));
            smsData.put("sms_y", String.valueOf(_sms_y));
        }
        try {
            String res = (this.myHttpRequest("http://services/SendNotifications.aspx?_wadl", "SendNotifications.aspx?_wad", "8040", smsData))
                .trim();
            String eCode = substring(res, "<error_code>", "</error_code>");
            if (eCode.equals("000"))
                msgStr = "SMS sending successfuly.";
            else
                msgStr = "SMS sending failed! error code: "+ eCode + " = " + ErrorCode.getErrStr(eCode) + ".";
        } catch (Exception e) {
            e.printStackTrace();
            msgStr = "Unable to get response from server!!!";
        }
        return msgStr + " ";

    }

    public String getBalance() {
        HashMap<String, String> balanceData = new HashMap<>();
        balanceData.put("adeh", _user_login);
        balanceData.put("http://192.168.41.186:8040/services/SendNotifications.aspx?_wadl", _api_key);
        String res;
        try {
            res = myHttpRequest( DOMAIN, PATH_BALANCE, PORT, balanceData).trim();
//			System.out.println(res);
            res = "The balance is:\tquality standard = " +
                substring(res, "type=\"XXX\">", "</balance></octopush") + "\n\t\tquality pro \t = " +
                substring(res, "type=\"FR\">", "</balance>  <balance type=") ;
        } catch (Exception e) {
//			e.printStackTrace();
            res = "Unable to get response from server!!!";
        }
        return res;
    }

    public static String substring(String mainStr, String beforeStr, String afterStr){
        int beginIndex, endIndex;
        if (!mainStr.isEmpty()){
            if (mainStr.indexOf(beforeStr)>0 && !beforeStr.isEmpty())
                beginIndex = mainStr.indexOf(beforeStr) + beforeStr.length();
            else
                beginIndex = 0;
            if (mainStr.indexOf(afterStr)>0 && !afterStr.isEmpty())
                endIndex = mainStr.indexOf(afterStr);
            else
                endIndex = mainStr.length()-1;
            return mainStr.substring(beginIndex, endIndex);
        }
        else
            return "No string!";
    }

    public static <T> String createImplode(String glue, ArrayList<T> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        Iterator<T> iter = list.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(iter.next());
        while (iter.hasNext()) {
            sb.append(glue).append(iter.next());
        }
        return sb.toString();
    }

    public String myHttpRequest(String domain, String path, String port,
                                HashMap<String, String> myMap) throws Exception {
        URL myUrl;
        String strRequest = "";
        if (myMap.size() < 2)
            return "No params";

        for (String hashKey : myMap.keySet())
            strRequest += "&"
                + hashKey
                + "="
                + URLEncoder.encode((myMap.get(hashKey) == null ? ""
                : myMap.get(hashKey)), "UTF-8");
        strRequest = strRequest.substring(1);
//		System.out.println("\nthe request string: " + strRequest);

        myUrl = new URL(domain + ":" + port + path + "?" + strRequest);
        HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
        con.setReadTimeout(0);
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
//		System.out.println("Sending 'GET' request to URL : " + myUrl);
        if (responseCode != 200)
            System.out.println("Response: <error>, Code <" + responseCode + ">");
        BufferedReader reader = new BufferedReader(new InputStreamReader(
            con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }

    public void setDOMAIN(String dOMAIN) {
        DOMAIN = dOMAIN;
    }

    public void setPORT(String pORT) {
        PORT = pORT;
    }

    public void setPATH_SMS(String pATH_SMS) {
        PATH_SMS = pATH_SMS;
    }

    public void setPATH_BALANCE(String pATH_BALANCE) {
        PATH_BALANCE = pATH_BALANCE;
    }

    public void set_user_login(String _user_login) {
        this._user_login = _user_login;
    }

    public void set_api_key(String _api_key) {
        this._api_key = _api_key;
    }

    public void set_sms_text(String _sms_text) {
        this._sms_text = _sms_text;
    }

    public void set_sms_recipients(ArrayList<String> _sms_recipients) {
        this._sms_recipients = _sms_recipients;
    }

    public void set_recipients_first_names(ArrayList<String> _recipients_first_names) {
        this._recipients_first_names = _recipients_first_names;
    }

    public void set_recipients_last_names(ArrayList<String> _recipients_last_names) {
        this._recipients_last_names = _recipients_last_names;
    }

    public void set_sms_fields_1(ArrayList<String> _sms_fields_1) {
        this._sms_fields_1 = _sms_fields_1;
    }

    public void set_sms_fields_2(ArrayList<String> _sms_fields_2) {
        this._sms_fields_2 = _sms_fields_2;
    }

    public void set_sms_fields_3(ArrayList<String> _sms_fields_3) {
        this._sms_fields_3 = _sms_fields_3;
    }

    public void set_sms_mode(Integer _sms_mode) {
        this._sms_mode = _sms_mode;
    }

    public void set_sms_type(String _sms_type) {
        this._sms_type = _sms_type;
    }

    public void set_sms_d(int _sms_d) {
        this._sms_d = _sms_d;
    }

    public void set_sms_m(int _sms_m) {
        this._sms_m = _sms_m;
    }

    public void set_sms_h(int _sms_h) {
        this._sms_h = _sms_h;
    }

    public void set_sms_i(int _sms_i) {
        this._sms_i = _sms_i;
    }

    public void set_sms_y(int _sms_y) {
        this._sms_y = _sms_y;
    }

    public void set_sms_sender(String _sms_sender) {
        this._sms_sender = _sms_sender;
    }

    public void set_request_mode(String _request_mode) {
        this._request_mode = _request_mode;
    }

    public void set_sms_ticket(String _sms_ticket) {
        this._sms_ticket = _sms_ticket;
    }



    public static void main(String[] args) throws Exception {
        SmsObject sms = new SmsObject();

//*************************  Configuration  ***********************
        sms.setDOMAIN(ConfigFile.getDOMAIN());
        sms.setPORT(ConfigFile.getPORT());
        sms.setPATH_SMS(ConfigFile.getPATH_SMS());
        sms.setPATH_BALANCE(ConfigFile.getPATH_BALANCE());
        sms.set_user_login(ConfigFile.get_user_login());
        sms.set_api_key(ConfigFile.get_api_key());
        sms.set_sms_text(ConfigFile.get_sms_text());

        sms.set_sms_recipients(new ArrayList<String>(ConfigFile.get_sms_recipients()));
        sms.set_recipients_first_names(new ArrayList<String>(ConfigFile.get_recipients_first_names()));
        sms.set_recipients_last_names(new ArrayList<String>(ConfigFile.get_recipients_last_names()));
        sms.set_sms_fields_1(new ArrayList<String>(ConfigFile.get_sms_fields_1()));
        sms.set_sms_fields_2(new ArrayList<String>(ConfigFile.get_sms_fields_2()));
        sms.set_sms_fields_3(new ArrayList<String>(ConfigFile.get_sms_fields_3()));

        sms.set_sms_mode(ConfigFile.getInstantane());	// INSTANTANE or change to DIFFERE for delay sms-mode sending

        sms.set_sms_type(ConfigFile.getQualitePro());	// QUALITE_STANDARD or change to QUALITE_PRO

        //Change to correct value of DAY_OF_MONTH, MONTH, HOUR_OF_DAY etc'
        sms.set_sms_d(ConfigFile.get_sms_d());	//	DAY_OF_MONTH
        sms.set_sms_m(ConfigFile.get_sms_m());	//	MONTH
        sms.set_sms_h(ConfigFile.get_sms_h());	//	HOUR_OF_DAY;
        sms.set_sms_i(ConfigFile.get_sms_i());	//	MINUTE;
        sms.set_sms_y(ConfigFile.get_sms_y());	//	YEAR;

        sms.set_sms_sender(ConfigFile.get_sms_sender());
        sms.set_request_mode(ConfigFile.getReel());
        sms.set_sms_ticket(ConfigFile.get_sms_ticket());

        Scanner in = new Scanner(System.in);
        int i;
        do {
            System.out.println("Press 1 to get balance ");
            System.out.println("Press 2 to send SMS ");
            System.out.println("Press 0 to exit ");
            System.out.print("Press here: ");
            i = in.nextInt();
            switch (i) {
                case 1:
                    System.out.println(sms.getBalance()+"\n");break;
                case 2:
                    System.out.println(sms.sendSms()+"\n");break;
                case 0:
                    break;
                default:
                    System.out.println("Enter only number 1/2/0");break;
            }
        } while (i != 0);
        in.close();
    }
}
*/
