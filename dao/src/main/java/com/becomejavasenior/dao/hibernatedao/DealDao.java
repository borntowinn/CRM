package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Deal;

import java.util.List;

public interface DealDao extends GeneralDao {
    Deal getByPK(Integer id);
    List getAll();
    List selectDealByContactId(int contactId);
}
