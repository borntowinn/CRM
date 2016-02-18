package com.becomejavasenior.dao.jdbc.impl;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kast0615 on 2/1/2016.
 */
public class TaskDaoImpl extends AbstractJDBCDao<Task> implements TaskDao<Task> {
 
  private static final Logger log = Logger.getLogger(TaskDaoImpl.class);
  private static final Logger log = Logger.getLogger(TaskDaoImpl.class);
  private final static String SELECT_QUERY = "SELECT task.*, comment.comment FROM task INNER JOIN comment ON task.task_id = comment.task_id";
  private final static String SELECT_BY_PK = "SELECT * FROM task WHERE task_id=?";
  private final static String CREATE_QUERY = "INSERT INTO task (period, task_name, plantime, responsible, task_type, author, company_id, deal_id, creation_time, contact_id, isdeleted, isdone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  private final static String UPDATE_QUERY = "UPDATE task SET period = ?, task_name = ?, plantime  = ?, responsible = ?, task_type = ?, author = ?, company_id = ?, deal_id = ? , creation_time = ?, contact_id = ?, isdeleted =?, isdone = ? WHERE task_id=?";
  private final static String DELETE_QUERY = "DELETE FROM task WHERE task_id= ?;";
  private final static String GET_TASKS_FOR_PERIOD ="SELECT task.*, comment.comment FROM task INNER JOIN comment ON task.task_id = comment.task_id WHERE plantime >= ? AND plantime < ?";

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
        task.setDeleted(rs.getBoolean("isdeleted"));
        task.setDone(rs.getBoolean("isdone"));
        task.setTaskType(rs.getString("task_type"));
        task.setResponsible(userDao.getByPK(rs.getInt("responsible")));
        task.setAuthor(userDao.getByPK(rs.getInt("author")));

        if(rs.getMetaData().getColumnCount() == 14){
          task.setComment(rs.getString("comment"));
        }

        if(rs.getTimestamp("plantime") != null){
          task.setPlanTime(rs.getTimestamp("plantime").toLocalDateTime());
        }
        if(rs.getTimestamp("creation_time") != null){
          task.setCreationTime(rs.getTimestamp("creation_time").toLocalDateTime());
        }
        if(rs.getInt("company_id") != 0){
          task.setCompany(companyDao.getByPK(rs.getInt("company_id")));
        }
        if(rs.getInt("deal_id") != 0){
          task.setDeal(dealDao.getByPK(rs.getInt("deal_id")));
        }
        if(rs.getInt("contact_id") != 0){
          task.setContact(contactDao.getByPK(rs.getInt("contact_id")));
        }

        result.add(task);
      }

    } catch (Exception e) {
      log.error("result has not parsed " + e);
      throw new PersistException(e);
    }
    return result;
  }

  @Override
  protected void prepareStatementForInsert(PreparedStatement statement, Task object) throws PersistException {
    setObjectValueToStatement(statement, object);
  }

  @Override
  protected void prepareStatementForUpdate(PreparedStatement statement, Task object) throws PersistException {
    setObjectValueToStatement(statement, object);
    try {
      statement.setInt(13, object.getId());
    } catch (SQLException e) {
      log.error("couldn't prepared Statement for Insert " + e);
      e.printStackTrace();
    }
  }


  private void setObjectValueToStatement(PreparedStatement statement, Task object) throws PersistException {
    try {

      statement.setString(1, object.getPeriod());
      statement.setString(2, object.getTaskName());

      if(object.getPlanTime() != null){
        statement.setTimestamp(3, Timestamp.valueOf(object.getPlanTime()));
      }else{
        statement.setNull(3, Types.TIMESTAMP);
      }

      statement.setInt(4, object.getResponsible().getId());
      statement.setString(5, object.getTaskType());
      statement.setInt(6, object.getAuthor().getId());

      if(object.getCompany() != null){
        statement.setInt(7, object.getCompany().getId());
      }else{
        statement.setNull(7, Types.INTEGER);
      }

      if(object.getDeal() != null){
        statement.setInt(8, object.getDeal().getId());
      } else{
        statement.setNull(8, Types.INTEGER);
      }

      if(object.getCreationTime() != null){
        statement.setTimestamp(9, Timestamp.valueOf(object.getCreationTime()));
      }else{
        statement.setNull(9, Types.TIMESTAMP);
      }

      if(object.getContact() != null){
        statement.setInt(10, object.getContact().getId());
      } else{
        statement.setNull(10, Types.INTEGER);
      }

      if(object.getDeleted() != null){
        statement.setBoolean(11, object.getDeleted());
      }else{
        statement.setNull(11, Types.BOOLEAN);
      }

      if(object.getDone() != null){
        statement.setBoolean(12, object.getDone());
      }else{
        statement.setNull(12, Types.BOOLEAN);
      }

    } catch (SQLException e) {
      log.error("couldn't prepared Statement for Update " + e);
      throw new PersistException(e);
    }
  }

  @Override
  public Task create(Task object) throws PersistException {
    return persist(object);
  }

  @Override
  public List<Task> getTasksForPeriod(LocalDateTime start, LocalDateTime end) throws PersistException{
    List<Task> taskList = null;

    String sql = TaskDaoImpl.GET_TASKS_FOR_PERIOD;
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setTimestamp(1, Timestamp.valueOf(start));
      statement.setTimestamp(2, Timestamp.valueOf(end));
      ResultSet rs = statement.executeQuery();
      taskList = parseResultSet(rs);

      if(rs != null){
        rs.close();
      }
    } catch (SQLException e) {
      throw new PersistException(e);
    }
    return taskList;
  }

}