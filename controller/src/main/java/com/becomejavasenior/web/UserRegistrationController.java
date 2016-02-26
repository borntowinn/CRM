package com.becomejavasenior.web;

import com.becomejavasenior.User;
import com.becomejavasenior.UserRole;
import com.becomejavasenior.abstraction.UserService;
import com.becomejavasenior.impl.UserServiceImpl;
import com.becomejavasenior.web.validation.EmailValidation;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        name = "userRegistrator",
        urlPatterns = {"/userRegistrator"}
)
public class UserRegistrationController extends HttpServlet {
    private static final Logger log = Logger.getLogger(UserRegistrationController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        HttpSession httpSession = request.getSession(false);
        Map<String, String> errors = new HashMap<>();
        Map<String, String> success = new HashMap<>();
        String datas;
        Gson gson = new Gson();

        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        try (PrintWriter out = response.getWriter()) {
            UserService userService = new UserServiceImpl();
            User checkUser = userService.getByEmail(email);
            if (userName == null || userName.trim().isEmpty()) {
                errors.put("Error", "You must specify the Username");
            } else if (email == null || email.trim().isEmpty()) {
                errors.put("Error", "You must specify an email");
            } else if (email.length() > 30) {
                errors.put("Error", "Email must be up to 30 chars");
            } else if (!new EmailValidation().validate(email)) {
                errors.put("Error", "You must specify a real email please");
            } else if (checkUser != null) {
                errors.put("Error", "This email is in use");
            } else if (password == null || password.trim().isEmpty()) {
                errors.put("Error", "You must specify a password");
            } else if (password.length() < 8 || password.length() > 16) {
                errors.put("Error", "Password must be between 8 and 16 chars");
            } else if (!password.equals(password2)) {
                errors.put("Error", "Passwords must be the same");
            }
            if (errors.isEmpty()) {

                // password hashing
                PasswordHashing ph = new PasswordHashing();
                String hashedPassword = ph.hashPassword(password);
                String salt = ph.getSALT();

                // set user attributes and add to the db
                User newUser = new User();
                newUser.setName(userName);
                newUser.setPassword(hashedPassword);
                newUser.setPasswordSalt(salt);
                newUser.setDescription("not filled yet");
                newUser.setCreationDate(LocalDateTime.now());
                newUser.setEmail(email);
                newUser.setMobilePhone("not filled yet");
                newUser.setWorkPhone("not filled yet");
                newUser.setLanguage(1);
                UserRole userRole = new UserRole();
                userRole.setId(2);
                newUser.setUserRole(userRole);
                userService.create(newUser);
                // add user object to the session
                httpSession.setAttribute("user", newUser);

                success.put("success", "true");
                datas = gson.toJson(success);
                out.println(datas);
            } else {
                datas = gson.toJson(errors);
                out.println(datas);
            }

        } catch (IOException e) {
            log.warn("UserRegistration.java response writer fail", e);
            throw new IOException(e + "UserRegistration.java response writer fail");
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

