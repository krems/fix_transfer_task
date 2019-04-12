package edu.mipt;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class RestServerLauncher {
    public static final String BASE_URI = "http://localhost:8080";

    public static void main(final String[] args) {
        ResourceConfig rc = new ResourceConfig().packages("edu.mipt");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        System.out.println(String.format("Jersey app started with WADL available at "
            + "%s/application.wadl\nHit enter to stop it...", BASE_URI));
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.shutdownNow();
        }
    }
}
