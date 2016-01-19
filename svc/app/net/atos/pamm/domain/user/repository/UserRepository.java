package net.atos.pamm.domain.user.repository;

import net.atos.pamm.domain.project.model.Project;
import net.atos.pamm.domain.user.model.User;

import java.util.List;

public interface UserRepository {
    User findUserByEmail(String email);

    List<Project> findProjectsForUser(Integer userId);

    void activate(User user);

    User set(User user);

    User get(Integer userId);

    List<User> getAll();

    void update(User user);

    void remove(User user);
}
