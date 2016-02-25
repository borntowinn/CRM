package com.becomejavasenior.abstraction;

import com.becomejavasenior.Company;
import com.becomejavasenior.Contact;
import com.becomejavasenior.Phase;
import com.becomejavasenior.User;

import java.util.Map;

public interface DealService {
    Map<Integer, String> getPhaseMap();
    Map<Integer, String> getUserMap();
    Map<Integer, String> getCompanyMap();
    Map<Integer, String> getContactMap();
    User userByPK(String param);
    Phase phaseByPK(String param);
    void executeInsert(int tagId, int dealId);
    Object createEntity(Object object) throws ClassNotFoundException;
    Company companyByPK(String parameter);
    Contact contactByPK(String param);
}
