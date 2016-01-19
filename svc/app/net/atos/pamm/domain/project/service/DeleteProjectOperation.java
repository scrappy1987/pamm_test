package net.atos.pamm.domain.project.service;

import net.atos.pamm.domain.project.model.Project;
import net.atos.pamm.domain.project.repository.ProjectRepository;
import play.Logger;
import net.atos.pamm.domain.ServiceResult;

import javax.inject.Inject;

public class DeleteProjectOperation {
    private static final Logger.ALogger LOG = Logger.of(CreateProjectOperation.class);

    private final ProjectRepository repository;

    @Inject
    public DeleteProjectOperation(ProjectRepository repository) {
        this.repository = repository;
    }

    public ServiceResult execute(Integer id) {
        final Project project = repository.get(id);

        repository.remove(project);

        return new ServiceResult("Deleted Project with id" + id);
    }
}
