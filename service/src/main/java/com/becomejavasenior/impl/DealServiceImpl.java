package com.becomejavasenior.impl;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import com.becomejavasenior.abstraction.DealService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealServiceImpl implements DealService{

    public Map<Integer, String> getPhaseMap() {
        Map<Integer, String> phaseMap = new HashMap<>();
        List<Phase> phaseList = getPhase().getAll();
        for (Phase phase : phaseList) {
            phaseMap.put(phase.getId(), phase.getPhase());

        }
        return phaseMap;
    }

    public Map<Integer, String> getUserMap() {
        Map<Integer, String> userMap = new HashMap<>();
        List<User> userList = getUser().getAll();
        for (User user : userList) {
            userMap.put(user.getId(), user.getName());
        }
        return userMap;
    }

    public Map<Integer, String> getCompanyMap() {
        Map<Integer, String> companyMap = new HashMap<>();
        companyMap.put(null, "");
        List<Company> companyList = getCompany().getAll();
        for (Company company : companyList) {
            companyMap.put(company.getId(), company.getCompanyName());
        }
        return companyMap;
    }

    public Map<Integer, String> getContactMap() {
        Map<Integer, String> contactMap = new HashMap<>();
        contactMap.put(null, "");
        List<Contact> contactList = getContact().getAll();
        for (Contact contact : contactList) {
            contactMap.put(contact.getId(), contact.getNameSurname());
        }
        return contactMap;
    }

    public User userByPK(String param) {
        return getUser().getByPK(Integer.valueOf(param));
    }

    public Phase phaseByPK(String param) {
        return getPhase().getByPK(Integer.valueOf(param));
    }

    public void executeInsert(int tagId, int dealId) {
        getTag().addTagToDeal(tagId, dealId);
    }

    public Object createEntity(Object object) throws ClassNotFoundException {
        if (object instanceof Deal) return getDeal().create((Deal) object);
        if (object instanceof Company) return getCompany().create((Company) object);
        if (object instanceof Contact) return getContact().create((Contact) object);
        if (object instanceof Task) return getTask().create((Task) object);
        if (object instanceof Tag) return getTag().create((Tag) object);
        if (object instanceof Comment) return getComment().create((Comment) object);
        if (object instanceof File) return getFile().create((File) object);
        else throw new ClassNotFoundException();
    }

    public Company companyByPK(String parameter) {
        if (!isEmptyString(parameter)) {
            return getCompany().getByPK(Integer.valueOf(parameter));
        }
        return null;
    }

    public Contact contactByPK(String param) {
        if (!isEmptyString(param)) {
            return getContact().getByPK(Integer.valueOf(param));
        }
        return null;
    }

    //mock method, write current user instead this
    public User currentUser() {
        return getUser().getByPK(1);
    }

    private boolean isEmptyString(String parameter) {
        return parameter.equals("");
    }

    private PhaseDao<Phase> getPhase() {
        return DaoFactory.getPhaseDao();
    }

    private CompanyDao<Company> getCompany() {
        return DaoFactory.getCompanyDAO();
    }

    private ContactDao<Contact> getContact() {
        return DaoFactory.getContactDAO();
    }

    private UserDao<User> getUser() {
        return DaoFactory.getUserDAO();
    }

    private DealDao<Deal> getDeal() {
        return DaoFactory.getDealDao();
    }

    private TagDao<Tag> getTag() {
        return DaoFactory.getTagDao();
    }

    private TaskDao<Task> getTask() {
        return DaoFactory.getTaskDao();
    }

    private CommentDao<Comment> getComment() {
        return DaoFactory.getCommentDao();
    }

    private FileDao<File> getFile() {
        return DaoFactory.getFileDao();
    }
}
