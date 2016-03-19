package com.becomejavasenior.dao.jdbc.impl;


import com.becomejavasenior.*;
import com.becomejavasenior.dao.TaskDao;
import com.becomejavasenior.dao.exception.PersistException;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import org.junit.*;

import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Katia on 03.02.2016.
 */
public class TaskDaoImplTest {
    private static Task newTask = null;
    private static Task lastInsertedObject = null;
    private List<Task> tasks = null;
    private static TaskDao taskDao =null;

    @BeforeClass
    public static void setUp() {
        taskDao = DaoFactory.getTaskDao();
        newTask = new Task();
        lastInsertedObject = new Task();
    }

    @Test
    public void testCreateAndUpdateAndGetByPK(){
        User user = new User();
        user.setId(1);
        Deal deal = new Deal();
        deal.setId(1);
        Company company = new Company();
        company.setId(1);

        newTask.setPeriod("one week");
        newTask.setTaskName("new_task");
        newTask.setPlanTime(LocalDateTime.now());
        newTask.setResponsible(user);
        newTask.setTaskType("low_priority");
        newTask.setAuthor(user);
        newTask.setCompany(company);
        newTask.setDeal(deal);
        newTask.setCreationTime(LocalDateTime.now());
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

        newTask.setPeriod("one week");
        newTask.setTaskName("new_task");
        newTask.setPlanTime(LocalDateTime.now());
        newTask.setResponsible(user);
        newTask.setTaskType("low_priority");
        newTask.setAuthor(user);
        newTask.setCompany(company);
        newTask.setDeal(deal);
        newTask.setCreationTime(LocalDateTime.now());
        newTask.setDeleted(Boolean.FALSE);
        newTask.setDone(Boolean.FALSE);
        Task lastInsertedObject = (Task) taskDao.persist(newTask);

        //delete
        taskDao.delete(lastInsertedObject.getId());

        //getByPK - PersistException
        taskDao.getByPK(lastInsertedObject.getId());
    }
}
