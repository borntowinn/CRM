package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Contact;
import com.becomejavasenior.Tag;

import java.util.List;

public interface TagDao extends GeneralDao {
//    void addTagToDeal(int tagId, int dealId);
    List<Tag> selectTagByContact(Contact contactId);
}
