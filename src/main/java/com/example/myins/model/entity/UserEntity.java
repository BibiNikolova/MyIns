package com.example.myins.model.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Role> roles;
    @ManyToOne
    private Client client;


    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public UserEntity setClient(Client client) {
        this.client = client;
        return this;
    }
}

