package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
@Path("/route")
public class RouteResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Register() {
        return "Route!"; }
}