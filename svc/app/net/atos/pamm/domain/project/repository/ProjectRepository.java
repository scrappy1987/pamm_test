package net.atos.pamm.domain.project.repository;

import net.atos.pamm.domain.project.model.Project;

import java.util.List;

public interface ProjectRepository {
    Project get(Integer projectId);

    List<Project> getAll();

    Project update(Project project);

    void remove(Project project);

    Project set(Project project);
}
