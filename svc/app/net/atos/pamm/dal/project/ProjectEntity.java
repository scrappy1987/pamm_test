package net.atos.pamm.dal.project;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Table(name = "project")
@Entity
@NamedQueries({
        @NamedQuery(name = ProjectEntity.FIND_ALL, query = "SELECT p FROM ProjectEntity p")
})
public class ProjectEntity {
    public static final String FIND_ALL = "ProjectEntity.FIND_ALL";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "project_code", nullable = false)
    private String projectCode;

    @Column(name = "client", nullable = false)
    private String client;

    @Column(name = "summary", nullable = false)
    private String summary;


    @Column(name = "status", nullable = false)
    private String status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<ProjectUserEntity> members;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProjectUserEntity> getMembers() {
        return members;
    }

    public void setMembers(List<ProjectUserEntity> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(projectCode, that.projectCode) &&
                Objects.equals(client, that.client) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(status, that.status) &&
                Objects.equals(members, that.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, projectCode, client, summary, status, members);
    }
}
