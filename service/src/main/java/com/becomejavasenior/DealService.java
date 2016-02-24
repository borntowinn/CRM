package com.becomejavasenior;

import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealService {

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

    public PhaseDao<Phase> getPhase() {
        return DaoFactory.getPhaseDao();
    }

    public CompanyDao<Company> getCompany() {
        return DaoFactory.getCompanyDAO();
    }

    public ContactDao<Contact> getContact() {
        return DaoFactory.getContactDAO();
    }

    public UserDao<User> getUser() {
        return DaoFactory.getUserDAO();
    }

    public DealDao<Deal> getDeal() {
        return DaoFactory.getDealDao();
    }

    public FileDao<File> getFile() {
        return DaoFactory.getFileDao();
    }

    public CommentDao<Comment> getComment() {
        return DaoFactory.getCommentDao();
    }

    public TagDao<Tag> getTag() {
        return DaoFactory.getTagDao();
    }

    public TaskDao<Task> getTask() {
        return DaoFactory.getTaskDao();
    }

    public User userByPK(String param) {
        return getUser().getByPK(Integer.valueOf(param));
    }

    public Phase phaseByPK(String param) {
        return getPhase().getByPK(Integer.valueOf(param));
    }

    //mock method, write current user instead this
    public User currentUser() {
        return getUser().getByPK(1);
    }

    public void executeInsert(String sql) {
        getDeal().executeQuery(sql);
    }

    public File createEntity(File file) {
        return getFile().create(file);
    }

    public Comment createEntity(Comment comment) {
        return getComment().create(comment);
    }

    public Tag createEntity(Tag tag) {
        return getTag().create(tag);
    }

    public Task createEntity(Task task) {
        return getTask().create(task);
    }

    public Company createEntity(Company company) {
        return getCompany().create(company);
    }

    public Contact createEntity(Contact contact) {
        return getContact().create(contact);
    }

    public Deal createEntity(Deal deal) {
        return getDeal().create(deal);
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

    private boolean isEmptyString(String parameter) {
        return parameter.equals("");
    }
}
