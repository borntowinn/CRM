package com.becomejavasenior.dao.hibernatedao;

import java.util.List;

public interface DealDao extends GeneralDao {
    List selectDealByContactId(int contactId);
}
