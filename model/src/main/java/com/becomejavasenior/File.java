package com.becomejavasenior;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Default71721 on 22.01.16
 */
public class File implements Serializable {
    private Integer id;
    private Date creationDate;
    private Byte[] file;
    private String fileName;

    public File() {

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
