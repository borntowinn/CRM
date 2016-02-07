package com.becomejavasenior.web;

import com.becomejavasenior.Task;
import com.becomejavasenior.service.TaskService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@WebServlet(
        name = "taskControllerAjax",
        urlPatterns = {"/tasks/ajax/*"}
)
public class TaskControllerAjax extends HttpServlet {

    private final String GET_WEEK_MONTH = "/getPeriod";
    private final String GET_DAY = "/getDay";
    private List<Task> taskList = null;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith(GET_DAY)) {
            taskList = this.getTasksForDay(request, response);
        } else if (requestURI.endsWith(GET_WEEK_MONTH)){
            taskList = this.getTasksForPeriod(request, response);
        }

        if(taskList != null && taskList.size() > 0){
            String json = new Gson().toJson(taskList);
            response.setContentType("application/json");
            response.getWriter().write(json);
        } else {
            response.setContentType("text/plain");
            response.getWriter().write("NO DATA");
        }
    }

    private List<Task> getTasksForPeriod(HttpServletRequest request, HttpServletResponse response) {
        Timestamp startTimestamp = new Timestamp(Long.valueOf(request.getParameter("start")) * 1000);
        Timestamp endTimestamp = new Timestamp(Long.valueOf(request.getParameter("end")) * 1000);
        LocalDateTime start = startTimestamp.toLocalDateTime();
        LocalDateTime end = endTimestamp.toLocalDateTime();

        return TaskService.getTasksForPeriod(start, end);
    }

    private List<Task> getTasksForDay(HttpServletRequest request, HttpServletResponse response) {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.MIN;
        LocalDateTime start = LocalDateTime.of(date, time);
        LocalDateTime end = start.plusDays(1L);

        return TaskService.getTasksForPeriod(start, end);
    }
}
