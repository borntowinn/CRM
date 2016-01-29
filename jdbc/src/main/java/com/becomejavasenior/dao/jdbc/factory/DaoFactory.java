package com.becomejavasenior.dao.jdbc.factory;

import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.jdbc.impl.UserDaoImpl;
import com.becomejavasenior.dao.jdbc.impl.UserRoleDaoImpl;
import com.becomejavasenior.dao.jdbc.impl.CompanyDaoImpl;

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
}