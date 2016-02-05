package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kast0615 on 2/1/2016.
 */
public class TaskDaoImpl extends AbstractJDBCDao<Task> implements TaskDao<Task> {

  private final static String SELECT_QUERY = "SELECT task_id, period, task_name, plantime, responsible, task_type, author, company_id, deal_id, creation_time, contact_id, isdeleted, isdone from task ";
  private final static String SELECT_BY_PK = "SELECT task_id, period, task_name, plantime, responsible, task_type, author, company_id, deal_id, creation_time, contact_id, isdeleted, isdone from task WHERE task_id=?";
  private final static String CREATE_QUERY = "INSERT INTO task (period, task_name, plantime, responsible, task_type, author, company_id, deal_id, creation_time, contact_id, isdeleted, isdone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  private final static String UPDATE_QUERY = "UPDATE task SET period = ?, task_name = ?, plantime  = ?, responsible = ?, task_type = ?, author = ?, company_id = ?, deal_id = ? , creation_time = ?, contact_id = ?, isdeleted =?, isdone = ? WHERE task_id=?";
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
    try {
      while (rs.next()) {
        Task task = new Task();
        task.setId(rs.getInt("task_id"));
        task.setPeriod(rs.getString("period"));
        task.setTaskName(rs.getString("task_name"));
        task.setPlanTime(rs.getTimestamp("plantime").toLocalDateTime());
        task.setResponsible(userDao.getByPK(rs.getInt("responsible")));
        task.setTaskType(rs.getString("task_type"));
        task.setAuthor(userDao.getByPK(rs.getInt("author")));

        if(task.getCompany() != null){
          task.setCompany(companyDao.getByPK(rs.getInt("company_id")));
        }

        if(task.getDeal() != null){
          task.setDeal(dealDao.getByPK(rs.getInt("deal_id")));
        }

        if(task.getCreationTime() != null ){
          task.setCreationTime(rs.getTime("creation_time"));
        }

        if(task.getContact() != null){
          task.setContact(contactDao.getByPK(rs.getInt("contact_id")));
        }

        task.setDeleted(rs.getBoolean("isdeleted"));
        task.setDone(rs.getBoolean("isdone"));
        result.add(task);
      }

    } catch (Exception e) {
      throw new PersistException(e);
    }
    return result;
  }

  @Override
  protected void prepareStatementForInsert(PreparedStatement statement, Task object) throws PersistException {
    setObjectValueToStatement(statement, object);
    //statement.setInt(13, object.getId());
  }

  @Override
  protected void prepareStatementForUpdate(PreparedStatement statement, Task object) throws PersistException {
    setObjectValueToStatement(statement, object);
  }


  private void setObjectValueToStatement(PreparedStatement statement, Task object) throws PersistException {
    try {

      statement.setString(1, object.getPeriod());
      statement.setString(2, object.getTaskName());
      statement.setTimestamp(3, Timestamp.valueOf(object.getPlanTime()));
      statement.setInt(4, object.getResponsible().getId());
      statement.setString(5, object.getTaskType());
      statement.setInt(6, object.getAuthor().getId());

      if(object.getCompany() != null){
        statement.setInt(7, object.getCompany().getId());
      }else{
        statement.setNull(7, java.sql.Types.INTEGER);
      }

      if(object.getDeal() != null){
        statement.setInt(8, object.getDeal().getId());
      } else{
        statement.setNull(8, java.sql.Types.INTEGER);
      }

      if(object.getContact() != null){
        statement.setInt(10, object.getContact().getId());
      } else{
        statement.setNull(10, java.sql.Types.INTEGER);
      }

      statement.setNull(9, java.sql.Types.TIMESTAMP);
      statement.setBoolean(11, object.getDeleted());
      statement.setBoolean(12, object.getDone());
    } catch (SQLException e) {
      throw new PersistException(e);
    }
  }

  @Override
  public Task create(Task object) throws PersistException {
    return persist(object);
  }
}