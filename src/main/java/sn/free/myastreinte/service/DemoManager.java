package sn.free.myastreinte.service;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class DemoManager {

         private ManagerConnection managerConnection;

        public DemoManager() throws IOException {
            ManagerConnectionFactory factory = new ManagerConnectionFactory("172.20.10.12", "manager", "secret");
            this.managerConnection = factory.createManagerConnection();
        }

        public void call(final String user, final String phoneNumber) throws IOException, AuthenticationFailedException, TimeoutException {
            OriginateAction originateAction;
            ManagerResponse originateResponse;

            originateAction = new OriginateAction();
            originateAction.setChannel("SIP/" + user);
            originateAction.setContext("CSI");
            originateAction.setExten("1");
            originateAction.setPriority(new Integer(1));
            originateAction.setTimeout(new Integer(30000));
            originateAction.setVariable("customernum", "9001");

            // connect to Asterisk and log in
            managerConnection.login();

            // send the originate action and wait for a maximum of 30 seconds for Asterisk to send a reply
            originateResponse = managerConnection.sendAction(originateAction, 30000);

            System.out.println(originateResponse.getMessage());
            System.out.println(originateResponse.getResponse());
            System.out.println(originateResponse.getUniqueId());

            // and finally log off and disconnect
            managerConnection.logoff();
        }

        public static void main(String[] args) throws AuthenticationFailedException, TimeoutException, IOException {
             DemoManager demoManager = new DemoManager();

            System.out.println("CALLING ASSANE");
            demoManager.call("DEH", "9001");
            System.out.println("END CALLING DEH");



        }
}
