package com.becomejavasenior.dao;

import com.becomejavasenior.Deal;

import java.util.List;

/**
 * Created by Default71721 on 28.01.16.
 */
@Deprecated
public interface DealDao<T> extends AbstractDao<T> {
    List<Deal> selectDealByContactId(int contactId);
}
