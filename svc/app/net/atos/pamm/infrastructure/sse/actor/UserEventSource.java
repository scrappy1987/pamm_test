package net.atos.pamm.infrastructure.sse.actor;

import play.libs.EventSource;

/**
 * Created by a585493 on 18/11/2015.
 */
public class UserEventSource {
    private String username;
    private EventSource eventSource;

    public UserEventSource()
    {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EventSource getEventSource() {
        return eventSource;
    }

    public void setEventSource(EventSource eventSource) {
        this.eventSource = eventSource;
    }
}
