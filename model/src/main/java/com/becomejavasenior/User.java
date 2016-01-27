package com.becomejavasenior;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Default71721 on 22.01.16
 */
public class User implements Serializable {
    private Integer id;
    private String name;
    private String surname;
    private String password;
    private Date creationDate;
    private UserRole userRole;
    private String email;
    private String mobilePhone;
    private String workPhone;

    public User() {
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserRole getUserRoleID() {
        return userRole;
    }

    public void setUserRoleID(UserRole userRoleID) {
        this.userRole = userRoleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }
}
