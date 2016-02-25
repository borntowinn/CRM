package com.becomejavasenior.dao;

import com.becomejavasenior.Task;
import com.becomejavasenior.dao.exception.PersistException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kast0615 on 2/1/2016.
 */
public interface TaskDao<T> extends AbstractDao<T> {

    List<String> getTaskTypes();
    List<String> getTaskPeriods();
    List<Task> getTasksForPeriod(LocalDateTime start, LocalDateTime end) throws PersistException;

}
