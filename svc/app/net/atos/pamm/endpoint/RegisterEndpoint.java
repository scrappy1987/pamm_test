package net.atos.pamm.endpoint;

import net.atos.pamm.domain.ServiceResult;
import net.atos.pamm.domain.user.service.UserService;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class RegisterEndpoint extends Controller {
    private final UserService userService;

    @Inject
    public RegisterEndpoint(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public Result register() {
        final ServiceResult serviceResult = userService.register(request().body().asJson());

        if (serviceResult.getStatus() == ServiceResult.Status.SUCCESS) {
            return ok(serviceResult.getResult());
        } else if (serviceResult.getStatus() == ServiceResult.Status.OP_ERROR) {
            return badRequest("User Exists");
        } else {
            return internalServerError(serviceResult.getResult().asText());
        }
    }
}
