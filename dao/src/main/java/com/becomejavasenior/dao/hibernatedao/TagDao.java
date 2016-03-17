package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Contact;
import com.becomejavasenior.Tag;

import java.util.List;

public interface TagDao extends GeneralDao {
    Tag getByPK(Integer id);
    List getAll();
//    void addTagToDeal(int tagId, int dealId);
    List selectTagByContact(Contact contactId);
}
