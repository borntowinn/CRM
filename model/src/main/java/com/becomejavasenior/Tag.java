package com.becomejavasenior;

import java.io.Serializable;

/**
 * Created by Default71721 on 22.01.16
 */
public class Tag implements Serializable {
    private Integer id;
    private String tag;

    public Tag() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
