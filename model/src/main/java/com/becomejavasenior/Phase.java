package com.becomejavasenior;

import java.util.List;

/**
 * Created by valkos on 28.01.16.
 */
public class Phase {
    private Integer id;
    private String phase;
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
