package net.atos.pamm.infrastructure.sse.connection;


import play.Logger;
import play.libs.EventSource;

import javax.inject.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Singleton
public class SSEConnectionManager implements ConnectionManager {

    private static final Logger.ALogger logger = Logger.of(SSEConnectionManager.class);

    private Map<String, EventSource> SSEConnections = new ConcurrentHashMap<>();

    public SSEConnectionManager()
    {
    }

    @Override
    public void addConnection(String username, EventSource eventSource)
    {
        SSEConnections.put(username, eventSource);
    }

    @Override
    public EventSource getConnection(String username)
    {
        return SSEConnections.get(username);
    }

    @Override
    public void removeConnection(String username)
    {
        SSEConnections.remove(username);
    }

    public Map<String, EventSource> getSSEConnectionManager() {
        return SSEConnections;
    }

}
