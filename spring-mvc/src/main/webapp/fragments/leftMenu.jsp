<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<fmt:message key="leftmenu.home" var="home"/>
<fmt:message key="leftmenu.dashboard" var="dashboard"/>
<fmt:message key="leftmenu.addContact" var="addContact"/>
<fmt:message key="leftmenu.deals" var="deals"/>
<fmt:message key="leftmenu.tagsToContacts" var="tagsToContacts"/>
<fmt:message key="leftmenu.addCompany" var="addCompany"/>
<fmt:message key="leftmenu.tasks" var="tasks"/>
<fmt:message key="leftmenu.analytics" var="analytics"/>
<fmt:message key="leftmenu.settings" var="settings"/>


<html lang="${language}">
<ul class="nav nav-pills nav-stacked">
    <li><a href="#">${home}</a></li>
    <li><a href="dashboard">${dashboard}</a></li>
    <li><a href="add_contact">${addContact}</a></li>
    <li><a href="#">${deals}</a></li>
    <li><a href="<c:url value="/tagsToContacts" />">${tagsToContacts}</a></li>
    <li><a href="<c:url value="/company" />">${addCompany}</a></li>
    <li><a href="<c:url value="/tasks/listAll" />">${tasks}</a></li>
    <li><a href="#">${analytics}</a></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">${settings}
            <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="#">Submenu 1-1</a></li>
            <li><a href="#">Submenu 1-2</a></li>
            <li><a href="#">Submenu 1-3</a></li>
        </ul>
    </li>
</ul>

</html>

