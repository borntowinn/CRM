package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Deal;

import java.util.List;

public interface DealDao extends GeneralDao {
    List<Deal> selectDealByContactId(int contactId);
}
