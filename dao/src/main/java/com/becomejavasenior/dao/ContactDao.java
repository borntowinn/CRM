package com.becomejavasenior.dao;

import com.becomejavasenior.Comment;
import com.becomejavasenior.File;
import com.becomejavasenior.Tag;

import java.util.List;

/**
 * Created by valkos on 02.02.16.
 */
public interface ContactDao<T> extends AbstractDao<T> {
    void addCommentToContact(Comment comment, int contact_id);
    void addFileToContact(File file, int contact_id);
    List<String> getAllTegs();
}
