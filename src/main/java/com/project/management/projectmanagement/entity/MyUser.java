package com.project.management.projectmanagement.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.io.Serializable;

@Entity
@Table(name = "USERS", schema = "DEV")
@Builder
public class MyUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGenerator")
    @SequenceGenerator(name = "userSeqGenerator", schema = "DEV",  sequenceName = "USERS_SEQ")
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
    
    @Column(name = "username")
    private String userName;
    
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;
    
    @Column(name = "enabled")
    private boolean enabled;
    
    @Column(name = "email_id")
    private String emailId;

    public MyUser() {
    }

    public MyUser(Long userId, String firstName, String lastName, String userName, String password, String roles, boolean enabled, String emailId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
        this.emailId = emailId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", enabled=" + enabled +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
