package net.atos.pamm.domain.user.service;

import com.fasterxml.jackson.databind.JsonNode;
import net.atos.pamm.domain.ServiceResult;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {
    private final ListUsersOperation listUsersOperation;
    private final UpdateUserOperation updateUserOperation;
    private final FindUserByIdOperation findUserByIdOperation;
    private final AuthenticateOperation authenticateOperation;
    private final FindUserProjectsOperation findUserProjectsOperation;
    private final RegisterUserOperation registerUserOperation;
    private final ActivateUserOperation activateUserOperation;

    @Inject
    public UserService(ListUsersOperation listUsersOperation,
                       UpdateUserOperation updateUserOperation,
                       FindUserByIdOperation findUserByIdOperation,
                       AuthenticateOperation authenticateOperation,
                       FindUserProjectsOperation findUserProjectsOperation,
                       RegisterUserOperation registerUserOperation,
                       ActivateUserOperation activateUserOperation) {

        this.listUsersOperation = listUsersOperation;
        this.updateUserOperation = updateUserOperation;
        this.findUserByIdOperation = findUserByIdOperation;
        this.authenticateOperation = authenticateOperation;
        this.findUserProjectsOperation = findUserProjectsOperation;
        this.registerUserOperation = registerUserOperation;
        this.activateUserOperation = activateUserOperation;
    }

    public ServiceResult list() {
        return listUsersOperation.execute();
    }

    public ServiceResult update(JsonNode jsonResource) {
        return updateUserOperation.execute(jsonResource);
    }

    public ServiceResult find(Integer userId) {
        return findUserByIdOperation.execute(userId);
    }

    public ServiceResult authenticate(String username, String password) {
        return authenticateOperation.execute(username, password);
    }

    public ServiceResult findProjects(final Integer userId) {
        return findUserProjectsOperation.execute(userId);
    }

    public ServiceResult register(final JsonNode jsonResource) {
        return registerUserOperation.execute(jsonResource);
    }

    public ServiceResult activate(final Integer userId, final String activationCode) {
        return activateUserOperation.execute(userId, activationCode);
    }
}
