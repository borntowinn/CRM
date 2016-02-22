package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.File;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;
import com.becomejavasenior.service.CompanyService;
import com.becomejavasenior.service.GeneralService;
import com.becomejavasenior.service.TaskService;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "dealServlet",
        urlPatterns = {"/deal"}
)
@MultipartConfig
public class DealController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DealController.class);

    private final String CREATE_DEAL = "/deal/addDeal.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap());
        request.setAttribute("timeList", TaskService.getTimeList());
        request.setAttribute("periodMap", TaskService.getPeriodMap());
        request.setAttribute("typeMap", TaskService.getTypeMap());
        request.setAttribute("userMap", GeneralService.getUserMap());
        request.setAttribute("companyMap", GeneralService.getCompanyMap());
        request.setAttribute("contactMap", GeneralService.getContactMap());
        request.setAttribute("phaseMap", GeneralService.getPhaseMap());

        request.getRequestDispatcher(CREATE_DEAL)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap());
        request.setAttribute("timeList", TaskService.getTimeList());
        request.setAttribute("periodMap", TaskService.getPeriodMap());
        request.setAttribute("typeMap", TaskService.getTypeMap());
        request.setAttribute("userMap", GeneralService.getUserMap());
        request.setAttribute("companyMap", GeneralService.getCompanyMap());
        request.setAttribute("contactMap", GeneralService.getContactMap());
        request.setAttribute("phaseMap", GeneralService.getPhaseMap());

        createDeal(request);
        request.getRequestDispatcher(CREATE_DEAL).forward(request, response);
    }

    private void createDeal(HttpServletRequest request) {
        UserDao<User> userDao = DaoFactory.getUserDAO();
        PhaseDao<Phase> phaseDao = DaoFactory.getPhaseDao();
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
        ContactDao<Contact> contactDao = DaoFactory.getContactDAO();
        DealDao<Deal> dealDao = DaoFactory.getDealDao();
        Deal deal = new Deal();
        try {
            deal.setDealName(request.getParameter("dealName"));
            String budgetString = request.getParameter("budget");
            if (budgetString.contains(","))
                throw new IllegalArgumentException("incorrect data input, use . ");
            deal.setBudget(new BigDecimal(Double.parseDouble(budgetString)));
            deal.setCreatedBy(userDao.getByPK(Integer.valueOf(request.getParameter("responsible"))));
            deal.setPhase(phaseDao.getByPK(Integer.valueOf(request.getParameter("phase"))));
            deal.setResponsible(userDao.getByPK(Integer.valueOf(request.getParameter("responsible"))));
            deal.setCreationDate(LocalDateTime.now());
            String companyParameter = request.getParameter("company");
            Company company;
            if (!companyParameter.equals("")) {
                company = companyDao.getByPK(Integer.valueOf(companyParameter));
                deal.setCompany(company);
            } else {
                company = createCompany(request);
                deal.setCompany(company);
            }
            String contactParameter = request.getParameter("contact");
            if (!contactParameter.equals("")) {
                deal.setContact(contactDao.getByPK(Integer.valueOf(contactParameter)));
            } else {
                deal.setContact(createContact(request, company));
            }
            deal.setDeleted(false);
            List<Comment> comments = new ArrayList<>();
            String note = request.getParameter("note");
            if (note != null) {
                Comment comment = createComment(note);
                comments.add(comment);
                deal.setCommentList(comments);
            }
            if (request.getPart("fileData") != null)
                deal.setFileList(createFile(request));
            if (request.getParameter("addTask") != null) {
                createTask(request);
            }
            dealDao.create(deal);
            final String INSERT_INTO = "INSERT INTO tags_to_deal (tag_id, deal_id) VALUES";
            dealDao.executeQuery(INSERT_INTO + " (" + createTag(request) + ", " + deal.getId() + ");");
        } catch (IllegalArgumentException | IOException | ServletException e) {
            LOGGER.error("incorrect data input" + e);
            e.printStackTrace();
        }
    }

    private List<File> createFile(HttpServletRequest request) throws IOException, ServletException {
        FileDao<File> fileDao = DaoFactory.getFileDao();
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
        fileDao.create(file);
        request.setAttribute("message", "File was added successfully");
        files.add(file);
        return files;
    }

    private Comment createComment(String note) {
        CommentDao<Comment> commentDao = DaoFactory.getCommentDao();
        Comment comment = new Comment();
        comment.setCreationDate(LocalDateTime.now());
        comment.setComment(note);
        return commentDao.create(comment);
    }

    private int createTag(HttpServletRequest request) {
        TagDao<Tag> tagDao = DaoFactory.getTagDao();
        Tag tag = new Tag();
        tag.setTag(request.getParameter("tags"));
        return tagDao.create(tag).getId();
    }

    private void createTask(HttpServletRequest request) {
        UserDao<User> userDAO = DaoFactory.getUserDAO();
        TaskDao<Task> taskDao = DaoFactory.getTaskDao();
        Task task = new Task();
        task.setResponsible(userDAO.getByPK(Integer.valueOf(request.getParameter("responsibleName"))));
        task.setAuthor(userDAO.getByPK(1));
        task.setCreationTime(LocalDateTime.now());
        task.setDone(false);
        task.setDeleted(false);
        task.setPeriod(request.getParameter("periodName"));
        taskDao.create(task);
        request.setAttribute("message", "Task was added successfully");
    }

    private Company createCompany(HttpServletRequest request) {
        UserDao<User> userDao = DaoFactory.getUserDAO();
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
        Company company = new Company();
        company.setCompanyName(request.getParameter("companyName"));
        company.setPhoneNumber(request.getParameter("phoneNumber"));
        company.setEmail(request.getParameter("email"));
        company.setWebsite(request.getParameter("website"));
        company.setAddress(request.getParameter("address"));
        company.setResponsible(userDao.getByPK(1));
        company.setCreatedBy(userDao.getByPK(1));
        company.setDeleted(false);
        company.setCreationTime(LocalDateTime.now());
        return  companyDao.create(company);
    }

    private Contact createContact(HttpServletRequest request, Company company) {
        CompanyDao<Company> companyDao = DaoFactory.getCompanyDAO();
        ContactDao<Contact> contactDao = DaoFactory.getContactDAO();
        UserDao<User> userDao = DaoFactory.getUserDAO();
        Contact contact = new Contact();
        contact.setNameSurname(request.getParameter("nameSurname"));
        if (company == null) {
            contact.setCompanyId(companyDao.getByPK(Integer.valueOf(request.getParameter("company"))));
        } else {
            contact.setCompanyId(company);
        }
        contact.setPhoneType(Integer.valueOf(request.getParameter("phoneType")));
        contact.setPhoneNumber(request.getParameter("phoneNumber"));
        contact.setEmail(request.getParameter("email"));
        contact.setSkype(request.getParameter("skype"));
        contact.setCreatedBy(userDao.getByPK(1));
        contact.setResponsible(userDao.getByPK(1));
        contact.setDeleted(false);
        contact.setCreationTime(LocalDateTime.now());
        return contactDao.create(contact);
    }
}
