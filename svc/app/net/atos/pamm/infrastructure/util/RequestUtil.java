package net.atos.pamm.infrastructure.util;

import com.google.inject.Singleton;
import play.mvc.Http;

@Singleton
public class RequestUtil {
    public String getBaseUrl() {
        if (Http.Context.current().request().secure()) {
            return "https://" + Http.Context.current().request().host();
        } else {
            return "http://" + Http.Context.current().request().host();
        }
    }
}
