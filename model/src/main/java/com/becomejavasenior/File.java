package com.becomejavasenior;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Default71721 on 22.01.16
 */
public class File implements Serializable {
    private Integer id;
    private LocalDateTime creationDate;
    private byte[] file;
    private String fileName;

    public File() {

    }

    public void setFile(byte[] file) {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public byte[] getFile() {
        return file;
    }
}
