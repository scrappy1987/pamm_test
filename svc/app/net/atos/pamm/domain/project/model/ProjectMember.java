package net.atos.pamm.domain.project.model;

import net.atos.pamm.domain.SessionStatus;

import java.util.Objects;

public class ProjectMember {
    public enum Role {OWNER, MEMBER};

    private Integer projectId;
    private Integer userId;
    private String email;
    private String forename;
    private String surname;
    private Role role;

    private SessionStatus sessionStatus;

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMember that = (ProjectMember) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(email, that.email) &&
                Objects.equals(forename, that.forename) &&
                Objects.equals(surname, that.surname) &&
                role == that.role &&
                sessionStatus == that.sessionStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, userId, email, forename, surname, role, sessionStatus);
    }
}
