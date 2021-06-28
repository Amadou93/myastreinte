package sn.free.myastreinte.service;

import org.asteriskjava.live.*;
import org.asteriskjava.live.internal.AsteriskAgentImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
@Service
@Transactional
public class HelloLiveEverything implements AsteriskServerListener, PropertyChangeListener {
    private AsteriskServer asteriskServer;

    public  HelloLiveEverything()
    {
        asteriskServer = new DefaultAsteriskServer("172.20.10.12", "manager", "secret");
    }

    public void run() throws ManagerCommunicationException
    {
        // listen for new events
        asteriskServer.addAsteriskServerListener(this);

        // add property change listeners to existing objects
        for (AsteriskChannel asteriskChannel : asteriskServer.getChannels())
        {
            System.out.println(asteriskChannel);
            asteriskChannel.addPropertyChangeListener(this);
        }

        for (AsteriskQueue asteriskQueue : asteriskServer.getQueues())
        {
            System.out.println(asteriskQueue);
            for (AsteriskQueueEntry asteriskChannel : asteriskQueue.getEntries())
            {
                asteriskChannel.addPropertyChangeListener(this);
            }
        }

        for (MeetMeRoom meetMeRoom : asteriskServer.getMeetMeRooms())
        {
            System.out.println(meetMeRoom);
            for (MeetMeUser user : meetMeRoom.getUsers())
            {
                user.addPropertyChangeListener(this);
            }
        }
    }

    public void onNewAsteriskChannel(AsteriskChannel channel)
    {
        System.out.println(channel);
        channel.addPropertyChangeListener(this);
    }

    public void onNewMeetMeUser(MeetMeUser user)
    {
        System.out.println(user);
        user.addPropertyChangeListener(this);
    }

    @Override
    public void onNewAgent(AsteriskAgent asteriskAgent) {

    }

    public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    {
        System.out.println(propertyChangeEvent);
    }

    public static void main(String[] args) throws Exception
    {/*
        HelloLiveEverything helloLiveEverything = new HelloLiveEverything();
        helloLiveEverything.run();*/
    }


    public void onNewAgent(AsteriskAgentImpl arg0) {
        System.out.println(" AgentID :" + arg0.getAgentId());
        System.out.println(" AgentName :" + arg0.getName());
    }

    @Override
    public void onNewQueueEntry(AsteriskQueueEntry arg0) {
        System.out.println(" Date : "+arg0.getDateJoined());
        System.out.println(" Position :" + arg0.getPosition());
    }
}
