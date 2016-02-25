<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav nav-pills nav-stacked">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="/crm/dashboard">Рабочий стол</a></li>
    <li><a href="/crm/add_contact">Добавить контакт</a></li>
    <li><a href="#">Сделки</a></li>
    <li><a href="/contacts">Контакты</a></li>
    <li><a href="/company">Добавить компанию</a></li>
    <li><a href="<c:url value="/tasks/listAll" />">Задачи</a></li>
    <li><a href="#">Аналитика</a></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Настройки
            <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="#">Submenu 1-1</a></li>
            <li><a href="#">Submenu 1-2</a></li>
            <li><a href="#">Submenu 1-3</a></li>
        </ul>
    </li>
</ul>
