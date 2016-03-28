package com.becomejavasenior;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_role_id")
    private Integer id;
    @Column(name = "role")
    private String userRole;

    @OneToMany(mappedBy = "userRole")
    List<User> users;

    public UserRole() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}