package com.becomejavasenior.service;

import com.becomejavasenior.Company;
import com.becomejavasenior.Contact;
import com.becomejavasenior.Phase;
import com.becomejavasenior.User;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralService {

    public static Map<Integer, String> getPhaseMap() {
        Map<Integer, String> phaseMap = new HashMap<>();
        List<Phase> phaseList = DaoFactory.getPhaseDao().getAll();
        for (Phase phase : phaseList) {
            phaseMap.put(phase.getId(), phase.getPhase());

        };
        return phaseMap;
    }

    public static Map<Integer, String> getUserMap() {
        Map<Integer, String> userMap = new HashMap<>();
        List<User> userList = DaoFactory.getUserDAO().getAll();
        for (User user : userList) {
            userMap.put(user.getId(), user.getName());
        }
        return userMap;
    }

    public static Map<Integer, String> getCompanyMap() {
        Map<Integer, String> companyMap = new HashMap<>();
        companyMap.put(null, "");
        List<Company> companyList = DaoFactory.getCompanyDAO().getAll();
        for (Company company : companyList) {
            companyMap.put(company.getId(), company.getCompanyName());
        }
        return companyMap;
    }

    public static Map<Integer, String> getContactMap() {
        Map<Integer, String> contactMap = new HashMap<>();
        contactMap.put(null, "");
        List<Contact> contactList = DaoFactory.getContactDAO().getAll();
        for (Contact contact : contactList) {
            contactMap.put(contact.getId(), contact.getNameSurname());
        }
        return contactMap;
    }
}
