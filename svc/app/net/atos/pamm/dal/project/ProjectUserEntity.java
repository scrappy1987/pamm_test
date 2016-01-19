package net.atos.pamm.dal.project;

import net.atos.pamm.dal.user.UserEntity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = ProjectUserEntity.FIND_ALL_FOR_USER, query = "SELECT p FROM ProjectUserEntity p WHERE p.userId = :userId")
})
@Table(name = "project_user")
public class ProjectUserEntity implements Serializable {
    public static final String FIND_ALL_FOR_USER = "ProjectUserEntity.FIND_ALL_FOR_USER";
    public static final String USER_ID_PARAM = "userId";

    @EmbeddedId
    ProjectUserId id;

    @Column(name = "user_id", nullable = true)
    private Integer userId;

    @Column(name = "role", nullable = true)
    private String role;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email", insertable = false, updatable = false)
    private UserEntity user;

    public ProjectUserId getId() {
        return id;
    }

    public void setId(ProjectUserId id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectUserEntity that = (ProjectUserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(role, that.role) &&
                Objects.equals(project, that.project) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, role, project, user);
    }
}
