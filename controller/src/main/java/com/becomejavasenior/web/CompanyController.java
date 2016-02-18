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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Default71721 on 06.02.16.
 */
@WebServlet(name = "companyServlet",
        urlPatterns = {"/company"})

public class CompanyController extends HttpServlet {
    private final UserDao<User> userDao = DaoFactory.getUserDAO();
    private final PhaseDao<Phase> phaseDao = DaoFactory.getPhaseDao();
    /*Following lists were declared final to prevent unwanted changes in the concurrent environment.
    Current approach has to be changed as soon as Ajax tool for continuous load from DB will be implemented.*/
    private final List<User> userList = userDao.getAll();
    private final List<Phase> phaseList = phaseDao.getAll();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userList", userList);
        req.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap());
        req.setAttribute("phaseList", phaseList);

        req.getRequestDispatcher("/company/addCompany.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userList", userList);
        req.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap());
        req.setAttribute("phaseList", phaseList);

        createCompany(req);

        req.getRequestDispatcher("/company/addCompany.jsp")
                .forward(req, resp);
    }

    private void createCompany(HttpServletRequest req)
    {
        ArrayList<Integer> filesId = new ArrayList<>();
        Company company = new Company();
        UserDao<User> userDao = DaoFactory.getUserDAO();
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
        Comment comment = new Comment();
        CommentDao<Comment> commentDao = DaoFactory.getCommentDao();
        Contact contact = new Contact();

        company.setCompanyName(req.getParameter("companyName"));
        company.setAddress(req.getParameter("address"));
        company.setWebsite(req.getParameter("webAddress"));
        company.setEmail(req.getParameter("email"));
        company.setPhoneNumber(req.getParameter("phoneNumber"));
        company.setCreationTime(LocalDateTime.now());
        company.setResponsible(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleName"))));
        company.setCreatedBy(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleName"))));
        company.setDeleted(false);

        comment.setComment(req.getParameter("note"));
        comment.setCreationDate(LocalDateTime.now());
        company = companyDao.create(company);
        comment = commentDao.create(comment);
        companyDao.addCommentForCompany(comment, company);

        if(req.getParameter("nameSurname")!=null)
        {
            contact = createContact(req, company);
            if(req.getParameter("dealName")!=null)
            {
                quickAddDeal(req, company, contact);
            }
        }
    }

    private Contact createContact(HttpServletRequest req, Company company)
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
        contact.setCompanyId(company);

        return contactDao.create(contact);
    }

    private void quickAddDeal(HttpServletRequest req, Company company, Contact contact)
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
        deal.setCompany(company);
        deal.setContact(contact);
        deal.setDeleted(false);
        deal.setCreationDate(LocalDateTime.now());

        dealDao.create(deal);
    }
}
