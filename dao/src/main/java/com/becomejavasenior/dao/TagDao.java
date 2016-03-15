package com.becomejavasenior.dao;

import com.becomejavasenior.Tag;

import java.util.List;

public interface TagDao<T> extends AbstractDao<T> {
    void addTagToDeal(int tagId, int dealId);
    List<Tag> selectTagByContact(int contactId);
}
