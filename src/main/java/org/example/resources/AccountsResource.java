package org.example.resources;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.example.domain.Account;
import org.example.repositories.AccountDB;
import org.example.util.KeyGenerator;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static java.time.LocalDateTime.now;

@Path("accounts")
public class AccountsResource {

    @Context
    UriInfo uriInfo;

    @Inject
    AccountDB accountDB;

    @Inject
    KeyGenerator keyGenerator;

    @GET
    @Produces({APPLICATION_JSON})
    public List<Account> search(@QueryParam("q") String q) {
        return q != null ?
                this.accountDB.getAll().stream()
                        .filter(s -> s.getGebruikersnaam().toLowerCase().contains(q.toLowerCase()))
                        .toList() :
                this.accountDB.getAll();
    }

    @POST
    @Produces({APPLICATION_JSON})
    @Consumes({APPLICATION_JSON})
    public Account login(Account account) {
        try {
            String gebruikersnaam = account.getGebruikersnaam();
            String wachtwoord = account.getWachtwoord();
            Account dbaccount = accountDB.findByUsernameAndPassword(gebruikersnaam, wachtwoord);
            String jwt = issueToken(gebruikersnaam);
            dbaccount.setJwt(jwt);
            System.out.println(dbaccount);
            accountDB.update(dbaccount);
            return dbaccount;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotAuthorizedException("User " + account + " is not authorized.");
        }
    }


    private String issueToken(String username) {
        Key password = keyGenerator.generateKey();
        return Jwts.builder()
                .setSubject(username)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, password)
                .compact();
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


}
