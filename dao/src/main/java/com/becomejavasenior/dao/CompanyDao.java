package com.becomejavasenior.dao;

import com.becomejavasenior.Comment;
import com.becomejavasenior.Company;
import com.becomejavasenior.File;

import java.util.List;

public interface CompanyDao<T> extends AbstractDao<T> {
    void addFileForCompany(File file, Company company);
    void addCommentForCompany(Comment comment, Company company);
    List<File> getAllFilesForCompany(Company company);
    List<Comment> getAllCommentsForCompany(Company company);
}