package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/register")
public class RegisterResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean IsRegistered() {
        Boolean Registered = false;
        // TO DO : Actual Logic
        return Registered;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response Register() {
        int OperationCode = 0;
        // TO DO: Actual Logic

        // Assuming OperationCode indicates success (you can modify this condition based on your logic)
        if (OperationCode == 0) {
            // Return a 201 Created response
            return Response.status(Response.Status.CREATED).build();
        } else {
            // You can handle other cases and return different status codes if needed
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
