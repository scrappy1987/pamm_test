package net.atos.pamm.dal.user;

import net.atos.pamm.dal.EntityManagerProvider;
import net.atos.pamm.dal.project.ProjectMapper;
import net.atos.pamm.dal.project.ProjectUserEntity;
import net.atos.pamm.domain.RepositoryObjectFactory;
import net.atos.pamm.domain.project.model.Project;
import net.atos.pamm.domain.user.model.User;
import net.atos.pamm.domain.user.repository.UserRepository;
import play.Logger;

import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserJpaRepository implements UserRepository {
    private static final Logger.ALogger LOG = Logger.of(UserJpaRepository.class);

    private final RepositoryObjectFactory repositoryObjectFactory;
    private final EntityManagerProvider emProvider;
    private final ProjectMapper projectMapper;

    @Inject
    public UserJpaRepository(RepositoryObjectFactory repositoryObjectFactory,
                             EntityManagerProvider emProvider,
                             ProjectMapper projectMapper) {
        this.repositoryObjectFactory = repositoryObjectFactory;
        this.emProvider = emProvider;
        this.projectMapper = projectMapper;
    }

    @Override
    public User set(User user) {
        final UserEntity newUserEntity = repositoryObjectFactory.createEntity(user, UserEntity.class);
        emProvider.getEntityManager().persist(newUserEntity);

        final Integer id = (Integer) emProvider.getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(newUserEntity);
        user.setId(id);

        return repositoryObjectFactory.createBusinessObject(newUserEntity, User.class);
    }

    @Override
    public User get(Integer userId) {
        final UserEntity userEntity = emProvider.getEntityManager().find(UserEntity.class, userId);
        return repositoryObjectFactory.createBusinessObject(userEntity, User.class);
    }

    @Override
    public List<User> getAll() {
        final List<UserEntity> userEntities = emProvider.getEntityManager().createNamedQuery(UserEntity.FIND_ALL).getResultList();
        final List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            users.add(repositoryObjectFactory.createBusinessObject(userEntity, User.class));
        }
        return users;
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Not yet imeplemented");

    }

    @Override
    public void remove(User user) {
        final UserEntity userToRemove = emProvider.getEntityManager().getReference(UserEntity.class, user.getId());
        emProvider.getEntityManager().remove(userToRemove);
    }


    @Override
    public User findUserByEmail(String email) {
        final Query query = emProvider.getEntityManager().createNamedQuery(UserEntity.FIND_BY_EMAIL);
        query.setParameter(UserEntity.EMAIL_PARAM, email);
        final List<UserEntity> result = query.getResultList();

        if (result.size() > 1) {
            LOG.error("Duplicate user found for " + email + ".  Expected to be unique");
            throw new NonUniqueResultException();
        } else if (result.size() == 0) {
            LOG.debug("Cannot find user with " + email);
            return null;
        } else {
            return repositoryObjectFactory.createBusinessObject(result.get(0), User.class);
        }
    }

    @Override
    public List<Project> findProjectsForUser(Integer userId) {
        final Query query = emProvider.getEntityManager().createNamedQuery(ProjectUserEntity.FIND_ALL_FOR_USER);
        query.setParameter(ProjectUserEntity.USER_ID_PARAM, userId);
        final List<ProjectUserEntity> userProjects = query.getResultList();

        final List<Project> projects = new ArrayList<>();
        for (ProjectUserEntity userProject : userProjects) {
            projects.add(projectMapper.projectToBusinessObject(userProject.getProject()));
        }
        return projects;
    }

    @Override
    public void activate(User user) {
        final UserEntity userEntity = emProvider.getEntityManager().find(UserEntity.class, user.getId());

        userEntity.setActivated(true);
        userEntity.setActivationDate(new Date());

        emProvider.getEntityManager().merge(userEntity);
    }
}

