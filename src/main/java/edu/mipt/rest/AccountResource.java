package edu.mipt.rest;

import edu.mipt.model.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Path(AccountResource.ACCOUNTS)
public class AccountResource {
    public static final String ACCOUNTS = "/accounts";

    public static final Map<UUID, Account> accounts = new ConcurrentHashMap<>();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(final @PathParam("id")UUID id) {
        Account account = accounts.getOrDefault(id, Account.EMPTY);
        if (account == Account.EMPTY) {
            throw new NotFoundException("No account with id " + id);
        }
        return account;
    }

    @POST
    public Response createAccount() {
        Account account = new Account(UUID.randomUUID());
        accounts.put(account.id, account);
        URI uri = URI.create(ACCOUNTS + "/" + account.id);
        return Response.created(uri).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteResponse(final @PathParam("id") UUID id) {
        if (accounts.remove(id) != null) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
