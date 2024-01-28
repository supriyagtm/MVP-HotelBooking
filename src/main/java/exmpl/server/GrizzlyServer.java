package exmpl.server;

import java.io.IOException;

import java.net.URI;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import exmpl.resource.BookingResource;

public class GrizzlyServer {

private final URI ADDRESS = URI.create("http://127.0.0.1:8080/");
	
	public GrizzlyServer() {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.registerClasses(BookingResource.class);
		GrizzlyHttpServerFactory.createHttpServer(ADDRESS, resourceConfig);
	}
	
	
	public static void main(String[] args) throws IOException {
        @SuppressWarnings("unused")
        GrizzlyServer server = new GrizzlyServer();
	}
}


