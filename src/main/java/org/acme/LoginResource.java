package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@Path("/login")
public class LoginResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean Login() {
        Boolean LoginSuccesfull = false;
        // TO DO : Actual Logic
        return LoginSuccesfull;
    }
}