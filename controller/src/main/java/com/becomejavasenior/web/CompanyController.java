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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        req.setAttribute("periodMap", TaskService.getPeriodMap());
        req.setAttribute("timeList", TaskService.getTimeList());
        req.setAttribute("typeMap", TaskService.getTypeMap());

        req.getRequestDispatcher("/company/addCompany.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userList", userList);
        req.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap());
        req.setAttribute("phaseList", phaseList);
        req.setAttribute("periodMap", TaskService.getPeriodMap());
        req.setAttribute("timeList", TaskService.getTimeList());
        req.setAttribute("typeMap", TaskService.getTypeMap());

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
        company.setResponsible(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleForCompany"))));
        /*Must be changed to real creator as soon as user sessions will be implemented*/
        company.setCreatedBy(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleForCompany"))));
        company.setDeleted(false);
        company = companyDao.create(company);

        if (!req.getParameter("note").equals("default")) {
            comment.setComment(req.getParameter("note"));
            comment.setCreationDate(LocalDateTime.now());
            comment.setCompanyId(company);
            commentDao.create(comment);
        }

        if(!req.getParameter("nameSurname").equals("default"))
        {
            contact = createContact(req, company);
            if(!req.getParameter("dealName").equals("default"))
            {
                quickAddDeal(req, company, contact);
            }
            quickSubmitTask(req, company, contact);
        }
    }

    private Contact createContact(HttpServletRequest req, Company company)
    {
        ContactDao<Contact> contactDao = DaoFactory.getContactDao();
        UserDao<User> userDao = DaoFactory.getUserDAO();
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
        Contact contact = new Contact();

        contact.setDeleted(false);
        contact.setResponsible(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleForContact"))));
        /*Must be changed to real creator as soon as user sessions will be implemented*/
        contact.setCreatedBy(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleForContact"))));
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
        /*Must be changed to real creator as soon as user sessions will be implemented*/
        deal.setCreatedBy(userDao.getByPK(1));
        deal.setCompany(company);
        deal.setContact(contact);
        deal.setDeleted(false);
        deal.setCreationDate(LocalDateTime.now());

        dealDao.create(deal);
    }

    private void quickSubmitTask(HttpServletRequest req, Company company, Contact contact) {
        Task task = new Task();
        UserDao<User> userDao= DaoFactory.getUserDAO();

        task.setTaskName("quicklySubmittedTask");
        task.setComment(req.getParameter("comment"));
        /*Must be changed to real creator as soon as user sessions will be implemented*/
        task.setAuthor(userDao.getByPK(1));
        task.setDeleted(false);
        if (req.getParameter("periodName").equals("default")) {
            return;
        }
        task.setPeriod(req.getParameter("periodName"));
        task.setResponsible(userDao.getByPK(Integer.valueOf(req.getParameter("responsibleForDeal"))));

        task.setCompany(company);
        task.setContact(contact);
        LocalDate date = (!req.getParameter("dateName").isEmpty()) ? LocalDate.parse(req.getParameter("dateName")) : LocalDate.now();
        LocalTime time = (req.getParameter("timeName") != null) ? LocalTime.parse(req.getParameter("timeName")) : LocalTime.MIDNIGHT;
        LocalDateTime planTime = LocalDateTime.of(date, time);
        task.setPlanTime(planTime);
        task.setTaskType(req.getParameter("typeName"));
    }
}
