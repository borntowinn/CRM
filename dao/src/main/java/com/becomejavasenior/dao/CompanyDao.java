package com.becomejavasenior.dao;

import com.becomejavasenior.Company;

import java.util.List;

public interface CompanyDao {
    Company create(Company object) throws RuntimeException;

    Company persist(Company object) throws RuntimeException;

    Company getByPK(Integer id) throws RuntimeException;

    void update(Company object) throws RuntimeException;

    void delete(Integer id) throws RuntimeException;

    List<Company> getAll() throws RuntimeException;
}