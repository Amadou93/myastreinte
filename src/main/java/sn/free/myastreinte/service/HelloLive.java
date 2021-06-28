package sn.free.myastreinte.service;

import org.asteriskjava.live.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HelloLive {
    private AsteriskServer asteriskServer;

    public HelloLive()
    {
        asteriskServer = new DefaultAsteriskServer("172.20.10.12", "manager", "secret");
    }

    public void run() throws ManagerCommunicationException
    {

        for (AsteriskChannel asteriskChannel : asteriskServer.getChannels())
        {
            System.out.println(asteriskChannel);
        }

        for (AsteriskQueue asteriskQueue : asteriskServer.getQueues())
        {
            System.out.println(asteriskQueue);
        }

        for (MeetMeRoom meetMeRoom : asteriskServer.getMeetMeRooms())
        {
            System.out.println(meetMeRoom);
        }

    }

    public static void main(String[] args) throws Exception
    {
      /*  HelloLive helloLive = new HelloLive();
        helloLive.run();*/
    }
}
