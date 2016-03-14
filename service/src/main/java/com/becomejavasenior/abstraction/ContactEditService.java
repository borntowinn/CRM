package com.becomejavasenior.abstraction;

import com.becomejavasenior.*;

import java.util.List;
import java.util.Map;

public interface ContactEditService {
    Map<Integer, String> getPhaseMap();
    Map<Integer, String> getUserMap();
    Map<Integer, String> getCompanyComments(int companyId);
    Map<Integer, String> getDealComments(int dealId);
    void updateEntity(Object object) throws ClassNotFoundException;
    Tag selectTag(int contactId);
    void createDeal(Deal deal);
    User userByPK(String param);
    Phase phaseByPK(String param);
    Contact contactByPK(String param);
    List<Deal> dealsForContact(int contactId);
    Company companyByContactId(int contactId);
    Comment commentByContactId(int contactId);
}
