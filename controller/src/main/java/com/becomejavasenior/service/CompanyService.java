package com.becomejavasenior.service;

import java.util.HashMap;
import java.util.Map;

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
