package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import com.becomejavasenior.service.CompanyService;
import com.becomejavasenior.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by Default71721 on 06.02.16.
 */
@WebServlet(name = "companyServlet",
        urlPatterns = {"/company"})

public class CompanyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userMap", TaskService.getUserMap());
        req.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap());
        req.setAttribute("companyMap", CompanyService.getCompanyMap());
        req.setAttribute("phaseMap", CompanyService.getPhaseMap());

        req.getRequestDispatcher("/company/addCompany.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userMap", CompanyService.getUserMap());
        req.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap());
        req.setAttribute("companyMap", CompanyService.getCompanyMap());
        req.setAttribute("phaseMap", CompanyService.getPhaseMap());

        switch (req.getParameter("action"))
        {
            case "createCompany":
                createCompany(req);
                break;
            case "createContact":
                createContact(req);
                break;
            case "quickAddDeal":
                quickAddDeal(req);
                break;
        }
        req.getRequestDispatcher("/company/addCompany.jsp")
                .forward(req, resp);
    }

    private void createCompany(HttpServletRequest req)
    {
        Company company = new Company();
        UserDao<User> userDao = DaoFactory.getUserDAO();
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();

        company.setCompanyName(req.getParameter("companyName"));
        company.setAddress(req.getParameter("address"));
        company.setWebsite(req.getParameter("webAddress"));
        company.setEmail(req.getParameter("email"));
        company.setPhoneNumber(req.getParameter("phoneNumber"));
        company.setCreationTime(LocalDateTime.now());
        company.setResponsible(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleName"))));
        company.setCreatedBy(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleName"))));
        company.setDeleted(false);

        companyDao.create(company);

        userDao.closeCurrentConnection();
        companyDao.closeCurrentConnection();
    }

    private void createContact(HttpServletRequest req)
    {
        ContactDao<Contact> contactDao = DaoFactory.getContactDao();
        UserDao<User> userDao = DaoFactory.getUserDAO();
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
        Contact contact = new Contact();

        contact.setDeleted(false);
        contact.setResponsible(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleNameContact"))));
        contact.setCreatedBy(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleNameContact"))));
        contact.setCreationTime(LocalDateTime.now());
        contact.setPosition(req.getParameter("position"));
        contact.setNameSurname(req.getParameter("nameSurname"));
        contact.setPhoneNumber(req.getParameter("contactPhoneNumber"));
        contact.setEmail(req.getParameter("contactEmail"));
        contact.setSkype(req.getParameter("skype"));
        contact.setPhoneType(Integer.valueOf(req.getParameter("phoneType")));
        contact.setCompanyId(companyDao.getByPK(Integer.valueOf(req.getParameter("companyId"))));

        contactDao.create(contact);
        contactDao.closeCurrentConnection();
        companyDao.closeCurrentConnection();
    }

    private void quickAddDeal(HttpServletRequest req)
    {
        PhaseDao<Phase> phaseDao = DaoFactory.getPhaseDao();
        DealDao<Deal> dealDao = DaoFactory.getDealDao();
        UserDao<User> userDao = DaoFactory.getUserDAO();
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
        ContactDao<Contact> contactDao = DaoFactory.getContactDao();
        Deal deal = new Deal();

        deal.setDealName(req.getParameter("dealName"));
        deal.setBudget(BigDecimal.valueOf(Double.valueOf(req.getParameter("budget"))));
        deal.setPhase(phaseDao.getByPK(Integer.valueOf(req.getParameter("phase"))));
        deal.setResponsible(userDao.getByPK(1));
        deal.setCreatedBy(userDao.getByPK(1));
        deal.setCompany(companyDao.getByPK(1));
        deal.setContact(contactDao.getByPK(1));
        deal.setDeleted(false);
        deal.setCreationDate(LocalDateTime.now());

        dealDao.create(deal);
        dealDao.closeCurrentConnection();
        phaseDao.closeCurrentConnection();
        userDao.closeCurrentConnection();
        contactDao.closeCurrentConnection();
        companyDao.closeCurrentConnection();
    }
}
