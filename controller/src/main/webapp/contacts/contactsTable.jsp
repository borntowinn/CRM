<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-primary" style="border-color: #428bca">
    <div class="text-right" style="margin: 5px">
        <form action="${pageContext.request.contextPath}/add_contact">
            <button type="submit" class="btn btn-default" style="color: #428bca">Добавить
                контакт
            </button>
        </form>
    </div>
    <div class="text-center"
         style="margin-bottom: 10px; font-size: xx-large; color: #428bca">
        Список контактов
    </div>

    <div class="table-responsive">
        <table id="contactsTable" class="display table table-striped table-bordered">
            <thead style="color: #428bca">
            <tr>
                <th>Контакт (Имя Фамилия)</th>
                <th>Компания</th>
                <th>Телефон</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cont" items="${contacts}">
                <tr>
                    <td><p><a href="javascript:void(0)">${cont.nameSurname}</a></p></td>
                    <td><p><a href="javascript:void(0)">${cont.companyId.companyName}</a></p></td>
                    <td><p>${cont.phoneNumber}</p></td>
                    <td><p>${cont.email}</p></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dataTable.css"/>

