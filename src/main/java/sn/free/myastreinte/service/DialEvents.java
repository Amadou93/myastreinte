package sn.free.myastreinte.service;

import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.DialEvent;
import org.asteriskjava.manager.event.ManagerEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
@Service
@Transactional
public class DialEvents implements ManagerEventListener {

private ManagerConnection managerConnection;
    private DemoManager demoManager = new DemoManager();

    public DialEvents() throws IOException
    {
        ManagerConnectionFactory factory = new ManagerConnectionFactory("172.20.10.12", "manager", "secret");

        this.managerConnection = factory.createManagerConnection();
    }

    public void run() throws IOException, AuthenticationFailedException,
        TimeoutException, InterruptedException
    {
        // register for events
        managerConnection.addEventListener( this);

        // connect to Asterisk and log in
        managerConnection.login();

        // request channel state
        managerConnection.sendAction(new StatusAction());

        // wait 10 seconds for events to come in
        Thread.sleep(60000);

//		System.out.println("CALLING ASSANE");
//      demoManager.call("100", "Assane");
//      System.out.println("END CALLING ASSANE");
        // and finally log off and disconnect
        managerConnection.logoff();
    }

    public void onManagerEvent(ManagerEvent event)
    {
        // just print received events
        if(event instanceof DialEvent) {
            DialEvent de = (DialEvent) event;
            System.out.println(de.getCallerIdNum() + " a destination de " + de.getDestination());
        }
    }

    public static void main(String[] args) throws Exception
    {
        /*DialEvents helloEvents;

        helloEvents = new DialEvents();
        helloEvents.run();*/

    }
}
