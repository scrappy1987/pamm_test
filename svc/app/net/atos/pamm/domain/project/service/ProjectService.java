package net.atos.pamm.domain.project.service;

import com.fasterxml.jackson.databind.JsonNode;
import net.atos.pamm.domain.ServiceResult;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProjectService {
    private final CreateProjectOperation createProjectOperation;
    private final ListProjectsOperation listProjectsOperation;
    private final UpdateProjectOperation updateProjectOperation;
    private final DeleteProjectOperation deleteProjectOperation;
    private final FindProjectOperation findProjectOperation;

    @Inject
    public ProjectService(CreateProjectOperation createProjectOperation,
                          ListProjectsOperation listProjectsOperation,
                          UpdateProjectOperation updateProjectOperation,
                          DeleteProjectOperation deleteProjectOperation,
                          FindProjectOperation findProjectOperation) {

        this.createProjectOperation = createProjectOperation;
        this.listProjectsOperation = listProjectsOperation;
        this.updateProjectOperation = updateProjectOperation;
        this.deleteProjectOperation = deleteProjectOperation;
        this.findProjectOperation = findProjectOperation;

    }

    public ServiceResult list() {
        return listProjectsOperation.execute();
    }

    public ServiceResult create(JsonNode jsonResource) {
        return createProjectOperation.execute(jsonResource);
    }

    public ServiceResult update(JsonNode jsonResource) {
        return updateProjectOperation.execute(jsonResource);
    }

    public ServiceResult delete(Integer projectId) {
        return deleteProjectOperation.execute(projectId);
    }

    public ServiceResult find(Integer projectId) {
        return findProjectOperation.execute(projectId);
    }
}
