package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(
        name = "taskServlet",
        urlPatterns = {"/tasks"}
)
public class TaskController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "view";
        }

        switch(action)
        {
            case "create":
                this.createTask(request, response);
                break;
            case "view":
            default:
                this.listTasks(request, response);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String period =request.getParameter("periodName");
        String task = request.getParameter("task_name");
        String company = request.getParameter("companyName");
        String deal = request.getParameter("dealName");
        String type = request.getParameter("typeName");
        String comment = request.getParameter("commentName");
        String responsible = request.getParameter("responsibleName");
        String createdBy = request.getParameter("createdByName");

        System.out.println("period: " + period);
        System.out.println("task: " + task);
        System.out.println("company id: " + company);
        System.out.println("deal id: " + deal);
        System.out.println("type: " + type);
        System.out.println("comment: " + comment);
        System.out.println("responsible id: " + responsible);
        System.out.println("createdBy id: " + createdBy);

        String message = "Сделка успешно добавлена";
        request.setAttribute("message", message);

        request.getRequestDispatcher("/task/index.jsp")
                .forward(request, response);
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

        String[] timeArray = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00",
                "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        List<String> timeList = new ArrayList<>(Arrays.asList(timeArray));

        Map<String, String> periodMap = new LinkedHashMap<>();
        periodMap.put("today", "Сегодня");
        periodMap.put("allday", "Весь День");
        periodMap.put("tomorrow", "Завтра");
        periodMap.put("nextWeek", "Следущая неделя");
        periodMap.put("nextMonth", "Следущий месяц");
        periodMap.put("nextYear", "Следущий год");

        Map<String, String> nameMap = new LinkedHashMap<>();
        //nameMap.put("contact","Контакт");
        nameMap.put("company","Компания");
        nameMap.put("deal","Сделка");

        Map<String, String> typeMap = new LinkedHashMap<>();
        typeMap.put("followUp", "Follow-up");
        typeMap.put("meeting", "Встреча");
        typeMap.put("other", "Другой");

        //List<Contact> contactList = DaoFactory.getContactDao().getAll();
        List<Company> companyList = DaoFactory.getCompanyDAO().getAll();
        List<Deal> dealList = DaoFactory.getDealDao().getAll();
        List<User> userList = DaoFactory.getUserDAO().getAll();

        Map<Integer, String> userMap = new LinkedHashMap<>();
        for(User user: userList){
            userMap.put(user.getId(),user.getName());
        }

        Map<Integer,String> companyMap = new LinkedHashMap<>();
        for(Company company: companyList){
            companyMap.put(company.getId(),company.getCompanyName());
        }

        Map<Integer,Integer> dealMap = new LinkedHashMap<>();
        for(Deal deal: dealList){
            dealMap.put(deal.getId(), deal.getId());
        }

        request.setAttribute("timeList", timeList);
        request.setAttribute("periodMap", periodMap);
        request.setAttribute("nameMap", nameMap);
        request.setAttribute("typeMap", typeMap);
        request.setAttribute("companyMap", companyMap);
        request.setAttribute("dealList", dealList);
        request.setAttribute("dealMap", dealMap);
        request.setAttribute("userMap", userMap);
        //request.setAttribute("contactList", contactList);

        request.getRequestDispatcher("/task/addTask.jsp")
                .forward(request, response);
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/task/index.jsp")
                .forward(request, response);
    }
}
