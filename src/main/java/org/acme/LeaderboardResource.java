package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
@Path("/leaderboard")
public class LeaderboardResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Register() {
        return "LeaderBoard!"; }
}