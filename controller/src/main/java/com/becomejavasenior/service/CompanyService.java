package com.becomejavasenior.service;

import com.becomejavasenior.Company;
import com.becomejavasenior.Phase;
import com.becomejavasenior.User;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Default71721 on 06.02.16.
 */
public class CompanyService {

    private static Map<Integer, String> userMap;
    private static Map<Integer, String> phoneTypeMap;
    private static Map<Integer, String> companyMap;
    private static Map<Integer, String> phaseMap;

    static {
        userMap = new HashMap<>();
        List<User> userList = DaoFactory.getUserDAO().getAll();
        for (User user : userList) {
            userMap.put(user.getId(), user.getName());
        }

        companyMap = new HashMap<>();
        List<Company> companyList = DaoFactory.getCompanyDAO().getAll();
        for (Company company : companyList) {
            companyMap.put(company.getId(), company.getCompanyName());
        }

        phaseMap = new HashMap<>();
        List<Phase> phaseList = DaoFactory.getPhaseDao().getAll();
        for (Phase phase : phaseList) {
            phaseMap.put(phase.getId(), phase.getPhase());

        }

        phoneTypeMap = new HashMap<>();
        phoneTypeMap.put(1, "Домашний");
        phoneTypeMap.put(2, "Рабочий");
        phoneTypeMap.put(3, "Мобильный");
        phoneTypeMap.put(4, "Факс");
        phoneTypeMap.put(5, "Другой");
    }

    public static Map<Integer, String> getPhaseMap() {
        return phaseMap;
    }

    public static Map<Integer, String> getUserMap() {

        return userMap;
    }

    public static Map<Integer, String> getPhoneTypeMap() {
        return phoneTypeMap;
    }

    public static Map<Integer, String> getCompanyMap() {
        return companyMap;
    }
}
