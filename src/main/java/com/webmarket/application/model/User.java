package com.webmarket.application.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.EnumSet;
import java.util.Set;

public class User {
    @Id
    private String id;

    @Email
    @NotEmpty(message = "must not be empty")
    @Indexed(unique = true)
    private String email;

    @NotEmpty
    @Range(min = 6, max = 12, message = "must be between 6 and 12 characters")
    private String password;

    private Set<Role> roles;

    public User() {
    }

    public User(String id, String email, String password, Role role, Role... roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = EnumSet.of(role, roles);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Role role, Role... roles) {
        this.roles = EnumSet.of(role, roles);
    }
}
