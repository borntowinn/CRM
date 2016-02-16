package com.becomejavasenior.dao;

import java.util.List;

/**
 * Created by kast0615 on 2/1/2016.
 */
public interface TaskDao<T> extends AbstractDao<T> {
    List<String> getTaskTypes();
    List<String> getTaskPeriods();
}
