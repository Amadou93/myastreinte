package sn.free.myastreinte.service;

import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.ManagerEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
@Service
@Transactional
public class InteractAsterisk {
    private ManagerConnection managerConnection;

    public  InteractAsterisk() throws IOException
    {
        ManagerConnectionFactory factory = new ManagerConnectionFactory("172.20.10.12", "manager", "secret");
        this.managerConnection = factory.createManagerConnection();
    }

    public void run() throws IOException, AuthenticationFailedException,
        TimeoutException, InterruptedException
    {
        // register for events
        managerConnection.addEventListener((ManagerEventListener) this);

        // connect to Asterisk and log in
        managerConnection.login();
        // request channel state
        managerConnection.sendAction(new StatusAction());
        // wait 10 seconds for events to come in
        Thread.sleep(10000);
        // and finally log off and disconnect
        managerConnection.logoff();
    }



    public void onManagerEvent(ManagerEvent arg0) {
        System.out.println(arg0.getDateReceived().getTime());
    }


    public static void main(String[] args) throws Exception
    {
       /* InteractAsterisk helloEvents;
        helloEvents = new InteractAsterisk();
        helloEvents.run();*/
    }
}
