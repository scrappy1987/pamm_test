package net.atos.pamm.infrastructure.sse.actor;

import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.JsonNode;
import net.atos.pamm.infrastructure.sse.connection.SSEConnectionManager;
import play.libs.EventSource;

import static play.libs.EventSource.Event.event;

public class SSEActor extends UntypedActor {

    private SSEConnectionManager connectionManager;


    public SSEActor()
    {
        connectionManager = new SSEConnectionManager();
    }


    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof JsonNode)
        {
            JsonNode jsonNode = (JsonNode) message;
            String username =  jsonNode.get("username").asText();
            if(connectionManager.getSSEConnectionManager().containsKey(username)) {
                EventSource evt = connectionManager.getConnection(username);
                evt.send(event(jsonNode));
            }
        }
        if (message instanceof UserEventSource)
        {
            UserEventSource userEventSource = (UserEventSource) message;
            connectionManager.addConnection(userEventSource.getUsername(), userEventSource.getEventSource());
        }
    }
}
