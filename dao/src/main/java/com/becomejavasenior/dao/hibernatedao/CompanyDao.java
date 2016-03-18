package com.becomejavasenior.dao.hibernatedao;

import com.becomejavasenior.Company;

public interface CompanyDao {
    Company selectCompanyByContactId(int contactId);
}
