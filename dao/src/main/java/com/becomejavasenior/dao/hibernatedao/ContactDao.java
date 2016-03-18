package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Comment;
import com.becomejavasenior.Contact;
import com.becomejavasenior.File;

import java.util.List;

public interface ContactDao extends GeneralDao{
    void addCommentToContact(Comment comment, Contact contactId);
    void addFileToContact(File file, int contactId);
    List<String> getAllTags();
}
