package net.atos.pamm.endpoint;

import akka.actor.ActorRef;
import akka.actor.Props;
import com.fasterxml.jackson.databind.JsonNode;
import net.atos.pamm.infrastructure.sse.actor.SSEActor;
import net.atos.pamm.infrastructure.sse.actor.UserEventSource;
import play.Logger;
import play.libs.Akka;
import play.libs.EventSource;
import play.mvc.Controller;
import play.mvc.Result;

public class SSEEndpoint extends Controller
{
    private static final Logger.ALogger logger = Logger.of(SSEEndpoint.class);
    final ActorRef sseActorRef = Akka.system().actorOf(Props.create(SSEActor.class));

    public SSEEndpoint()
    {

    }

    // POST {path}/util.sse-request
    public Result createSSEExampleRequest()
    {
        JsonNode jsonRequest = request().body().asJson();
        sseActorRef.tell(jsonRequest, null);
        return ok(jsonRequest);
    }

    // GET {path}/util.sse-connection/:username
    public Result createSSEConnection(String username)
    {
        final UserEventSource userEventSource = new UserEventSource();
        return ok(EventSource.whenConnected(eventSource ->
                {   userEventSource.setEventSource(eventSource);
                    userEventSource.setUsername(username);
                    sseActorRef.tell(userEventSource, null);
                }  ));
    }

}
