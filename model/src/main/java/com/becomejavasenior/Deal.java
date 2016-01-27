package com.becomejavasenior;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Default71721 on 22.01.16
 */
public class Deal implements Serializable {
    private Integer id;
    private User userId;
    private BigDecimal budget;
    private String phase;
    private String textArea;
    private Date creationDate;
    private Company company;
    private List<Contact> contactList;
    private List<Tag> tagList;
    private List<Comment> commentList;
    private List<File> fileList;

    public Deal() {

    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getTextArea() {
        return textArea;
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
