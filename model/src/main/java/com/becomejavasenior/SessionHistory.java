package com.becomejavasenior;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "session_history")
public class SessionHistory {

    @Id
    @GeneratedValue
    @Column(name = "session_history_id")
    private Integer id;
    @Column(name = "user_id")
    private User userId;
    @Column(name = "ip_address")
    private String ipAddress;
    private String browser;
    @Column(name = "data_session")
    private LocalDateTime creationDate;

    public SessionHistory() {
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
