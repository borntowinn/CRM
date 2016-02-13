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

    private static Map<Integer, String> phoneTypeMap;

    static {
        phoneTypeMap = new HashMap<>();
        phoneTypeMap.put(1, "Домашний");
        phoneTypeMap.put(2, "Рабочий");
        phoneTypeMap.put(3, "Мобильный");
        phoneTypeMap.put(4, "Факс");
        phoneTypeMap.put(5, "Другой");
    }


    public static Map<Integer, String> getPhoneTypeMap() {
        return phoneTypeMap;
    }

}
