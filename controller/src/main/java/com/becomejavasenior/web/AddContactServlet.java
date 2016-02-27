package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ipvinner on 02.02.2016.
 */
@MultipartConfig
public class AddContactServlet extends HttpServlet {

    public static final String CONTACTS_URL = "contacts";
    public static final String ADD_CONTACT_URL = "addContact.jsp";

    private ContactDao contactDao;
    private UserDao userDao;
    private TaskDao taskDao;
    private CompanyDao companyDao;
    private PhaseDao phaseDao;
    private DealDao dealDao;
    private TagDao tagDao;


    @Override
    public void init() throws ServletException {
        this.contactDao = DaoFactory.getContactDAO();
        this.userDao = DaoFactory.getUserDAO();
        this.taskDao = DaoFactory.getTaskDao();
        this.companyDao = DaoFactory.getCompanyDAO();
        this.phaseDao = DaoFactory.getPhaseDao();
        this.dealDao = DaoFactory.getDealDao();
        this.tagDao = DaoFactory.getTagDao();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // create contact for insert to db
        Contact newContact = buildContact(request);
        Contact contact = (Contact) contactDao.create(newContact);

        addTagsToContact(request, contact);
        addCommentToContact(request, contact);
        addFilesToContact(request, contact);
        addDeal(request, contact);
        addTask(request, contact);

        response.sendRedirect(CONTACTS_URL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userDao.getAll();
        List<String> taskTypeList = taskDao.getTaskTypes();
        List<Company> companyList = companyDao.getAll();
        List<Phase> phaseList = phaseDao.getAll();
        List<String > taskPeriodList = taskDao.getTaskPeriods();
        List<String> tagList = contactDao.getAllTegs();

        request.setAttribute("userList", userList);
        request.setAttribute("taskTypeList", taskTypeList);
        request.setAttribute("companyList", companyList);
        request.setAttribute("phaseList", phaseList);
        request.setAttribute("hourList", getHoursList());
        request.setAttribute("taskPeriods", taskPeriodList);
        request.setAttribute("tagList", tagList);

        response.setContentType("text/html");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADD_CONTACT_URL);
        requestDispatcher.forward(request, response);
    }

    private byte[] convertPartToBlob(Part filePart) {
        InputStream fileContent = null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            fileContent = filePart.getInputStream();
            int nRead;
            byte[] data = new byte[16384];
            while ((nRead = fileContent.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toByteArray() ;
    }

    private List<String> getHoursList(){
        List<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if(i < 10){
                hours.add("0" + i + ":" + "00");
            }else{
                hours.add(i + ":" + "00");
            }
        }
        return hours;
    }

    private Contact buildContact(HttpServletRequest request){
        Contact newContact = new Contact();

        String companyName = (request.getParameter("company_name") != null) ? request.getParameter("company_name") : null;
        Integer chosenCompanyId = (request.getParameter("company") != null) ? Integer.valueOf(request.getParameter("company")) : null;

        // set Company for contact:
        if(chosenCompanyId != null){
            Company chosenCompany = (Company) companyDao.getByPK(chosenCompanyId);
            newContact.setCompanyId(chosenCompany);
        }

        if(companyName != null && !companyName.isEmpty()){
            newContact.setCompanyId(buildCompany(request));
        }

        // get addContactForm parameters
        String name = request.getParameter("contact_name");
        Integer responsible = (request.getParameter("responsible") != null) ?  Integer.valueOf(request.getParameter("responsible")) : 0;
        Integer phoneType = (request.getParameter("phone_type") != null) ? Integer.valueOf(request.getParameter("phone_type")) : 0;
        String phone = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String skype = request.getParameter("skype");
        String position = request.getParameter("position");

        newContact.setNameSurname(name);
        newContact.setPhoneType(phoneType);
        newContact.setPhoneNumber(phone);
        newContact.setEmail(email);
        newContact.setSkype(skype);
        newContact.setPosition(position);
        newContact.setCreationTime(LocalDateTime.now());
        newContact.setCreatedBy((User) userDao.getByPK(responsible));
        newContact.setDeleted(false);
        newContact.setResponsible((User) userDao.getByPK(responsible));

        return newContact;
    }

    private void addTagsToContact(HttpServletRequest request, Contact contact) {
        String tags = request.getParameter("tags");
        if(!tags.isEmpty() && tags != null){
            List<Tag> tagList = getTagListFromString(tags);
            for (Tag tag : tagList) {
                tagDao.create(tag);
            }
        }
    }

    private void addCommentToContact(HttpServletRequest request, Contact contact){
        String comment = request.getParameter("note");
        if(!comment.isEmpty()){
            Comment commentNew = new Comment();
            commentNew.setComment(comment);
            commentNew.setCreationDate(LocalDateTime.now());
            contactDao.addCommentToContact(commentNew, contact.getId());
        }
    }

    private void addFilesToContact(HttpServletRequest request, Contact contact) throws IOException, ServletException {
        //File upload
        Collection<Part> parts = request.getParts();

        // Adding files to DB
        for (Part part : parts) {
            if(part.getName().equals("file") && part.getContentType() != null && !part.getSubmittedFileName().isEmpty()){
                File file = new File();
                file.setFileName(part.getSubmittedFileName());
                file.setCreationDate(LocalDateTime.now());
                file.setFile(convertPartToBlob(part));
                contactDao.addFileToContact(file, contact.getId());
            }
        }
    }

    private Company buildCompany(HttpServletRequest request){
        // Company
        Company newCompany = new Company();
        Integer company_id = (request.getParameter("company_id") != null) ? Integer.valueOf(request.getParameter("company_id")) : null;
        String companyName = request.getParameter("company_name");
        String companyPhone = request.getParameter("company_phone");
        String companyWebAddress = request.getParameter("web_address");
        String companyAddress = request.getParameter("company_address");

        Integer responsible = (request.getParameter("responsible") != null) ?  Integer.valueOf(request.getParameter("responsible")) : 0;

        newCompany.setCompanyName(companyName);
        if (companyPhone != null) newCompany.setPhoneNumber(companyPhone);
        if (companyWebAddress != null ) newCompany.setWebsite(companyWebAddress);
        if (companyAddress != null) newCompany.setAddress(companyAddress);
        newCompany.setResponsible((User) userDao.getByPK(responsible));
        newCompany.setDeleted(false);
        newCompany.setCreationTime(LocalDateTime.now());
        Company insertedCompany = (Company) companyDao.create(newCompany);
        return insertedCompany;
    }

    private void addDeal(HttpServletRequest request, Contact contact){
        String dealName = request.getParameter("deal_name");

        // Add deal to contact
        if(dealName != null && !dealName.isEmpty()){
            Deal newDeal = new Deal();
            Integer dealPhase = (request.getParameter("deal_phase") != null) ? Integer.valueOf(request.getParameter("deal_phase")) : 0;
            BigDecimal dealBudget = (request.getParameter("deal_budget") != null) ? BigDecimal.valueOf(Long.valueOf(request.getParameter("deal_budget"))) : BigDecimal.ZERO;
            Integer responsible = (request.getParameter("responsible") != null) ?  Integer.valueOf(request.getParameter("responsible")) : 0;

            newDeal.setDealName(dealName);
            if (dealPhase != 0) newDeal.setPhase( (Phase) phaseDao.getByPK(dealPhase));
            if (dealBudget != BigDecimal.ZERO) newDeal.setBudget(dealBudget);
            newDeal.setCreationDate(LocalDateTime.now());
            newDeal.setDeleted(false);
            newDeal.setContact(contact);
            newDeal.setCreatedBy((User) userDao.getByPK(responsible)); // temp value, will be authorized user here
            newDeal.setResponsible((User) userDao.getByPK(responsible));
            dealDao.create(newDeal);
        }
    }



    private void addTask(HttpServletRequest request, Contact contact){
        String taskName = request.getParameter("task_name");
        // Add task to contact
        if(taskName != null && !taskName.isEmpty()){
            Task newTask = new Task();
            String dateTimeTo = request.getParameter("datetime_to");
            String taskPeriod = request.getParameter("task_period");
            Integer taskResponsible = Integer.valueOf(request.getParameter("task_responsible"));
            String taskType = request.getParameter("task_type");

            newTask.setTaskName(taskName);
            if(dateTimeTo != null) newTask.setPlanTime(LocalDateTime.parse(dateTimeTo));
            if(taskPeriod != null) newTask.setPeriod(taskPeriod);
            if(taskType != null) newTask.setTaskType(taskType);
            newTask.setResponsible((User) userDao.getByPK(taskResponsible));
            newTask.setAuthor((User) userDao.getByPK(taskResponsible)); // temporary, when authorization will done will be changed to user from session
            newTask.setCreationTime(LocalDateTime.now());
            newTask.setDeleted(false);
            newTask.setDone(false);
            newTask.setContact(contact);
            taskDao.create(newTask);
        }
    }

    private List<Tag> getTagListFromString(String tagsString){
        List<Tag> tags = new LinkedList<>();
        String[] splittedString = tagsString.split(",");
        for (int i = 0; i < splittedString.length; i++) {
            Tag newTag = new Tag();
            newTag.setTag(splittedString[i]);
            tags.add(newTag);
        }
        return tags;
    }
}
