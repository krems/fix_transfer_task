package edu.mipt.rest;

import edu.mipt.model.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.UUID;

@Path(TransactionResource.TRACNSACTIONS)
public class TransactionResource {
    public static final String TRACNSACTIONS = "/tracnsactions";

    @POST
    public Response transfer(final @QueryParam("from") UUID fromAccount,
                             final @QueryParam("to") UUID toAccount,
                             final @QueryParam("amount") BigDecimal amount) {
        Account from = AccountResource.accounts.getOrDefault(fromAccount, null);
        if (from == null) {
            throw new NotFoundException("No account with id " + fromAccount);
        }
        Account to = AccountResource.accounts.getOrDefault(toAccount, null);
        if (to == null) {
            throw new NotFoundException("No account with id " + toAccount);
        }
        from.withdraw(amount);
        to.topup(amount);
        return Response.ok().build();
    }
}
