package com.becomejavasenior.web;

import com.becomejavasenior.User;
import com.becomejavasenior.abstraction.UserService;
import com.becomejavasenior.impl.UserServiceImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        name = "passwordRecovery",
        urlPatterns = {"/passwordRecovery"}
)
public class UserPasswordRecoveryController extends HttpServlet {
    private static final Logger log = Logger.getLogger(UserPasswordRecoveryController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, String> errors = new HashMap<>();
        Map<String, String> success = new HashMap<>();
        String datas;
        Gson gson = new Gson();

        String email = request.getParameter("email");
        try (PrintWriter out = response.getWriter()) {

            if (email == null
                    || email.trim().isEmpty()
                    || email.length() > 30) {
                errors.put("Error", "Wrong email");
            } else {
                UserService userService = new UserServiceImpl();
                User user = userService.getByEmail(email);
                if (user == null) {
                    errors.put("Error", "Wrong email");
                }
            }
            if (errors.isEmpty()) {
                try {
                    sendRecoveryEmail(email);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    log.warn("UserPasswordRecovery.java recovery email fail", e);
                    throw new IOException(e + "UserPasswordRecovery.java recovery email fail");
                }
                success.put("recovery", "true");
                datas = gson.toJson(success);
                out.println(datas);
            } else {
                datas = gson.toJson(errors);
                out.println(datas);
            }

        } catch (IOException e) {
            log.warn("UserPasswordRecovery.java response writer fail", e);
            throw new IOException(e + "UserPasswordRecovery.java response writer fail");
        }
    }

    private void sendRecoveryEmail(String email) throws MessagingException {
        // // TODO: 28/02/16  S.Gerasimov
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

