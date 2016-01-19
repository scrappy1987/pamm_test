package controllers.resource.play;

import controllers.EndpointTest;
import controllers.EndpointTestCase;
import org.junit.Test;

import static play.test.Helpers.inMemoryDatabase;

public class ProjectsEndpointTest extends EndpointTest
{
    @Test public void testCreateProjects()
    {
        EndpointTestCase testCase = new CreateProjectTestCase();

        runEndpointTest(testCase, inMemoryDatabase());
    }

    @Test public void testGetProjects()
    {
        EndpointTestCase testCase = new GetProjectTestCase();

        runEndpointTest(testCase, inMemoryDatabase());
    }
}
