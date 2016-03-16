package com.becomejavasenior;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "file")
public class File implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Integer id;
    @Column(name = "date_creation")
    private LocalDateTime creationDate;
    @Column(name = "file")
    private byte[] file;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "company_id")
    private Company companyId;
    @Column(name = "deal_id")
    private Deal dealId;
    @Column(name = "contact_id")
    private Contact contactId;
    @Column(name = "user_id")
    private User userId;

    public File() {

    }


    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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

    public void setFile(byte[] file) {
        this.file = file;
    }
}
