package com.becomejavasenior.dao.jdbc.factory;

import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.jdbc.impl.*;

public class DaoFactory {
    public static UserRoleDao getUserRoleDAO() {
        return new UserRoleDaoImpl();
    }

    public static UserDao getUserDAO() {
        return new UserDaoImpl();
    }

    public static CompanyDao getCompanyDAO() {
        return new CompanyDaoImpl();
    }

    public static ContactDao getContactDAO() {
        return new ContactDaoImpl();
    }

    public static PhaseDao getPhaseDao() {
        return new PhaseDaoImpl();
    }

    public static DealDao getDealDao() {
        return new DealDaoImpl();
    }


    public static SessionHistoryDao getSessionHistoryDao() {
        return new SessionHistoryDaoImpl();
    }
    public static DashboardDao getDashboardDao(){
        return new DashboardDaoImpl();
    }
}