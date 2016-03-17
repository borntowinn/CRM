package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Comment;
import com.becomejavasenior.Contact;
import com.becomejavasenior.File;

import java.util.List;

public interface ContactDao extends GeneralDao{
    Comment getByPK(Integer id);
    List getAll();
    void addCommentToContact(Comment comment, Contact contact_id);
    void addFileToContact(File file, int contact_id);
    List<String> getAllTags();
}
