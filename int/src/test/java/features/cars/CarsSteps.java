package features.cars;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import features.Util;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CarsSteps {
    @Inject
    private Util util;

    @And("^a list of vehicles parked in the site is known$")
    public void a_list_of_vehicles_parked_in_the_site_is_known() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^a request is made to retrieve all parked vehicles$")
    public void a_request_is_made_to_retrieve_all_parked_vehicles() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^a list with all parked vehicles is retrieved$")
    public void a_list_with_all_parked_vehicles_is_retrieved() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
