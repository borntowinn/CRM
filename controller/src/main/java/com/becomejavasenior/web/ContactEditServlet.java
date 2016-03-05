package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.ContactEditServiceImpl;
import com.becomejavasenior.service.CompanyService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "editContact",
        urlPatterns = {"/contact"}
)
@MultipartConfig
public class ContactEditServlet extends HttpServlet{
    private static final Logger LOGGER = Logger.getLogger(DealController.class);

    private final String EDIT_CONTACT = "/contacts/editContact.jsp";
    private final ContactEditServiceImpl contactService = new ContactEditServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Contact contact = contactService.contactByPK(request.getParameter("contactId"));
        request.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap()); //TODO: change it, when CompanyServise will be ready
        request.setAttribute("userMap", contactService.getUserMap());
        request.setAttribute("phaseMap", contactService.getPhaseMap());
        Integer contactId = contact.getId();
        request.setAttribute("commentsCompany", contactService.getCompanyComments(
                contactService.companyByContactId(contactId).getId()));
        Map<Integer, String> dealComments = new HashMap<>();
        for (Deal deal : contactService.dealsForContact(contactId)) {
            Map<Integer, String> comments = contactService.getDealComments(deal.getId());
            dealComments.putAll(comments);
        }
        request.setAttribute("commentsDeal", dealComments);
        request.setAttribute("deals", contactService.dealsForContact(contactId));

        request.getRequestDispatcher(EDIT_CONTACT)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Contact contact = contactService.contactByPK(request.getParameter("contactId"));
        request.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap()); //TODO: change it, when CompanyServise will be ready
        request.setAttribute("userMap", contactService.getUserMap());
        request.setAttribute("phaseMap", contactService.getPhaseMap());
        Integer contactId = contact.getId();
        request.setAttribute("commentsCompany", contactService.getCompanyComments(
                contactService.companyByContactId(contactId).getId()));
        Map<Integer, String> dealComments = new HashMap<>();
        for (Deal deal : contactService.dealsForContact(contactId)) {
            Map<Integer, String> comments = contactService.getDealComments(deal.getId());
            dealComments.putAll(comments);
        }
        request.setAttribute("commentsDeal", dealComments);
        request.setAttribute("deals", contactService.dealsForContact(contactId));
        request.setAttribute("nameSurname", contact.getNameSurname());

        editContact(request, contact);
        if (request.getParameter("") != null) {
            try {
                editCompany(request, contact);
            } catch (ClassNotFoundException e) {
                LOGGER.error("you try to update nonexistent object" + e);
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher(EDIT_CONTACT)
                .forward(request, response);
    }

    private void editContact(HttpServletRequest request, Contact editContact) throws ServletException, IOException {
        editContact.setNameSurname(request.getParameter("nameSurname"));
        editContact.setTags(updateTag(editContact));
        editContact.setResponsible(contactService.userByPK(request.getParameter("responsible")));
        editContact.setPosition(request.getParameter("position"));
        editContact.setPhoneType(Integer.valueOf(request.getParameter("phoneType")));
        editContact.setPhoneNumber(request.getParameter("phoneNumber"));
        editContact.setEmail(request.getParameter("email"));
        editContact.setSkype(request.getParameter("skype"));
        String note = request.getParameter("note");
        try {
            if (note != null) {
                editContact.setCommentList(updateComment(note, editContact.getId()));
            }
            contactService.updateEntity(editContact);
        } catch (ClassNotFoundException e) {
            LOGGER.error("you try to update nonexistent object" + e);
            e.printStackTrace();
        }
        if (request.getParameter("addDeal") != null) {
            addDeal(request);
        }
    }

    //
    private List<Tag> updateTag(Contact editContact) {
        Tag tag = contactService.executeInsert(editContact.getId());
        return null;
    }

    private List<Comment> updateComment(String note, int contactId) throws ClassNotFoundException {
        List<Comment> comments = new ArrayList<>();
        Comment comment = contactService.commentByContactId(contactId);
        comment.setCreationDate(LocalDateTime.now());
        comment.setComment(note);
        contactService.updateEntity(comment);
        comments.add(comment);
        return comments;
    }

    private void editCompany(HttpServletRequest request, Contact contact) throws ClassNotFoundException {
        Company company = contactService.companyByContactId(contact.getId());
        company.setCompanyName(request.getParameter("companyName"));
        company.setPhoneNumber(request.getParameter("phoneNumber"));
        company.setEmail(request.getParameter("email"));
        company.setWebsite(request.getParameter("webAddress"));
        company.setAddress(request.getParameter("address"));
        contactService.updateEntity(company);
    }

    private void addDeal(HttpServletRequest request) {
        Deal deal = new Deal();
        deal.setDealName(request.getParameter("dealName"));
        deal.setPhase(contactService.phaseByPK(request.getParameter("phaseList")));
        String budgetString = request.getParameter("budget");
        if (budgetString.contains(","))
            request.setAttribute("message", "incorrect data input, use . ");
        deal.setBudget(new BigDecimal(Double.parseDouble(budgetString)));
        contactService.createDeal(deal);
    }
}
