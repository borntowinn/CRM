package com.becomejavasenior.dao;

import com.becomejavasenior.Comment;
import com.becomejavasenior.Company;
import com.becomejavasenior.File;

import java.util.List;

public interface CompanyDao<T> extends AbstractDao<T> {
    Company selectCompanyByContactId(int contactId);
}