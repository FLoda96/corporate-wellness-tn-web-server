package org.acme.resource;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
//import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.common.annotation.Blocking;
//import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.ForgotPassword;
import org.acme.model.ProfileAuth;
import org.jboss.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Timestamp;
import java.util.List;

import static org.acme.service.RandomStringGenerator.generateRandomString;

@Path("/forgotpassword")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ForgotPasswordResource {

    @Inject
    EntityManager entityManager;

    @Inject
    Mailer mailer;
    private static final Logger LOG = Logger.getLogger(ForgotPasswordResource.class);

    /*@GET
    @Path("/all")
    public List<ForgotPassword> getAllForgotPasswords() {
        return entityManager.createNamedQuery("ForgotPassword.findAll", ForgotPassword.class)
                .getResultList();
    }*/

    // Should be a GET but then it doesn't permit me to insert a body
    @PUT
    @Path("/checkvalidity")
    public Response checkValidity(ForgotPassword forgotPassword) {
        List<ForgotPassword> dbForgotPasswords = null;
            dbForgotPasswords = entityManager.createNamedQuery("ForgotPassword.findByEmail", ForgotPassword.class)
                    .setParameter("email", forgotPassword.getEmail()).getResultList();

            if (dbForgotPasswords != null) {
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                long oneDayInMillis = 24 * 60 * 60 * 1000;
                for (ForgotPassword result : dbForgotPasswords) {
                    if ((currentTimestamp.getTime() - result.getTimestampRequest().getTime()) < oneDayInMillis) {
                        if (BCrypt.checkpw(forgotPassword.getUniqueCode(), result.getUniqueCode())) {
                            return Response.ok("Go Ahead").status(200).build();
                        }
                    }
                }
            } else {
                return Response.ok("No forgot password request for this user").status(400).build();
            }

        return Response.ok("No forgot password request for this user").status(400).build();
    }


    public Void sendEmailForgotPassword(String EmailAddress, String Code) {
        String resetLink = "http://www.moveappapp.com/password?email=" + EmailAddress + "%26" + Code ;
        String emailBody = "To reset your password open the app through this link <a href='" + resetLink + "'>here</a>.";
        String EmailSubject = "Reset Password";
        mailer.send(Mail.withText(EmailAddress, EmailSubject, emailBody));
        return null;
    }

    @POST
    @Transactional
    public Response createForgotPassword(ForgotPassword forgotPassword) {
        // Checks
        if (forgotPassword.getEmail() == null) {
            throw new WebApplicationException("Missing obligatory parameters", 400);
        }
        try {
            ProfileAuth auth = null;
            auth = entityManager.createNamedQuery("ProfileAuth.findByEmail", ProfileAuth.class)
                    .setParameter("email", forgotPassword.getEmail())
                    .getSingleResult();
        } catch (NoResultException nre) {
            return Response.ok("The selected user do not exist").status(400).build();
        }
        // Generate random code
        String Code = generateRandomString(10);
        LOG.info("Code : " + Code);
        forgotPassword.setUniqueCode(BCrypt.hashpw(Code, BCrypt.gensalt()));
        sendEmailForgotPassword(forgotPassword.getEmail(), Code);
        LOG.info("Persisting");
        try {
            entityManager.persist(forgotPassword);
            return Response.ok(forgotPassword).status(201).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }

    /*@PUT
    @Transactional
    public Response updateForgotPassword(ForgotPassword forgotPassword) {
        try {
            entityManager.merge(forgotPassword);
            return Response.ok(forgotPassword).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }*/

    /*@PUT
    @Transactional
    public Response updateForgotPassword(ForgotPassword forgotPassword) {
        try {
            entityManager.merge(forgotPassword);
            return Response.ok(forgotPassword).status(200).build();
        } catch (PersistenceException pe) {
            return Response.ok("The operation failed").status(500).build();
        }
    }*/

    /*@DELETE
    @Path("/{forgotpassword_id}")
    @Transactional
    public Response deleteForgotPassword(@PathParam("forgotpassword_id") Integer forgotpasswordId) {
        ForgotPassword forgotPassword = entityManager.find(ForgotPassword.class, forgotpasswordId);
        if (forgotPassword != null) {
            entityManager.remove(forgotPassword);
            return Response.ok().status(204).build();
        } else {
            return Response.ok("ForgotPassword not found").status(404).build();
        }
    }*/
}
