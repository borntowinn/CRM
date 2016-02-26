package com.becomejavasenior.web;

import com.becomejavasenior.User;
import com.becomejavasenior.abstraction.UserService;
import com.becomejavasenior.impl.UserServiceImpl;
import com.becomejavasenior.web.validation.PasswordHashing;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        name = "userLogin",
        urlPatterns = {"/userLogin"}
)
public class UserLoginController extends HttpServlet {
    private static final Logger log = Logger.getLogger(UserLoginController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        HttpSession httpSession = request.getSession(false);
        Map<String, String> errors = new HashMap<>();
        Map<String, String> success = new HashMap<>();
        String datas;
        Gson gson = new Gson();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try (PrintWriter out = response.getWriter()) {
            UserService userService = new UserServiceImpl();
            User user = userService.getByEmail(email);

            if (email == null
                    || email.trim().isEmpty()
                    || email.length() > 30
                    || password == null
                    || password.trim().isEmpty()
                    || password.length() < 8
                    || password.length() > 16) {
                errors.put("Error", "Wrong email or password");
            } else if (user == null) {
                errors.put("Error", "Wrong email or password");
            } else {
                PasswordHashing ph = new PasswordHashing();
                if (!ph.login(user, password)) {
                    errors.put("Error", "Wrong email or password");
                }
            }
            if (errors.isEmpty()) {
                if (httpSession.getAttribute("user") != null) {
                    success.put("success", "true");
                    datas = gson.toJson(success);
                    out.println(datas);
                } else {
                    httpSession.setAttribute("user", user);
                    success.put("success", "true");
                    datas = gson.toJson(success);
                    out.println(datas);
                }
            } else {
                datas = gson.toJson(errors);
                out.println(datas);
            }

        } catch (IOException e) {
            log.warn("UserLogin.java response writer fail", e);
            throw new IOException(e + "UserLogin.java response writer fail");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

