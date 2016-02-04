package com.becomejavasenior.dao.jdbc.impl;


import com.becomejavasenior.*;
import com.becomejavasenior.dao.TaskDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

/**
 * Created by Katia on 03.02.2016.
 */
public class TaskDaoImplTest {
    private Task newTask = null;
    private Task lastInsertedObject = null;
    private List<Task> tasks = null;
    private TaskDao taskDao =null;

    @Before
    public void setUp() {
        Connection connection = ConnectionFactory.getConnection();
        taskDao = DaoFactory.getTaskDao();
        newTask = new Task();
        lastInsertedObject = new Task();

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateAndUpdateAndGetByPK(){
        User user = new User();
        user.setId(1);
        Deal deal = new Deal();
        deal.setId(1);
        Company company = new Company();
        company.setId(1);
        Contact contact = new Contact();

        newTask.setPeriod("one week");
        newTask.setTaskName("new_task");
        newTask.setPlanTime(LocalDateTime.now());
        newTask.setResponsible(user);
        newTask.setTaskType("low_priority");
        newTask.setAuthor(user);
        newTask.setCompany(company);
        newTask.setDeal(deal);
        newTask.setCreationTime(new java.util.Date());
        newTask.setContact(contact);
        newTask.setDeleted(Boolean.FALSE);
        newTask.setDone(Boolean.FALSE);

        //1 - create
        Task lastInsertedObject = (Task) taskDao.persist(newTask);
        assertEquals("new_task", lastInsertedObject.getTaskName());

        //2 - update
        lastInsertedObject.setTaskName("update_task");
        lastInsertedObject.setDeleted(Boolean.TRUE);
        taskDao.update(lastInsertedObject);

        //3 - get by PK and check update
        int id = lastInsertedObject.getId();
        Task getByPkTask = (Task) taskDao.getByPK(id);
        assertEquals("update_task", getByPkTask.getTaskName());
        assertNotSame(2, getByPkTask.getDeal().getId());
    }

    @Test
    public void testGetAll(){
        tasks = taskDao.getAll();
        Assert.assertNotNull(tasks);
        Assert.assertTrue(tasks.size() > 0);
    }

    @Test(expected = PersistException.class)
    public void testDeleteAndException(){
        //create

        User user = new User();
        user.setId(1);
        Deal deal = new Deal();
        deal.setId(1);
        Company company = new Company();
        company.setId(1);
        Contact contact = new Contact();

        newTask.setPeriod("one week");
        newTask.setTaskName("new_task");
        newTask.setPlanTime(LocalDateTime.now());
        newTask.setResponsible(user);
        newTask.setTaskType("low_priority");
        newTask.setAuthor(user);
        newTask.setCompany(company);
        newTask.setDeal(deal);
        newTask.setCreationTime(new java.util.Date());
        newTask.setContact(contact);
        newTask.setDeleted(Boolean.FALSE);
        newTask.setDone(Boolean.FALSE);
        Task lastInsertedObject = (Task) taskDao.persist(newTask);

        //delete
        taskDao.delete(lastInsertedObject.getId());

        //getByPK - PersistException
        taskDao.getByPK(lastInsertedObject.getId());
    }
}
