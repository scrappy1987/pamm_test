package net.atos.pamm.domain.user.service;

import net.atos.pamm.domain.user.model.User;
import net.atos.pamm.domain.user.repository.UserRepository;
import play.Logger;
import play.libs.Json;
import net.atos.pamm.domain.ServiceResult;

import javax.inject.Inject;
import java.util.List;

public class ListUsersOperation {
    private static final Logger.ALogger LOG = Logger.of(ListUsersOperation.class);

    private final UserRepository repository;

    @Inject
    public ListUsersOperation(UserRepository repository) {
        this.repository = repository;
    }

    public ServiceResult execute() {
        final List<User> users = repository.getAll();
        return new ServiceResult(Json.toJson(users));
    }
}
