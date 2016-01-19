package net.atos.pamm.infrastructure.module;

import com.google.inject.AbstractModule;
import net.atos.pamm.dal.project.ProjectJpaRepository;
import net.atos.pamm.dal.user.UserJpaRepository;
import net.atos.pamm.domain.project.repository.ProjectRepository;
import net.atos.pamm.domain.user.repository.UserRepository;

public class RepositoryModule extends AbstractModule {
    protected void configure() {

        bind(ProjectRepository.class).to(ProjectJpaRepository.class);
        bind(UserRepository.class).to(UserJpaRepository.class);
    }
}
