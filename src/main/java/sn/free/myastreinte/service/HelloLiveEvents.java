package sn.free.myastreinte.service;

import org.asteriskjava.live.*;
import org.asteriskjava.live.internal.AsteriskAgentImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HelloLiveEvents  implements AsteriskServerListener{
    private AsteriskServer asteriskServer;

    public HelloLiveEvents()
    {
        asteriskServer = new DefaultAsteriskServer("172.20.10.12", "manager", "secret");
    }

    public void run() throws ManagerCommunicationException
    {
        asteriskServer.addAsteriskServerListener(this);
    }

    public void onNewAsteriskChannel(AsteriskChannel channel)
    {
        System.out.println(channel);
    }

    public void onNewMeetMeUser(MeetMeUser user)
    {
        System.out.println(user);
    }

    @Override
    public void onNewAgent(AsteriskAgent asteriskAgent) {

    }

    public static void main(String[] args) throws Exception
    {
        HelloLiveEvents helloLiveEvents = new HelloLiveEvents();
        helloLiveEvents.run();
    }


    public void onNewAgent(AsteriskAgentImpl arg0) {
        System.out.println(arg0.getAgentId()+ " : "+ arg0.getName() + "\n");
    }

@Override
    public void onNewQueueEntry(AsteriskQueueEntry arg0) {
        System.out.println(arg0.getChannelName() + " " +arg0.getDateJoined() + "\n");
    }
}
