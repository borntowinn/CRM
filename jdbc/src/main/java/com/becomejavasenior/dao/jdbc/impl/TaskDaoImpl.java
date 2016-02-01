package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kast0615 on 2/1/2016.
 */
public class TaskDaoImpl extends AbstractJDBCDao<Task> implements TaskDao<Task> {

    private final static String SELECT_QUERY = "SELECT task_id, period, taskname, plantime, responsible, task_type, comment, author, company_id, deal_id, creation_time, contact_id, isdeleted from task ";
    private final static String SELECT_BY_PK = "SELECT task_id, period, taskname, plantime, responsible, task_type, comment, author, company_id, deal_id, creation_time, contact_id, isdeleted from task WHERE task_id=?";
    private final static String CREATE_QUERY = "INSERT INTO task (period, taskname, plantime, responsible, task_type, comment, author, company_id, deal_id, creation_time, contact_id, isdeleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?. ?, ?, ?)";
    private final static String UPDATE_QUERY = "UPDATE task SET period = ?, taskname = ?, plantime  = ?, responsible = ?, task_type = ?, comment = ?, author = ?, company_id = ?, deal_id = ? , creation_time = ?, contact_id = ?, isdeleted =? WHERE task_id=?";
    private final static String DELETE_QUERY = "DELETE FROM task WHERE task_id= ?;";

    private UserDao<User> userDao = DaoFactory.getUserDAO();
    private DealDao<Deal> dealDao = DaoFactory.getDealDao();
    private CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
    private ContactDao<Contact> contactDao = DaoFactory.getContactDao();


    @Override
    protected String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String getSelectPKQuery() {
        return SELECT_BY_PK;
    }

    @Override
    protected List parseResultSet(ResultSet rs) throws PersistException {
        List<Task> result = new LinkedList<>();
        try{
            while(rs.next()){
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setPeriod(rs.getString("period"));
                task.setTaskName(rs.getString("task_name"));
                task.setPlanTime(rs.getTime("plan_time"));
                task.setResponsible(userDao.getByPK(rs.getInt("responsible")));
                task.setTaskType(rs.getString("task_type"));
                task.setAuthor(userDao.getByPK(rs.getInt("author")));
                task.setCompany(companyDao.getByPK(rs.getInt("company_id")));
                task.setDeal(dealDao.getByPK(rs.getInt("deal_id")));
                task.setCreationTime(rs.getTime("creation_time"));
                task.setContact(contactDao.getByPK(rs.getInt("contact_id")));
                task.setDeleted(rs.getBoolean("isdeleted"));
                result.add(task);
            }

        }catch (Exception e){
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Task object) throws PersistException {
        try{
            statement.setString(1, object.getPeriod());
            statement.setString(2, object.getTaskName());
            statement.setTimestamp(3, Timestamp.valueOf(object.getPlanTime()));
            statement.setInt(4, object.getResponsible().getId());
            statement.setString(5, object.getTaskType());
            statement.setInt(6, object.getAuthor().getId());
            statement.setInt(7, object.getCompany().getId());
            statement.setInt(8, object.getDeal().getId());
            statement.setTime(9, (Time) object.getCreationTime());
            statement.setInt(10, object.getContact().getId());
            statement.setBoolean(11, object.getDeleted());
            statement.setInt(12, object.getId());
        }catch (Exception e){
            throw new PersistException(e);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Task object) throws PersistException {
        try{
            statement.setString(1, object.getPeriod());
            statement.setString(2, object.getTaskName());
            statement.setTimestamp(3, Timestamp.valueOf(object.getPlanTime()));
            statement.setInt(4, object.getResponsible().getId());
            statement.setString(5, object.getTaskType());
            statement.setInt(6, object.getAuthor().getId());
            statement.setInt(7, object.getCompany().getId());
            statement.setInt(8, object.getDeal().getId());
            statement.setTime(9, (Time) object.getCreationTime());
            statement.setInt(10, object.getContact().getId());
            statement.setBoolean(11, object.getDeleted());
        }catch (Exception e){
            throw new PersistException(e);
        }
    }



    @Override
    public Task create(Task object) throws PersistException {
        return persist(object);
    }
}
