package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Contact;

import java.util.List;

public interface TagDao extends GeneralDao {
//    void addTagToDeal(int tagId, int dealId);
    List selectTagByContact(Contact contactId);
}
