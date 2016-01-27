package com.becomejavasenior;

import java.io.Serializable;

/**
 * Created by Default71721 on 22.01.16.
 */
public class UserRole implements Serializable{
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