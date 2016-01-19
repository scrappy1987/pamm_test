package net.atos.pamm.dal.project;

import net.atos.pamm.dal.EntityManagerProvider;
import net.atos.pamm.domain.SessionStatus;
import net.atos.pamm.domain.project.repository.ProjectRepository;
import net.atos.pamm.domain.project.model.Project;
import net.atos.pamm.domain.project.model.ProjectMember;
import play.Logger;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

public class ProjectJpaRepository implements ProjectRepository {
    private static final Logger.ALogger LOG = Logger.of(ProjectJpaRepository.class);

    private final EntityManagerProvider emProvider;
    private final ProjectMapper projectMapper;

    @Inject
    public ProjectJpaRepository(EntityManagerProvider emProvider,
                                ProjectMapper projectMapper) {
        this.emProvider = emProvider;
        this.projectMapper = projectMapper;
    }

    @Override
    public Project set(Project project) {
        final ProjectEntity newProjectEntity = projectMapper.projectToEntity(project);

        emProvider.getEntityManager().persist(newProjectEntity);
        project.setId((Integer) emProvider.getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(newProjectEntity));
        newProjectEntity.setMembers(projectMapper.projectUsersToEntityList(project));
        emProvider.getEntityManager().merge(newProjectEntity);
        emProvider.getEntityManager().flush();
        emProvider.getEntityManager().refresh(newProjectEntity);

        return projectMapper.projectToBusinessObject(newProjectEntity);
    }

    @Override
    public Project get(Integer projectId) {
        return projectMapper.projectToBusinessObject(emProvider.getEntityManager().find(ProjectEntity.class, projectId));
    }

    @Override
    public List<Project> getAll() {
        final List<ProjectEntity> projectEntities = emProvider.getEntityManager().createNamedQuery(ProjectEntity.FIND_ALL).getResultList();
        return projectMapper.projectsToBusinessObjectList(projectEntities);
    }

    @Override
    public Project update(Project project) {
        final ProjectEntity projectEntityToUpdate = emProvider.getEntityManager().find(ProjectEntity.class, project.getId());

        projectEntityToUpdate.setTitle(project.getTitle());
        projectEntityToUpdate.setProjectCode(project.getProjectCode());
        projectEntityToUpdate.setClient(project.getClient());
        projectEntityToUpdate.setSummary(project.getSummary());
        projectEntityToUpdate.setStatus(project.getStatus());

        for (ProjectMember projectMember : project.getMembers()) {
            if (projectMember.getSessionStatus() == SessionStatus.NEW) {
                final ProjectUserEntity newUserEntity = projectMapper.projectUserToEntity(projectMember, project);
                emProvider.getEntityManager().persist(newUserEntity);
                emProvider.getEntityManager().flush();
                emProvider.getEntityManager().refresh(newUserEntity);

            } else if (projectMember.getSessionStatus() == SessionStatus.REMOVED) {
                for (Iterator<ProjectUserEntity> it = projectEntityToUpdate.getMembers().iterator(); it.hasNext(); ) {
                    final ProjectUserEntity projectUserEntityToCheck = it.next();
                    if (projectUserEntityToCheck.getId().getUserEmail().equals(projectMember.getEmail())) {
                        it.remove();
                        emProvider.getEntityManager().remove(projectUserEntityToCheck);
                        break;
                    }
                }
            }
        }
        emProvider.getEntityManager().merge(projectEntityToUpdate);
        emProvider.getEntityManager().flush();
        emProvider.getEntityManager().refresh(projectEntityToUpdate);

        return projectMapper.projectToBusinessObject(projectEntityToUpdate);
    }

    @Override
    public void remove(Project project) {
        final ProjectEntity projectToRemove = emProvider.getEntityManager().getReference(ProjectEntity.class, project.getId());
        emProvider.getEntityManager().remove(projectToRemove);
    }
}
