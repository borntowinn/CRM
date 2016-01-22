package com.becomejavasenior;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Default71721 on 22.01.16
 */
public class File implements Serializable {
    private Integer id;
    private Integer userNameId;
    private Date creationDate;
    private Byte[] file;
    private String fileName;
    private String fileSize;

    public File() {

    }

    public Integer getUserNameId() {

        return userNameId;
    }

    public void setUserNameId(Integer userNameId) {
        this.userNameId = userNameId;
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

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserNameID() {
        return userNameId;
    }

    public void setUserNameID(Integer userNameID) {
        this.userNameId = userNameID;
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
