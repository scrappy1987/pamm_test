package controllers.resource.play;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.EndpointTestCase;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;

import static play.test.Helpers.route;

/**
 * Created by a560832 on 20/11/2015.
 */
public class CreateProjectTestCase extends EndpointTestCase
{
    @Override protected void test()
    {
        Http.RequestBuilder postRequest = new Http.RequestBuilder().method("POST").bodyJson(getJsonRequest())
            .uri("/project");
        Result postResult = route(postRequest);
        String projects = Helpers.contentAsString(postResult);
        System.out.println("Projects returned POST " + projects);
    }

    private JsonNode getJsonRequest()
    {
        return Json.newObject().put("title", "Scott Project 1").put("summary", "Scott Project 1 Summary")
                .put("info", "Scott Project 1 info").put("status", "In Progress");
    }
}
