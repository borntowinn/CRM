package com.becomejavasenior.web;

import com.becomejavasenior.Company;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Katia on 08.02.2016.
 */

@WebServlet(
        name = "companyServlet",
        urlPatterns = {"/companies"}
)
public class CompanyServlet extends HttpServlet {
    private String viewCompany = "company.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = DaoFactory.getCompanyDAO().getAll();
        req.setAttribute("companies", companies);
        resp.setContentType("text/html");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewCompany);
        requestDispatcher.forward(req, resp);
    }

}
