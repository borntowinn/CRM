package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "taskServlet",
        urlPatterns = {"/tasks"}
)
public class TaskController extends HttpServlet {

    private final String CREATE = "create";
    private final String VIEW = "view";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = VIEW;
        }

        switch(action)
        {
            case CREATE:
                this.createTask(request, response);
                break;
            case VIEW:
            default:
                this.listTasks(request, response);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Task task = new Task();
        task.setPeriod(request.getParameter("periodName"));
        task.setTaskName(request.getParameter("task_name"));
        task.setTaskType(request.getParameter("typeName"));
        task.setDeleted(false);
        task.setDone(false);

        if(task.getTaskName().equals("company")){
            Company company = new Company();
            company.setId(Integer.valueOf(request.getParameter("companyName")));
            task.setCompany(company);
        } else if(task.getTaskName().equals("deal")){
            Deal deal = new Deal();
            deal.setId(Integer.valueOf(request.getParameter("dealName")));
            task.setDeal(deal);
        } else if(task.getTaskName().equals("contact")){
            Contact contact = new Contact();
            contact.setId(Integer.valueOf(request.getParameter("contactName")));
            task.setContact(contact);
        }

        User responsible = new User();
        responsible.setId(Integer.valueOf(request.getParameter("responsibleName")));
        task.setResponsible(responsible);

        User createdBy = new User();
        createdBy.setId(Integer.valueOf(request.getParameter("createdByName")));
        task.setResponsible(createdBy);

        String message = TaskService.getSuccessMessage();
        request.setAttribute("message", message);

        //TaskService.saveTask(task);

        request.getRequestDispatcher("/task/index.jsp")
                .forward(request, response);
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        List<String> timeList = TaskService.getTimeList();
        Map<String, String> periodMap = TaskService.getPeriodMap();
        Map<String, String> nameMap = TaskService.getNameMap();
        Map<String, String> typeMap = TaskService.getTypeMap();
        Map<Integer, String> userMap = TaskService.getUserMap();
        Map<Integer, String> companyMap = TaskService.getCompanyMap();
        Map<Integer, String> contactMap = TaskService.getContactMap();
        Map<Integer, String> dealMap = TaskService.getDealMap();

        request.setAttribute("timeList", timeList);
        request.setAttribute("periodMap", periodMap);
        request.setAttribute("nameMap", nameMap);
        request.setAttribute("typeMap", typeMap);
        request.setAttribute("companyMap", companyMap);
        request.setAttribute("dealMap", dealMap);
        request.setAttribute("contactMap", contactMap);
        request.setAttribute("userMap", userMap);

        request.getRequestDispatcher("/task/addTask.jsp")
                .forward(request, response);
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/task/index.jsp")
                .forward(request, response);
    }
}
