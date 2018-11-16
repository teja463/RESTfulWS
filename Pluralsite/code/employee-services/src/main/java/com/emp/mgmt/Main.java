package com.emp.mgmt;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.emp.mgmt.config.ApplicationConfig;
import com.emp.mgmt.dao.EmployeeDao;
import com.emp.mgmt.dao.EmployeeDaoImpl;
import com.emp.mgmt.svc.EmployeeService;
import com.emp.mgmt.svc.EmployeeServiceImpl;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8090/empmgt/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
    	final EmployeeService svc = new EmployeeServiceImpl();
    	final EmployeeDao dao = new EmployeeDaoImpl();
    	
        final ResourceConfig rc = new ApplicationConfig(dao, svc);
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}
