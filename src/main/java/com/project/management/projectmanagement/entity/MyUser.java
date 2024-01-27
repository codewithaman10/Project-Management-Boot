package com.project.management.projectmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS", schema = "DEV")
public class MyUser {

    @Id
    @Column(name = "USER_ID")
    private Long userId;
    
    @Column(name = "username")
    private String userName;
    
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String roles;
    
    @Column(name = "enabled")
    private boolean enabled;
    
    @Column(name = "email_id")
    private String emailId;

    public MyUser() {
    }

    public MyUser(Long userId, String userName, String password, String roles, boolean enabled, String emailId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "MyUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", enabled=" + enabled +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
