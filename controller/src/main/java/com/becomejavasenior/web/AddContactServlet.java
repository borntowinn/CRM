package com.becomejavasenior.web;

import com.becomejavasenior.Company;
import com.becomejavasenior.Contact;
import com.becomejavasenior.User;
import com.becomejavasenior.dao.ContactDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.dao.jdbc.factory.ConnectionFactory;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ipvinner on 02.02.2016.
 */
public class AddContactServlet extends HttpServlet {

    private HttpServletRequest postRequest;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ContactDao contactDao = DaoFactory.getContactDAO();
        UserDao userDao = DaoFactory.getUserDAO();
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("contact_name");
        Integer responsible = Integer.valueOf(request.getParameter("responsible"));
        Integer phoneType = Integer.valueOf(request.getParameter("phone_type"));
        String phone = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String skype = request.getParameter("skype");
        String position = request.getParameter("position");

        Contact newContact = new Contact();
        newContact.setNameSurname(name);
        newContact.setPhoneType(phoneType);
        newContact.setPhoneNumber(phone);
        newContact.setEmail(email);
        newContact.setSkype(skype);
        newContact.setPosition(position);
        newContact.setCreationTime(LocalDateTime.now());
        newContact.setCreatedBy((User) userDao.getByPK(responsible));
        newContact.setCompanyId(new Company());

        contactDao.create(newContact);
        response.sendRedirect("dashboard");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = DaoFactory.getUserDAO();

        List<User> userList = userDao.getAll();
        request.setAttribute("userList", userList);

        response.setContentType("text/html");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addContact.jsp");
        requestDispatcher.forward(request, response);
    }

    private void addContact(){

    }

    private void addDeal(){

    }
}
