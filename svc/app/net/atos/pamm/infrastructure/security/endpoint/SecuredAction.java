package net.atos.pamm.infrastructure.security.endpoint;

import com.google.inject.Inject;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import net.atos.pamm.infrastructure.security.authentication.Authenticator;
import net.atos.pamm.infrastructure.security.authentication.Principal;
import net.atos.pamm.infrastructure.security.authentication.TokenStatus;

public class SecuredAction extends Action.Simple {

    private final Authenticator authenticator;

    @Inject
    public SecuredAction(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public F.Promise<Result> call(Http.Context ctx) throws Throwable {
        final String token = getTokenFromHeader(ctx);

        if (token == null) {
            return F.Promise.pure(Results.unauthorized("No Credentials"));
        } else {
            final Principal principal = authenticator.validateToken(token);

            if (principal.getTokenStatus() == TokenStatus.INVALID) {
                return F.Promise.pure(Results.unauthorized("Invalid Credentials"));
            } else if (principal.getTokenStatus() == TokenStatus.EXPIRED) {
                return F.Promise.pure(Results.unauthorized("Token Expired"));
            } else {
                ctx.args.put(Principal.class.getName(), principal);
                return delegate.call(ctx);
            }
        }
    }

    private String getTokenFromHeader(Http.Context ctx) {
        final String[] authTokenHeaderValues = ctx.request().headers().get(Http.HeaderNames.AUTHORIZATION);
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0].substring("Bearer ".length());
        } else {
            return null;
        }
    }
}
