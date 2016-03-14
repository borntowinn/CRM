package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.ContactEditServiceImpl;
import com.becomejavasenior.service.CompanyService;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "editContact",
        urlPatterns = {"/contact"})
@MultipartConfig
public class ContactEditServlet extends HttpServlet {
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
            dealComments.putAll(contactService.getDealComments(deal.getId()));
        }
        request.setAttribute("commentsDeal", dealComments);
        request.setAttribute("deals", contactService.dealsForContact(contactId));
        request.setAttribute("nameSurname", contact.getNameSurname());
        request.setAttribute("position", contact.getPosition());
        request.setAttribute("contactPhoneNumber", contact.getPhoneNumber());
        request.setAttribute("contactEmail", contact.getEmail());
        request.setAttribute("skype", contact.getSkype());
        request.setAttribute("resp", contact.getResponsible().getId());
        request.setAttribute("phoneType", contact.getPhoneType());
        request.setAttribute("tag", contact.getTags());
        Company company = contact.getCompanyId();
        request.setAttribute("companyName", company.getCompanyName());
        request.setAttribute("phoneNumber", company.getPhoneNumber());
        request.setAttribute("mail", company.getEmail());
        request.setAttribute("webAddr", company.getWebsite());
        request.setAttribute("addr", company.getAddress());

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
        if (request.getParameter("tags") != null) {
            try {
                updateTag(editContact, request);
            } catch (ClassNotFoundException e) {
                LOGGER.error("you try to update nonexistent object" + e);
                e.printStackTrace();
            }
        }
        editContact.setResponsible(contactService.userByPK(request.getParameter("responsible")));
        editContact.setPosition(request.getParameter("position"));
        editContact.setPhoneType(Integer.valueOf(request.getParameter("phoneType")));
        editContact.setPhoneNumber(request.getParameter("phoneNumber"));
        editContact.setEmail(request.getParameter("contactEmail"));
        editContact.setSkype(request.getParameter("skype"));
        String note = request.getParameter("note");
        try {
            if (note != null) {
                editContact.setCommentList(updateComment(note, editContact.getId()));
            }
            contactService.updateEntity(editContact);
            if (request.getParameter("fileData") != null) {
                editContact.setFiles(createFile(request));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("you try to update nonexistent object" + e);
            e.printStackTrace();
        }
        if (request.getParameter("addDeal") != null) {
            addDeal(request);
        }
    }

    private List<File> createFile(HttpServletRequest request) throws IOException, ServletException, ClassNotFoundException {
        File file = new File();
        List<File> files = new ArrayList<>();
        file.setCreationDate(LocalDateTime.now());
        Part filePart = request.getPart("fileData");
        InputStream fileContent = filePart.getInputStream();
        file.setFileName(filePart.getSubmittedFileName());
        String contentType = request.getContentType();
        byte array[];
        if ((contentType.contains("multipart/form-data"))) {
            array = IOUtils.toByteArray(fileContent);
            file.setFile(array);
        }
        contactService.createFile(file);
        request.setAttribute("message", "File was added successfully");
        files.add(file);
        return files;
    }

    private void updateTag(Contact contact, HttpServletRequest request) throws ClassNotFoundException {
        Tag tag = contactService.selectTag(contact.getId());
        tag.setTag(request.getParameter("tags"));
        contactService.updateEntity(tag);
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
