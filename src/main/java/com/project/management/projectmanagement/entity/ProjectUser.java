package com.project.management.projectmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class ProjectUser extends User {

    private String user;
    private List<? extends GrantedAuthority> roles;
    private String email;

    public ProjectUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String email) {
        super(username, password, authorities);
        this.email = email;
    }

    @Override
    public String toString() {
        return "ProjectUser{" +
                "user='" + user + '\'' +
                ", roles=" + roles +
                ", email='" + email + '\'' +
                '}';
    }
}
