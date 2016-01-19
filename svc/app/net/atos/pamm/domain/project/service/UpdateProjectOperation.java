package net.atos.pamm.domain.project.service;

import com.fasterxml.jackson.databind.JsonNode;
import net.atos.pamm.domain.ServiceResult;
import net.atos.pamm.domain.project.repository.ProjectRepository;
import net.atos.pamm.domain.project.model.Project;
import net.atos.pamm.infrastructure.mail.EmailService;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;

public class UpdateProjectOperation {
    private static final Logger.ALogger LOG = Logger.of(UpdateProjectOperation.class);

    private final ProjectRepository projectRepository;
    private final EmailService emailService;


    @Inject
    public UpdateProjectOperation(ProjectRepository projectRepository,
                                  EmailService emailService) {
        this.projectRepository = projectRepository;
        this.emailService = emailService;
    }

    public ServiceResult execute(JsonNode jsonRequest) {
        final Project project = Json.fromJson(jsonRequest, Project.class);
        final Project updatedProject = projectRepository.update(project);

        // TODO email new project members


        return new ServiceResult(Json.toJson(updatedProject));
    }
}
