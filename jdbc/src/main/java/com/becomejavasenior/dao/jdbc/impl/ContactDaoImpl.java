package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.Contact;
import com.becomejavasenior.dao.AbstractDao;
import com.becomejavasenior.dao.ContactDao;
import com.becomejavasenior.dao.exception.PersistException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by kast0615 on 2/1/2016.
 */
public class ContactDaoImpl extends AbstractJDBCDao<Contact> implements ContactDao<Contact>{

    @Override
    protected String getSelectQuery() {
        return null;
    }

    @Override
    protected String getUpdateQuery() {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return null;
    }

    @Override
    protected String getCreateQuery() {
        return null;
    }

    @Override
    protected String getSelectPKQuery() {
        return null;
    }

    @Override
    protected List<Contact> parseResultSet(ResultSet rs) throws PersistException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Contact object) throws PersistException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Contact object) throws PersistException {

    }

    @Override
    public Contact create(Contact object) throws PersistException {
        return null;
    }
}
