package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.CompanyDao;
import com.becomejavasenior.dao.ContactDao;
import com.becomejavasenior.dao.TaskDao;
import com.becomejavasenior.dao.UserDao;
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


    @Override
    public void init() throws ServletException {
        this.contactDao = DaoFactory.getContactDAO();
        this.userDao = DaoFactory.getUserDAO();
        this.taskDao = DaoFactory.getTaskDao();
        this.companyDao = DaoFactory.getCompanyDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // get addContactForm parameters
        String name = request.getParameter("contact_name");
        Integer responsible = Integer.valueOf(request.getParameter("responsible"));
        Integer phoneType = Integer.valueOf(request.getParameter("phone_type"));
        String phone = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String skype = request.getParameter("skype");
        String position = request.getParameter("position");
        String comment = request.getParameter("note");

        //File upload
        Part filePart = request.getPart("file");

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


        newContact.setCompanyId(new Company());

        Contact contact = (Contact) contactDao.create(newContact);
        if(!comment.isEmpty()){
            Comment commentNew = new Comment();
            commentNew.setComment(comment);
            commentNew.setCreationDate(LocalDateTime.now());
            contactDao.addCommentToContact(commentNew, contact.getId());
        }

        if(!(filePart == null)){
            File file = new File();
            file.setFileName(filePart.getSubmittedFileName());
            file.setCreationDate(LocalDateTime.now());
            file.setFile(convertPartToBlob(filePart));
        }

        response.sendRedirect("dashboard");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userDao.getAll();
        List<String> taskTypeList = taskDao.getTaskTypes();
        List<Company> companyList = companyDao.getAll();
        request.setAttribute("userList", userList);
        request.setAttribute("taskTypeList", taskTypeList);
        request.setAttribute("companyList", companyList);
        response.setContentType("text/html");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addContact.jsp");
        requestDispatcher.forward(request, response);

    }

    private Byte[] convertPartToBlob(Part filePart) {
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
        byte[] primBytes = buffer.toByteArray();
        Byte[] objectBytes = new Byte[primBytes.length];
        for(int i = 0; i < objectBytes.length; i++) {
            objectBytes[i] = primBytes[i];
        }
        return objectBytes ;
    }
}
