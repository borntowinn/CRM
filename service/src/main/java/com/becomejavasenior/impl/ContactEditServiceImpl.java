package com.becomejavasenior.impl;

import com.becomejavasenior.*;
import com.becomejavasenior.abstraction.ContactEditService;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactEditServiceImpl implements ContactEditService {
    @Override
    public Map<Integer, String> getPhaseMap() {
        Map<Integer, String> phaseMap = new HashMap<>();
        List<Phase> phaseList = getPhase().getAll();
        for (Phase phase : phaseList) {
            phaseMap.put(phase.getId(), phase.getPhase());

        }
        return phaseMap;
    }

    @Override
    public Map<Integer, String> getUserMap() {
        Map<Integer, String> userMap = new HashMap<>();
        List<User> userList = getUser().getAll();
        for (User user : userList) {
            userMap.put(user.getId(), user.getName());
        }
        return userMap;
    }

    public Map<Integer, String> getCompanyComments(int companyId) {
        Map<Integer, String> companyComments = new HashMap<>();
        List<Comment> commentList = getComment().selectCommentsForCompany(companyId);
        for (Comment comment : commentList) {
            companyComments.put(comment.getId(), comment.getComment());
        }
        return companyComments;
    }

    public Map<Integer, String> getDealComments(int dealId) {
        Map<Integer, String> companyComments = new HashMap<>();
        List<Comment> commentList = getComment().selectCommentsForDeal(dealId);
        for (Comment comment : commentList) {
            companyComments.put(comment.getId(), comment.getComment());
        }
        return companyComments;
    }

    public void updateEntity(Object object) throws ClassNotFoundException {
        if (object instanceof Company) getCompany().update((Company) object);
        if (object instanceof Contact) getContact().update((Contact) object);
        if (object instanceof Tag) getTag().update((Tag) object);
        if (object instanceof Comment) getComment().update((Comment) object);
        else throw new ClassNotFoundException();
    }

    public Tag selectTag(int contactId) {
        return getTag().selectTagByContact(contactId).get(0);
    }

    public void createDeal(Deal deal) {
        getDeal().create(deal);
    }

    public void createFile (File file) {
        getFile().create(file);
    }

    public User userByPK(String param) {
        return getUser().getByPK(Integer.valueOf(param));
    }

    public Phase phaseByPK(String param) {
        return getPhase().getByPK(Integer.valueOf(param));
    }

    public Contact contactByPK(String param) {
        return getContact().getByPK(Integer.valueOf(param));
    }

    public List<Deal> dealsForContact(int contactId) {
        return getDeal().selectDealByContactId(contactId);
    }

    public Company companyByContactId(int contactId) {
        return getCompany().selectCompanyByContactId(contactId);
    }

    public Comment commentByContactId(int contactId) {
        return getContact().selectComments(contactId).get(0);
    }

    private UserDao<User> getUser() {
        return DaoFactory.getUserDAO();
    }

    private CommentDao<Comment> getComment() {
        return DaoFactory.getCommentDao();
    }

    private PhaseDao<Phase> getPhase() {
        return DaoFactory.getPhaseDao();
    }

    private ContactDao<Contact> getContact() {
        return DaoFactory.getContactDAO();
    }

    private TagDao<Tag> getTag() {
        return DaoFactory.getTagDao();
    }

    private DealDao<Deal> getDeal() {
        return DaoFactory.getDealDao();
    }

    private CompanyDao<Company> getCompany() {
        return DaoFactory.getCompanyDAO();
    }

    private FileDao<File> getFile() {
        return DaoFactory.getFileDao();
    }
}
