package edu.mipt;

import edu.mipt.model.Account;
import edu.mipt.rest.AccountResource;
import org.glassfish.jersey.client.internal.HttpUrlConnector;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

public class Client {

    public static void main(String[] args) {
        WebTarget accountClient = ClientBuilder.newClient()
            .target(RestServerLauncher.BASE_URI)
            .path(AccountResource.ACCOUNTS);
        Response response = accountClient.request(MediaType.APPLICATION_JSON).post(Entity.json(""));
        System.out.println(response.getStatus());
        URI location = response.getLocation();
        System.out.println(location);
        System.out.println("Sending GET");

        Response getResponse = ClientBuilder.newClient()
            .target(location)
            .request(MediaType.APPLICATION_JSON).get();
        System.out.println(getResponse.getStatus());
        System.out.println(getResponse.readEntity(Account.class));

        System.out.println("DELETING");
        Response delResponse = ClientBuilder.newClient()
            .target(location)
            .request(MediaType.APPLICATION_JSON).delete();
        System.out.println(delResponse.getStatus());

        Response getResponse2 = ClientBuilder.newClient()
            .target(location)
            .request(MediaType.APPLICATION_JSON).get();
        System.out.println(getResponse2.getStatus());
        System.out.println(getResponse2.readEntity(Account.class));
    }

}
