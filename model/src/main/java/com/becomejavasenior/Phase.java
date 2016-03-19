package com.becomejavasenior;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "phase")
public class Phase {

    @Id
    @GeneratedValue
    @Column(name = "phase_id")
    private Integer id;
    private String phase;

    @OneToMany(mappedBy = "phase")
    private List<Deal> deals;

    public Phase() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }
}
