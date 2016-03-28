package com.becomejavasenior;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "deal")
public class Deal implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deal_id")
    private Integer id;

    @Column(name = "name")
    private String dealName;

    @ManyToOne
    @JoinColumn(name = "createdby")
    private User createdBy;
    private BigDecimal budget;

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "responsible")
    private User responsible;

    @Column(name = "date_creation")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @Column(name = "isdeleted")
    private Boolean isDeleted;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tags_to_deal", joinColumns = {
            @JoinColumn(name = "deal_id", nullable = false, updatable = true)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", nullable = false, updatable = true)})
    private List<Tag> tagList = new LinkedList<Tag>();

    @OneToMany
    private List<Comment> commentList = new LinkedList<>();
    @OneToMany
    private List<File> fileList = new LinkedList<>();
    @OneToMany
    private List<Task> tasks = new LinkedList<>();

    public Deal() {

    }

    public Integer getId() {
        return id;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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
}