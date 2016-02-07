package com.becomejavasenior.web;

import com.becomejavasenior.*;
import com.becomejavasenior.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "taskServlet",
        urlPatterns = {"/tasks/*"}
)
public class TaskController extends HttpServlet {

    private final String CREATE = "/create";
    private final String ADD = "/add";
    private final String LIST_ALL = "/listAll";
    private final String LIST_CURRENT = "/listCurrent";
    private final String LIST_DAY = "/listDay";
    private final String LIST_WEEK = "/listWeek";
    private final String LIST_MONTH = "/listMonth";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "";

        if (requestURI.endsWith(LIST_ALL)) {
            url = this.listAll(request, response);
        } else if (requestURI.endsWith(CREATE)) {
            url = this.createTask(request, response);
        } else if (requestURI.endsWith(LIST_CURRENT)) {
            url = this.listCurrent(request, response);
        } else if (requestURI.endsWith(LIST_DAY)) {
            url = this.listDay(request, response);
        } else if (requestURI.endsWith(LIST_WEEK)) {
            url = this.listWeek(request, response);
        } else if (requestURI.endsWith(LIST_MONTH)) {
            url = this.listMonth(request, response);
        }

        request.getRequestDispatcher(url)
                .forward(request, response);
    }

    private String listMonth(HttpServletRequest request, HttpServletResponse response) {
        return "/task/monthList.jsp";
    }

    private String listWeek(HttpServletRequest request, HttpServletResponse response) {
        return "/task/weekList.jsp";
    }

    private String listDay(HttpServletRequest request, HttpServletResponse response) {
        List<String> timeList = TaskService.getTimeList();
        request.setAttribute("timeList", timeList);

        return "/task/dayList.jsp";
    }

    private String listCurrent(HttpServletRequest request, HttpServletResponse response) {
        List<Task> todayList = TaskService.getTodayList();
        List<Task> tomorrowList = TaskService.getTomorrowList();
        List<Task> overdueList = TaskService.getOverdueList();

        if(todayList != null && todayList.size() > 0){
            request.setAttribute("todayList", todayList);
        } else if(tomorrowList != null && tomorrowList.size() > 0){
            request.setAttribute("tomorrowList", tomorrowList);
        } else if(overdueList != null && overdueList.size() > 0){
            request.setAttribute("overdueList", overdueList);
        }

        return "/task/currentList.jsp";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "";

        if (requestURI.endsWith(ADD)) {
            url = this.addTask(request, response);
        }

        request.getRequestDispatcher(url)
                .forward(request, response);
    }

    private String addTask(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        Task task = new Task();
        task.setPeriod(request.getParameter("periodName"));
        task.setTaskName(request.getParameter("task_name"));
        task.setTaskType(request.getParameter("typeName"));
        task.setDeleted(false);
        task.setDone(false);
        task.setCreationTime(LocalDateTime.now());

        User responsible = new User();
        responsible.setId(Integer.valueOf(request.getParameter("responsibleName")));
        task.setResponsible(responsible);

        User createdBy = new User();
        createdBy.setId(Integer.valueOf(request.getParameter("createdByName")));
        task.setAuthor(createdBy);

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

        LocalDate date = (!request.getParameter("dateName").isEmpty()) ? LocalDate.parse(request.getParameter("dateName")) : LocalDate.now();
        LocalTime time = (request.getParameter("timeName") != null) ? LocalTime.parse(request.getParameter("timeName")) : LocalTime.MIDNIGHT;
        LocalDateTime planTime = LocalDateTime.of(date, time);
        task.setPlanTime(planTime);

        if(TaskService.saveTask(task)){
            String message = TaskService.getSuccessMessage();
            request.setAttribute("message", message);
        }

        return "/task/allList.jsp";
    }

    private String createTask(HttpServletRequest request, HttpServletResponse response){
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

        return "/task/addTask.jsp";
    }

    private String listAll(HttpServletRequest request, HttpServletResponse response) {
        List<Task> taskList = TaskService.getAllTasks();
        request.setAttribute("taskList", taskList);

        return "/task/allList.jsp";
    }
}
