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
public class GetProjectTestCase extends EndpointTestCase
{
    @Override protected void test()
    {
        Http.RequestBuilder postRequest = new Http.RequestBuilder().method("POST").bodyJson(getJsonRequest())
                .uri("/project");
        Result postResult = route(postRequest);
        String projects = Helpers.contentAsString(postResult);
        System.out.println("Projects returned POST " + projects);

        Http.RequestBuilder getRequest = new Http.RequestBuilder().method("GET").uri("/project");
        Result getResult = route(getRequest);
        projects = Helpers.contentAsString(getResult);
        System.out.println("Projects returned GET " + projects);
    }

    private JsonNode getJsonRequest()
    {
        return Json.newObject().put("title", "Scott Project 2").put("summary", "Scott Project 2 Summary")
                .put("info", "Scott Project 2 info").put("status", "In Progress");
    }
}
