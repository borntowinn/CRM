package com.becomejavasenior.dao;

import com.becomejavasenior.dao.exception.PersistException;

/**
 * Created by Default71721 on 10.02.16.
 */
public interface CommentDao<T> extends AbstractDao<T> {
    public String getTaskComment(Integer task_id) throws PersistException;
}
