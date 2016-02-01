package com.becomejavasenior.dao;

import java.math.BigDecimal;

/**
 * Created by ipvinner on 30.01.2016.
 */
public interface DashboardDao {

    int getDealsCount();

    BigDecimal getDealsBudgetSum();

    int getSuccessDealsCount();

    int getFailedDealsCount();

    int getDealsWithoutTasks();

    int getDealsWithTasks();

    int getTasksCount();

    int getCompletedTasksCount();

    int getOverdueTasksCount();

    int getContactsCount();

    int getCompaniesCount();




}
