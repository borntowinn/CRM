package com.becomejavasenior;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Integer id;
    private String comment;

    @Column(name = "data_creation")
    private LocalDateTime creationDate;

    @ManyToOne
    private Company companyId;

    @ManyToOne
    private Deal dealId;

    @ManyToOne
    private Contact contactId;

    @ManyToOne
    private Task taskId;

    public Comment() {

    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public Deal getDealId() {
        return dealId;
    }

    public void setDealId(Deal dealId) {
        this.dealId = dealId;
    }

    public Contact getContactId() {
        return contactId;
    }

    public void setContactId(Contact contactId) {
        this.contactId = contactId;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
