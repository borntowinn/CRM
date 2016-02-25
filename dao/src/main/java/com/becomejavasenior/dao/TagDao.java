package com.becomejavasenior.dao;

public interface TagDao<T> extends AbstractDao<T> {
    void addTagToDeal(int tagId, int dealId);
}
