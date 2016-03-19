package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Company;
import com.becomejavasenior.Deal;
import com.becomejavasenior.File;

import java.util.List;

public interface ContactDao<T> extends GeneralDao<T> {
    Company selectCompanyByContactId(int contactId);
    List<Deal> selectDealByContactId(int contactId);
    void addFileToContact(File file, int contactId);
    List<String> getAllTags();
}
