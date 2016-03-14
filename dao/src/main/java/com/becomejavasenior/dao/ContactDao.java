package com.becomejavasenior.dao;

import com.becomejavasenior.Comment;

import java.util.List;

/**
 * Created by valkos on 02.02.16.
 */
public interface ContactDao<T> extends AbstractDao<T> {
    List<Comment> selectComments(int contactId);
}
