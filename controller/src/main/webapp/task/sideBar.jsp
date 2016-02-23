<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-3">
    <a href="<c:url value="/tasks/create" />" class="btn btn-info" role="button">Добавить задачу</a>
    <br><br>
    <div class="list-group">
        <a href="<c:url value="/tasks/listCurrent" />" class="list-group-item">Сегодня/Завтра</a>
        <a href="<c:url value="/tasks/listDay" />" class="list-group-item">День</a>
        <a href="<c:url value="/tasks/listWeek" />" class="list-group-item">Неделя</a>
        <a href="<c:url value="/tasks/listMonth" />" class="list-group-item">Месяц</a>
        <a href="<c:url value="/tasks/listAll" />" class="list-group-item">Список</a>
    </div>
    <div class="list-group">
        <a href="#" class="list-group-item active">Фильтры:</a>
        <a href="#" class="list-group-item">Только мои задачи</a>
        <a href="#" class="list-group-item">Просроченные задачи</a>
        <a href="#" class="list-group-item">Выполненные задачи</a>
        <a href="#" class="list-group-item">Все задачи</a>
        <a href="#" class="list-group-item">Удаленные</a>
    </div>
</div><%--col-sm-3--%>
