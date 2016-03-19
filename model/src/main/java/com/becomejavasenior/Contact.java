package com.becomejavasenior;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "contact_id")
    private Integer id;
    @Column(name = "name_surname")
    private String nameSurname;
    @Column(name = "phone_type")
    private Integer phoneType;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    private String skype;
    private String position;
    @Column(name = "isdeleted")
    private Boolean isDeleted;
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private Company companyId;

    @ManyToOne
    private User responsible;

    @OneToMany
    private List<File> files = new LinkedList<File>();

    @OneToMany(mappedBy = "contact")
    private List<Deal> deals = new LinkedList<Deal>();

    @OneToMany
    private List<Task> tasks = new LinkedList<Task>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tags_to_contact", joinColumns = {
            @JoinColumn(name = "contact_id", nullable = false, updatable = true)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", nullable = false, updatable = true)})
    private List<Tag> tags = new LinkedList<Tag>();

    @OneToMany
    private List<Comment> commentList = new LinkedList<>();

    public Contact() {
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public Integer getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Integer phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}