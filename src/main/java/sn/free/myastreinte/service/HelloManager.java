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
@Transactional
public class HelloManager {
    private ManagerConnection managerConnection;

    public HelloManager() throws IOException
    {
        ManagerConnectionFactory factory = new ManagerConnectionFactory("172.20.10.12", "manager", "secret");
        this.managerConnection = factory.createManagerConnection();
    }

    public void run() throws IOException, AuthenticationFailedException, TimeoutException
    {
        OriginateAction originateAction;
        ManagerResponse originateResponse;

        originateAction = new OriginateAction();
        originateAction.setChannel("SIP/Deh");
        originateAction.setContext("default");
        originateAction.setExten("9OO1");
        originateAction.setPriority(new Integer(1));
        originateAction.setTimeout(new Integer(30000));

        // connect to Asterisk and log in
        managerConnection.login();

        // send the originate action and wait for a maximum of 30 seconds for Asterisk
        // to send a reply
        originateResponse = managerConnection.sendAction(originateAction, 30000);

        // print out whether the originate succeeded or not
        System.out.println(originateResponse.getResponse());

        // and finally log off and disconnect
        managerConnection.logoff();
    }

    public static void main(String[] args) throws Exception
    {
        HelloManager helloManager;
        helloManager = new HelloManager();
        helloManager.run();
    }
}
