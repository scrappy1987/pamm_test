package net.atos.pamm.endpoint;

import net.atos.pamm.domain.ServiceResult;
import net.atos.pamm.infrastructure.security.authentication.Principal;
import net.atos.pamm.infrastructure.security.endpoint.SecuredAction;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

@With(SecuredAction.class)
public abstract class ResourceEndpoint extends Controller {
    private static final Logger.ALogger LOG = Logger.of(ResourceEndpoint.class);

    /**
     * Helper method for returning the appropriate HTTP response base on the service result.
     *
     * @param serviceResult
     * @return http response
     */
    protected Result response(final ServiceResult serviceResult) {
        if (serviceResult.getStatus() == ServiceResult.Status.SUCCESS) {
            return ok(serviceResult.getResult());
        } else {
            // TODO check other statuses

            return internalServerError();
        }
    }

    protected Principal getUserPrincipal() {
        return (Principal) ctx().args.get(Principal.class.getName());
    }
}
