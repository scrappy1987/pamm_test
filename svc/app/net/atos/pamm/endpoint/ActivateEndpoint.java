package net.atos.pamm.endpoint;

import net.atos.pamm.domain.ServiceResult;
import net.atos.pamm.domain.user.service.UserService;
import net.atos.pamm.infrastructure.util.RequestUtil;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class ActivateEndpoint extends Controller {
    private static final Logger.ALogger LOG = Logger.of(UserEndpoint.class);
    private final UserService userService;
    private final RequestUtil requestUtil;

    @Inject
    public ActivateEndpoint(UserService userService, RequestUtil requestUtil) {
        this.userService = userService;
        this.requestUtil = requestUtil;
    }

    @Transactional
    public Result activate(final Integer userId, final String activateString) {
        final ServiceResult serviceResult = userService.activate(userId, activateString);
        if (serviceResult.getStatus().equals(ServiceResult.Status.OP_ERROR)) {
            return badRequest();
        } else {
            return redirect(requestUtil.getBaseUrl() + "/#/user/activated");
        }
    }
}
