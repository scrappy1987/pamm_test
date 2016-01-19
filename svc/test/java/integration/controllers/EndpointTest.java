package controllers;

import play.Application;
import play.Mode;
import play.inject.guice.GuiceApplicationBuilder;

import java.util.Map;

import static play.test.Helpers.running;

/**
 * Created by a560832 on 20/11/2015.
 */
public abstract class EndpointTest
{
    public void runEndpointTest(EndpointTestCase testCase, Map configuration)
    {
        Application guiceApp = getApplication(configuration);

        running(guiceApp, testCase);
    }

    private Application getApplication(Map configuration) {
        return  new GuiceApplicationBuilder()
                .configure(configuration)
                .in(Mode.TEST)
                .build();
    }
}
