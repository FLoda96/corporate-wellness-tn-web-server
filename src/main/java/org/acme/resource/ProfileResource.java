package org.acme.resource;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Profile;
import org.jboss.logging.Logger;

@Path("/profile")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProfileResource {
    @Inject
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(ProfileResource.class);
    @GET
    @Path("/all")
    public List<Profile> GetAllUsers() {
        return entityManager.createNamedQuery("Profile.findAll", Profile.class)
                .getResultList();
    }

    @GET
    @Path("/{email}")
    public Profile GetUserbyEmail(String email) {
        try {
            return entityManager.createNamedQuery("Profile.findByEmail", Profile.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @POST
    @Transactional
    public Response Register(Profile user) {
        LOG.info(user.toString());
        Profile profile = null;
        try {
             profile = entityManager.createNamedQuery("Profile.findByEmail", Profile.class)
                    .setParameter("email", user.getEmail()).getSingleResult();
        } catch (NoResultException nre) {
            // Nothing happens, not finding anything it's a normal situation
        }
        if (profile != null) {
            return Response.ok("Email already present").status(400).build();
        }
        if (user.getEmail() == null) {
            throw new WebApplicationException("Invalid user.", 422);
        }
        try {
            entityManager.persist(user);
            return Response.ok(user).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @PUT
    @Transactional
    public Response Update(Profile user) {
        Profile profile = null;
        try {
            profile = entityManager.createNamedQuery("Profile.findByEmail", Profile.class)
                    .setParameter("email", user.getEmail()).getSingleResult();
        } catch (NoResultException nre) {
            return Response.ok("The selected user do not exist").status(400).build();
        }
        Profile.updateProfile(profile, user);
        try {
            entityManager.merge(profile);
            return Response.ok(profile).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{user_id}")
    public Response Delete (String user_id) {
        LOG.info("user_id : " + user_id);
        Profile profile = null;
        try {
            profile = entityManager.createNamedQuery("Profile.findById", Profile.class)
                    .setParameter("user_id", user_id).getSingleResult();
        } catch (NoResultException nre) {
            return Response.ok("The selected user do not exist").status(400).build();
        }
        try {
            entityManager.remove(profile);
            return Response.ok("profile removed").status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }
}