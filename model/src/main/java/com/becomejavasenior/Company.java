package com.becomejavasenior;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Integer id;
    @Column(name = "company_name")
    private String companyName;

    @ManyToOne
    private User responsible;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Column(name = "web_site")
    private String website;

    @ManyToOne
    private User createdBy;
    @Column(name = "isdeleted")
    private Boolean isDeleted;
    private String address;
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @OneToMany
    private List<File> files = new LinkedList<File>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tags_to_company", joinColumns = {
            @JoinColumn(name = "company_id", nullable = false, updatable = true)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", nullable = false, updatable = true)})
    private List<Tag> companiesToTags = new LinkedList<>();

    @OneToMany
    private List<Comment> comments = new LinkedList<>();

    @OneToMany
    private List<Task> tasks = new LinkedList<>();

    @OneToMany(mappedBy = "company")
    private List<Deal> deals = new LinkedList<>();

    @OneToMany(mappedBy = "companyId")
    private List<Contact> contacts = new LinkedList<>();

    public Company() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return companiesToTags;
    }

    public void setTags(List<Tag> tags) {
        this.companiesToTags = tags;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}