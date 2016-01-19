package net.atos.pamm.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import net.atos.pamm.domain.user.service.UserService;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Optional;

public class LoginEndpoint extends Controller {
    private static final Logger.ALogger LOG = Logger.of(UserEndpoint.class);

    public static final String BASIC = "Basic ";

    private final UserService userService;

    @Inject
    public LoginEndpoint(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public Result login() {
        final Optional<String> authHeader = Optional.ofNullable(request().getHeader(AUTHORIZATION));
        if (!authHeader.isPresent()) {
            return unauthorized();
        }

        if (!authHeader.get().startsWith(BASIC)) {
            return badRequest("Incorrect Auth Scheme");
        }

        try {
            final String encodedAuthValue = authHeader.get().substring("Basic ".length());
            final byte[] decoded = Base64.getDecoder().decode(encodedAuthValue);
            final String[] authValue = new String(decoded, "UTF-8").split(":");

            if (authValue.length != 2) {
                return unauthorized();
            } else {
                final String username = authValue[0];
                final String password = authValue[1];

                try {
                    final JsonNode userPrincipal = userService.authenticate(username, password).getResult();

                    if (userPrincipal == null) {
                        return unauthorized();
                    }

                    return ok(userPrincipal);
                } catch (IllegalArgumentException e) {
                    return badRequest(e.getMessage());
                } catch (NonUniqueResultException e) {
                    LOG.error("Login failed because email duplication is found for " + username);
                    return internalServerError();
                }
            }
        } catch (UnsupportedEncodingException e) {
            return badRequest("Malformed Credentials");
        }
    }
}
