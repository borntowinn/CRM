package com.becomejavasenior.web;

import com.becomejavasenior.Company;
import com.becomejavasenior.Contact;
import com.becomejavasenior.dao.CompanyDao;
import com.becomejavasenior.dao.ContactDao;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Sergey on 12.02.2016.
 */

@WebServlet(
        name = "contactsServlet",
        urlPatterns = {"/contacts"}
)
public class ContactsServlet extends HttpServlet {
    private static final String VIEW_CONTACTS = "/contacts/contacts.jsp";
    private static final String CONTACTS_TABLE = "/contacts/contactsTable.jsp";
    private static final String COMPANIES_TABLE = "/contacts/companiesTable.jsp";
    private static final String REQ_PARAMETER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContactDao<Contact> contactDao = DaoFactory.getContactDAO();
        List<Contact> contacts = contactDao.getAll();
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
        List<Company> companies = companyDao.getAll();
        if (req.getParameter(REQ_PARAMETER) == null) {
            responsePreparation(req, resp, VIEW_CONTACTS, "contacts", contacts);
        } else if (req.getParameter(REQ_PARAMETER).toString().equals("showContacts")) {
            responsePreparation(req, resp, CONTACTS_TABLE, "contacts", contacts);
        } else if (req.getParameter(REQ_PARAMETER).toString().equals("showCompanies")) {
            responsePreparation(req, resp, COMPANIES_TABLE, "companies", companies);
        }
    }

    private void responsePreparation(HttpServletRequest req, HttpServletResponse resp,
                                     String viewClients, String attribute, List<?> clients)
            throws ServletException, IOException {
        req.setAttribute(attribute, clients);
        resp.setContentType("text/html");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewClients);
        requestDispatcher.forward(req, resp);
    }

}
