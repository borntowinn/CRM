package com.becomejavasenior.web;

import com.becomejavasenior.User;
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
import java.util.List;

/**
 * Created by ipvinner on 02.02.2016.
 */
public class AddContactServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        UserDao userDao = DaoFactory.getUserDAO();

        List<User> userList = userDao.getAll();
        request.setAttribute("userList", userList);

        response.setContentType("text/html");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addContact.jsp");
        requestDispatcher.forward(request, response);
    }
}
