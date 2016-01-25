package com.becomejavasenior;

import java.io.Serializable;

public class UserRole implements Serializable, Identified<Integer> {
    private Integer id;
    private String userRole;

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
}
