package net.atos.pamm.dal.user;

import net.atos.pamm.domain.user.model.User.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Table(name = "user")
@Entity
@NamedQueries({
        @NamedQuery(name = UserEntity.FIND_ALL, query = "SELECT u FROM UserEntity u"),
        @NamedQuery(name = UserEntity.FIND_BY_EMAIL, query = "SELECT u FROM UserEntity u WHERE u.email = :email")
})
public class UserEntity implements Serializable {
    public static final String FIND_ALL = "UserEntity.FIND_ALL";
    public static final String FIND_BY_EMAIL = "UserEntity.FIND_BY_EMAIL";
    public static final String EMAIL_PARAM = "email";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "forename", nullable = false)
    private String forename;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "base_site", nullable = false)
    private String baseSite;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "activated", nullable = false)
    private Boolean activated;

    @Column(name = "activation_key", nullable = false)
    private String activationKey;

    @Column(name = "activation_date", nullable = false)
    private Date activationDate;

    @Column(name = "role", nullable = false)
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBaseSite() {
        return baseSite;
    }

    public void setBaseSite(String baseSite) {
        this.baseSite = baseSite;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(forename, that.forename) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(password, that.password) &&
                Objects.equals(jobTitle, that.jobTitle) &&
                Objects.equals(baseSite, that.baseSite) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(activated, that.activated) &&
                Objects.equals(activationKey, that.activationKey) &&
                Objects.equals(activationDate, that.activationDate) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, forename, surname, password, jobTitle, baseSite, phone, activated, activationKey, activationDate, role);
    }
}
