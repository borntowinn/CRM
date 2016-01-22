package com.becomejavasenior;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Default71721 on 22.01.16
 */
public class File implements Serializable {
    private Integer id;
    private User userName;
    private Date creationDate;
    private Byte[] file;
    private String fileName;

    public File() {

    }

    public User getUserName() {

        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public void setFile(Byte[] file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserNameID() {
        return userName;
    }

    public void setUserNameID(User userNameID) {
        this.userName = userNameID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Byte[] getFile() {
        return file;
    }
}
