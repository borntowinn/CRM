package com.becomejavasenior;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Default71721 on 22.01.16
 */
public class Comment implements Serializable{
    private Integer id;
    private String comment;
    private Date creationDate;

    public Comment() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
