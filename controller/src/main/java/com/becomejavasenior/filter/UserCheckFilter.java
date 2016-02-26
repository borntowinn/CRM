package com.becomejavasenior.filter;

import com.becomejavasenior.User;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserCheckFilter implements Filter {
    private static final String FILTER_PATTERN =
            ".*(css|jpg|png|gif|js|ttf|woff|woff2|/userLogin|/userRegistrator|/passwordRecovery|/register.jsp|/forgot-password.jsp)";

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user;
        if (session != null) {
            user = (User) req.getSession().getAttribute("user");
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                String currentURI = req.getRequestURI();
                if (currentURI.matches(FILTER_PATTERN)) {
                    chain.doFilter(request, response);
                    return;
                }
                req.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }

}