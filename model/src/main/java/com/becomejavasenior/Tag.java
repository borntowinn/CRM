package com.becomejavasenior;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Integer id;
    private String tag;

    @ManyToMany(mappedBy = "tags", targetEntity = Contact.class)
    private List<Contact> tagsToContacts = new ArrayList<>();

    @ManyToMany(mappedBy = "tagList")
    private List<Deal> tagsToDeals = new ArrayList<>();

    @ManyToMany(mappedBy = "companiesToTags")
    private List<Company> tagsToCompanies = new ArrayList<>();

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

    public List<Contact> getTagsToContacts() {
        return tagsToContacts;
    }

    public void setTagsToContacts(List<Contact> tagsToContacts) {
        this.tagsToContacts = tagsToContacts;
    }

    public List<Deal> getTagsToDeals() {
        return tagsToDeals;
    }

    public void setTagsToDeals(List<Deal> tagsToDeals) {
        this.tagsToDeals = tagsToDeals;
    }

    public List<Company> getTagsToCompanies() {
        return tagsToCompanies;
    }

    public void setTagsToCompanies(List<Company> tagsToCompanies) {
        this.tagsToCompanies = tagsToCompanies;
    }
}
