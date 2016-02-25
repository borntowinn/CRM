package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.File;
import com.becomejavasenior.impl.DealServiceImpl;
import com.becomejavasenior.service.CompanyService;
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
    private final DealServiceImpl dealService = new DealServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap());
        request.setAttribute("timeList", TaskService.getTimeList());
        request.setAttribute("periodMap", TaskService.getPeriodMap());
        request.setAttribute("typeMap", TaskService.getTypeMap());
        request.setAttribute("userMap", dealService.getUserMap());
        request.setAttribute("companyMap", dealService.getCompanyMap());
        request.setAttribute("contactMap", dealService.getContactMap());
        request.setAttribute("phaseMap", dealService.getPhaseMap());

        request.getRequestDispatcher(CREATE_DEAL)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("phoneTypeMap", CompanyService.getPhoneTypeMap());
        request.setAttribute("timeList", TaskService.getTimeList());
        request.setAttribute("periodMap", TaskService.getPeriodMap());
        request.setAttribute("typeMap", TaskService.getTypeMap());
        request.setAttribute("userMap", dealService.getUserMap());
        request.setAttribute("companyMap", dealService.getCompanyMap());
        request.setAttribute("contactMap", dealService.getContactMap());
        request.setAttribute("phaseMap", dealService.getPhaseMap());

        createDeal(request);
        request.getRequestDispatcher(CREATE_DEAL).forward(request, response);
    }

    private void createDeal(HttpServletRequest request) {
        Deal deal = new Deal();
        try {
            deal.setDealName(request.getParameter("dealName"));
            String budgetString = request.getParameter("budget");
            if (budgetString.contains(","))
                request.setAttribute("message", "incorrect data input, use . ");
            deal.setBudget(new BigDecimal(Double.parseDouble(budgetString)));
            deal.setCreatedBy(dealService.currentUser());
            deal.setPhase(dealService.phaseByPK(request.getParameter("phase")));
            deal.setResponsible(dealService.userByPK(request.getParameter("responsible")));
            deal.setCreationDate(LocalDateTime.now());
            Company company = dealService.companyByPK(request.getParameter("company"));
            if (company != null) {
                deal.setCompany(company);
            } else {
                company = createCompany(request);
                deal.setCompany(company);
            }
            Contact contact = dealService.contactByPK(request.getParameter("contact"));
            if (contact != null) {
                deal.setContact(contact);
            } else {
                deal.setContact(createContact(request, company));
            }
            deal.setDeleted(false);
            String note = request.getParameter("note");
            if (note != null) {
                deal.setCommentList(createComment(note));
            }
            if (request.getPart("fileData") != null)
                deal.setFileList(createFile(request));
            if (request.getParameter("addTask") != null) {
                createTask(request);
            }
            int dealId = ((Deal)dealService.createEntity(deal)).getId();
            int tag = createTag(request);
            dealService.executeInsert(tag, dealId);
        } catch (IOException | ServletException e) {
            LOGGER.error("can't upload file" + e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOGGER.error("createEntity method throws exception. you try to create nonexistent object " + e);
            e.printStackTrace();
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
        dealService.createEntity(file);
        request.setAttribute("message", "File was added successfully");
        files.add(file);
        return files;
    }

    private List<Comment> createComment(String note) throws ClassNotFoundException {
        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setCreationDate(LocalDateTime.now());
        comment.setComment(note);
        dealService.createEntity(comment);
        comments.add(comment);
        return comments;
    }

    private int createTag(HttpServletRequest request) throws ClassNotFoundException {
        Tag tag = new Tag();
        tag.setTag(request.getParameter("tags"));
        return ((Tag) dealService.createEntity(tag)).getId();
    }

    private void createTask(HttpServletRequest request) throws ClassNotFoundException {
        Task task = new Task();
        task.setResponsible(dealService.userByPK(request.getParameter("responsibleName")));
        task.setAuthor(dealService.currentUser());
        task.setCreationTime(LocalDateTime.now());
        task.setDone(false);
        task.setDeleted(false);
        task.setPeriod(request.getParameter("periodName"));
        dealService.createEntity(task);
        request.setAttribute("message", "Task was added successfully");
    }

    private Company createCompany(HttpServletRequest request) throws ClassNotFoundException {
        Company company = new Company();
        company.setCompanyName(request.getParameter("companyName"));
        company.setPhoneNumber(request.getParameter("phoneNumber"));
        company.setEmail(request.getParameter("email"));
        company.setWebsite(request.getParameter("website"));
        company.setAddress(request.getParameter("address"));
        company.setResponsible(dealService.currentUser());
        company.setCreatedBy(dealService.currentUser());
        company.setDeleted(false);
        company.setCreationTime(LocalDateTime.now());
        return (Company) dealService.createEntity(company);
    }

    private Contact createContact(HttpServletRequest request, Company company) throws ClassNotFoundException {
        Contact contact = new Contact();
        contact.setNameSurname(request.getParameter("nameSurname"));
        if (company == null) {
            contact.setCompanyId(dealService.companyByPK(request.getParameter("company")));
        } else {
            contact.setCompanyId(company);
        }
        contact.setPhoneType(Integer.valueOf(request.getParameter("phoneType")));
        contact.setPhoneNumber(request.getParameter("phoneNumber"));
        contact.setEmail(request.getParameter("email"));
        contact.setSkype(request.getParameter("skype"));
        contact.setCreatedBy(dealService.currentUser());
        contact.setResponsible(dealService.currentUser());
        contact.setDeleted(false);
        contact.setCreationTime(LocalDateTime.now());
        return (Contact) dealService.createEntity(contact);
    }
}
