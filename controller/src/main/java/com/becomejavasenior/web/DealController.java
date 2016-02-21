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

    private final UserDao<User> USER_DAO = DaoFactory.getUserDAO();
    private final PhaseDao<Phase> PHASE_DAO = DaoFactory.getPhaseDao();
    private final TaskDao<Task> TASK_DAO = DaoFactory.getTaskDao();
    private final CompanyDao<Company> COMPANY_DAO = DaoFactory.getCompanyDAO();
    private final ContactDao<Contact> CONTACT_DAO = DaoFactory.getContactDAO();
    private final DealDao<Deal> DEAL_DAO = DaoFactory.getDealDao();
    private final TagDao<Tag> TAG_DAO = DaoFactory.getTagDao();
    private final CommentDao<Comment> COMMENT_DAO = DaoFactory.getCommentDao();
    private final FileDao<File> FILE_DAO = DaoFactory.getFileDao();


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
        Deal deal = new Deal();
        try {
            deal.setDealName(request.getParameter("dealName"));
            String budgetString = request.getParameter("budget");
            if (budgetString.contains(","))
                throw new IllegalArgumentException("incorrect data input, use . ");
            deal.setBudget(new BigDecimal(Double.parseDouble(budgetString)));
            deal.setCreatedBy(USER_DAO.getByPK(Integer.valueOf(request.getParameter("responsible"))));
            deal.setPhase(PHASE_DAO.getByPK(Integer.valueOf(request.getParameter("phase"))));
            deal.setResponsible(USER_DAO.getByPK(Integer.valueOf(request.getParameter("responsible"))));
            deal.setCreationDate(LocalDateTime.now());
            String companyParameter = request.getParameter("company");
            Company company;
            if (!companyParameter.equals("")) {
                company = COMPANY_DAO.getByPK(Integer.valueOf(companyParameter));
                deal.setCompany(company);
            } else {
                company = createCompany(request);
                deal.setCompany(company);
            }
            String contactParameter = request.getParameter("contact");
            if (!contactParameter.equals("")) {
                deal.setContact(CONTACT_DAO.getByPK(Integer.valueOf(contactParameter)));
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
            DEAL_DAO.create(deal);
            final String INSERT_INTO = "INSERT INTO tags_to_deal (tag_id, deal_id) VALUES";
            DEAL_DAO.executeQuery(INSERT_INTO + " (" + createTag(request) + ", " + deal.getId() + ");");
        } catch (IllegalArgumentException | IOException | ServletException e) {
            LOGGER.error("incorrect data input" + e);
            e.printStackTrace();
        }
    }

    private List<File> createFile(HttpServletRequest request) throws IOException, ServletException {
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
        FILE_DAO.create(file);
        request.setAttribute("message", "File was added successfully");
        files.add(file);
        return files;
    }

    private Comment createComment(String note) {
        Comment comment = new Comment();
        comment.setCreationDate(LocalDateTime.now());
        comment.setComment(note);
        return COMMENT_DAO.create(comment);
    }

    private int createTag(HttpServletRequest request) {
        Tag tag = new Tag();
        tag.setTag(request.getParameter("tags"));
        return TAG_DAO.create(tag).getId();
    }

    private void createTask(HttpServletRequest request) {
        Task task = new Task();
        task.setResponsible(USER_DAO.getByPK(Integer.valueOf(request.getParameter("responsibleName"))));
        task.setAuthor(USER_DAO.getByPK(1));
        task.setCreationTime(LocalDateTime.now());
        task.setDone(false);
        task.setDeleted(false);
        task.setPeriod(request.getParameter("periodName"));
        TASK_DAO.create(task);
        request.setAttribute("message", "Task was added successfully");
    }

    private Company createCompany(HttpServletRequest request) {
        Company company = new Company();
        company.setCompanyName(request.getParameter("companyName"));
        company.setPhoneNumber(request.getParameter("phoneNumber"));
        company.setEmail(request.getParameter("email"));
        company.setWebsite(request.getParameter("website"));
        company.setAddress(request.getParameter("address"));
        company.setResponsible(USER_DAO.getByPK(1));
        company.setCreatedBy(USER_DAO.getByPK(1));
        company.setDeleted(false);
        company.setCreationTime(LocalDateTime.now());
        return  COMPANY_DAO.create(company);
    }

    private Contact createContact(HttpServletRequest request, Company company) {
        Contact contact = new Contact();
        contact.setNameSurname(request.getParameter("nameSurname"));
        if (company == null) {
            contact.setCompanyId(COMPANY_DAO.getByPK(Integer.valueOf(request.getParameter("company"))));
        } else {
            contact.setCompanyId(company);
        }
        contact.setPhoneType(Integer.valueOf(request.getParameter("phoneType")));
        contact.setPhoneNumber(request.getParameter("phoneNumber"));
        contact.setEmail(request.getParameter("email"));
        contact.setSkype(request.getParameter("skype"));
        contact.setCreatedBy(USER_DAO.getByPK(1));
        contact.setResponsible(USER_DAO.getByPK(1));
        contact.setDeleted(false);
        contact.setCreationTime(LocalDateTime.now());
        return CONTACT_DAO.create(contact);
    }
}
