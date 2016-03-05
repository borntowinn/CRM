package com.becomejavasenior.dao;

import com.becomejavasenior.Comment;

import java.util.List;

public interface CommentDao<T> extends AbstractDao<T> {
    List<Comment> selectCommentsForCompany(int companyId);
    List<Comment> selectCommentsForDeal(int dealId);
}
