package com.becomejavasenior.service;

import com.becomejavasenior.*;
import com.becomejavasenior.dao.jdbc.factory.DaoFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskService {

    private static final String[] TIME_ARRAY = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
            "16:00", "17:00", "18:00", "19:00"};

    private static final String MESSAGE = "Задача успешно добавлена";
    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate TOMORROW = TODAY.plusDays(1);
    private static final LocalDateTime NOW = LocalDateTime.now();


    public static List<String> getTimeList(){
        return new ArrayList<>(Arrays.asList(TIME_ARRAY));
    }

    public static String getSuccessMessage(){
        return MESSAGE;
    }

    public static Map<String,String> getPeriodMap(){
        Map<String, String> periodMap = new LinkedHashMap<>();
        periodMap.put("today", "Сегодня");
        periodMap.put("allday", "Весь День");
        periodMap.put("tomorrow", "Завтра");
        periodMap.put("nextWeek", "Следущая неделя");
        periodMap.put("nextMonth", "Следущий месяц");
        periodMap.put("nextYear", "Следущий год");

        return periodMap;
    }

    public static Map<String,String> getNameMap(){
        Map<String, String> nameMap = new LinkedHashMap<>();
        nameMap.put("contact","Контакт");
        nameMap.put("company","Компания");
        nameMap.put("deal","Сделка");

        return nameMap;
    }

    public static Map<String,String> getTypeMap(){
        Map<String, String> typeMap = new LinkedHashMap<>();
        typeMap.put("followUp", "Follow-up");
        typeMap.put("meeting", "Встреча");
        typeMap.put("other", "Другой");

        return typeMap;
    }

    public static Map<Integer,String> getUserMap(){
        List<User> userList = DaoFactory.getUserDAO().getAll();
        Map<Integer, String> userMap = new LinkedHashMap<>();
        for(User user: userList){
            userMap.put(user.getId(),user.getName());
        }

        return userMap;
    }

    public static Map<Integer,String> getCompanyMap(){
        List<Company> companyList = DaoFactory.getCompanyDAO().getAll();
        Map<Integer,String> companyMap = new LinkedHashMap<>();
        for(Company company: companyList){
            companyMap.put(company.getId(),company.getCompanyName());
        }

        return companyMap;
    }

    public static Map<Integer,String> getDealMap(){
        List<Deal> dealList = DaoFactory.getDealDao().getAll();
        Map<Integer,String> dealMap = new LinkedHashMap<>();
        for(Deal deal: dealList){
            dealMap.put(deal.getId(), deal.getDealName());
        }

        return dealMap;
    }

    public static Map<Integer, String> getContactMap(){
        List<Contact> contactList = DaoFactory.getContactDAO().getAll();
        Map<Integer,String> contactMap = new LinkedHashMap<>();
        for(Contact contact: contactList){
            contactMap.put(contact.getId(),contact.getNameSurname());
        }

        return contactMap;
    }

    public static boolean saveTask(Task task, String taskText){
        Task persistedTask = (Task)DaoFactory.getTaskDao().persist(task);

        return TaskService.saveComment(persistedTask, taskText) != null;
    }

    public static Comment saveComment(Task task, String taskText){
        Comment comment = new Comment();
        comment.setComment(taskText);
        comment.setTaskId(task);
        comment.setCreationDate(NOW);

        return (Comment)DaoFactory.getCommentDao().persist(comment);
    }

    public static List<Task> getTasksForPeriod(LocalDateTime start, LocalDateTime end) {
        List<Task> taskList = DaoFactory.getTaskDao().getTasksForPeriod(start, end);

        return taskList;
    }

    public static List<Task> getTodayList() {
        List<Task> taskList = TaskService.getAllTasks();
        List<Task> todayList = new ArrayList<>();
        for(Task task : taskList){
            if(TODAY.equals(task.getPlanTime().toLocalDate())){
                todayList.add(task);
            }
        }

        return todayList;
    }

    public static List<Task> getTomorrowList() {
        List<Task> taskList = TaskService.getAllTasks();
        List<Task> tomorrowList = new ArrayList<>();
        for(Task task : taskList){
            if(TOMORROW.equals(task.getPlanTime().toLocalDate())){
                tomorrowList.add(task);
            }
        }

        return tomorrowList;
    }

    public static List<Task> getOverdueList() {
        List<Task> taskList = TaskService.getAllTasks();
        List<Task> overdueList = new ArrayList<>();
        for(Task task : taskList){
            if(TODAY.isAfter(task.getPlanTime().toLocalDate()) && !task.getDone()){
                overdueList.add(task);
            }
        }

        return overdueList;
    }

    public static List<Task> getAllTasks() {
        return DaoFactory.getTaskDao().getAll();
    }
}
