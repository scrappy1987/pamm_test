import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

public class ClientApiImpl implements ClientApi {

    /*
       Example of HTTP Service call implementation:

       @Override
       public List<Car> getListOfCars() {
            Response response = client
                .target(serviceURI)
                .path("resourceName")
                .queryParam("id", id)
                .request(MediaType.APPLICATION_JSON)
                .header("Content-type", "application/json")
                .get();
            checkStatus(response);
            return response.readEntity(new GenericType<List<Cars>>(){});
        }

     */
}
