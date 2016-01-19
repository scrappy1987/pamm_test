package net.atos.pamm.endpoint;

import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Result;
import net.atos.pamm.domain.project.service.ProjectService;

import javax.inject.Inject;

public class ProjectsEndpoint extends ResourceEndpoint {
    private static final Logger.ALogger LOG = Logger.of(ProjectsEndpoint.class);

    private final ProjectService projectService;

    @Inject
    public ProjectsEndpoint(ProjectService projectService) {
        this.projectService = projectService;
    }

    // GET {path}/{resource}
    @Transactional
    public Result list() {
        return response(projectService.list());
    }

    // GET {path}/{resource}/:id
    @Transactional
    public Result find(final Integer id) {
        return response(projectService.find(id));
    }

    // POST {path}/{resource}

    @Transactional
    public Result create() {
        return response(projectService.create(request().body().asJson()));
    }

    // PUT {path}/{resource}
    @Transactional
    public Result update() {
        return response(projectService.update(request().body().asJson()));
    }

    // DELETE {path}/{resource}/:id
    @Transactional
    public Result delete(final Integer projectId) {
        return response(projectService.delete(projectId));
    }

}
