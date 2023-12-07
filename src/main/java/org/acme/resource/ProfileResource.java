package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.User;
import org.jboss.logging.Logger;

@Path("/profile")
public class ProfileResource {

    private static final Logger LOG = Logger.getLogger(ProfileResource.class);
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Register() {
        return "Profile!";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Register(User user) {
        int OperationCode = 0;
        LOG.info(user.toString());
        // TO DO: Actual Logic

        // Assuming OperationCode indicates success (you can modify this condition based on your logic)
        if (user != null) {
            // Return a 201 Created response
            return Response.status(Response.Status.CREATED).build();
        } else {
            // You can handle other cases and return different status codes if needed
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}