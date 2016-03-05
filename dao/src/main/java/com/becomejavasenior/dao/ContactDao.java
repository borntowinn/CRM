package com.becomejavasenior.dao;

import com.becomejavasenior.Comment;

/**
 * Created by valkos on 02.02.16.
 */
public interface ContactDao<T> extends AbstractDao<T> {
    Comment selectComments(int contactId);
}
