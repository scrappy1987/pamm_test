package net.atos.pamm.infrastructure.sse.connection;

import play.libs.EventSource;

/**
 * Created by a585493 on 17/11/2015.
 */
public interface ConnectionManager {
    void addConnection(String username, EventSource eventSource);
    EventSource getConnection(String username);
    void removeConnection(String username);
}
