package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.*;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
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
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ipvinner on 02.02.2016.
 */
@MultipartConfig
public class AddContactServlet extends HttpServlet {

    private ContactDao contactDao;
    private UserDao userDao;
    private TaskDao taskDao;
    private CompanyDao companyDao;
    private PhaseDao phaseDao;
    private DealDao dealDao;


    @Override
    public void init() throws ServletException {
        this.contactDao = DaoFactory.getContactDAO();
        this.userDao = DaoFactory.getUserDAO();
        this.taskDao = DaoFactory.getTaskDao();
        this.companyDao = DaoFactory.getCompanyDAO();
        this.phaseDao = DaoFactory.getPhaseDao();
        this.dealDao = DaoFactory.getDealDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // get addContactForm parameters
        String name = request.getParameter("contact_name");
        Integer responsible = (request.getParameter("responsible") != null) ?  Integer.valueOf(request.getParameter("responsible")) : 0;
        Integer phoneType = (request.getParameter("phone_type") != null) ? Integer.valueOf(request.getParameter("phone_type")) : 0;
        String phone = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String skype = request.getParameter("skype");
        String position = request.getParameter("position");
        String comment = request.getParameter("note");

        Integer company_id = (request.getParameter("company_id") != null) ? Integer.valueOf(request.getParameter("company_id")) : null;
        String companyName = request.getParameter("company_name");
        String companyPhone = request.getParameter("company_phone");
        String companyWebAddress = request.getParameter("web_address");
        String companyAddress = request.getParameter("company_address");



        //File upload
        Collection<Part> parts = request.getParts();

        // create contact for insert to db
        Contact newContact = new Contact();
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

        // set Company for contact:
        if(company_id != null && company_id != 0){
            Company chosenCompany = (Company) companyDao.getByPK(company_id);
            newContact.setCompanyId(chosenCompany);
        }else if(company_id != null && company_id == 0){
            Company newCompany = new Company();
            newCompany.setCompanyName(companyName);
            if (companyPhone != null) newCompany.setPhoneNumber(companyPhone);
            newContact.setCompanyId(new Company());
        }

        Contact contact = (Contact) contactDao.create(newContact);

        // Fast adding deal


        if(!comment.isEmpty()){
            Comment commentNew = new Comment();
            commentNew.setComment(comment);
            commentNew.setCreationDate(LocalDateTime.now());
            contactDao.addCommentToContact(commentNew, contact.getId());
        }

        for (Part part : parts) {
            if(part.getContentType() != null){
                File file = new File();
                file.setFileName(part.getSubmittedFileName());
                file.setCreationDate(LocalDateTime.now());
                file.setFile(convertPartToBlob(part));
                contactDao.addFileToContact(file, contact.getId());
            }
        }
        response.sendRedirect("dashboard");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userDao.getAll();
        List<String> taskTypeList = taskDao.getTaskTypes();
        List<Company> companyList = companyDao.getAll();
        List<Phase> phaseList = phaseDao.getAll();

        request.setAttribute("userList", userList);
        request.setAttribute("taskTypeList", taskTypeList);
        request.setAttribute("companyList", companyList);
        request.setAttribute("phaseList", phaseList);

        response.setContentType("text/html");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addContact.jsp");
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
}
