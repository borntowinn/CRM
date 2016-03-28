package com.becomejavasenior;

import javax.persistence.*;

@Entity
@Table(name = "timezone")
public class Timezone {

    @Id
    @GeneratedValue
    @Column(name = "timezone_id")
    private Integer id;
    private String timezone;

    public Timezone() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}