package net.atos.pamm.domain.project.service;

import play.Logger;
import play.libs.Json;
import net.atos.pamm.domain.project.model.Project;
import net.atos.pamm.domain.project.repository.ProjectRepository;
import net.atos.pamm.domain.ServiceResult;

import javax.inject.Inject;
import java.util.List;

public class ListProjectsOperation {
    private static final Logger.ALogger LOG = Logger.of(ListProjectsOperation.class);

    private final ProjectRepository repository;

    @Inject
    public ListProjectsOperation(ProjectRepository repository) {
        this.repository = repository;
    }

    public ServiceResult execute() {
        final List<Project> projects = repository.getAll();
        return new ServiceResult(Json.toJson(projects));
    }
}
