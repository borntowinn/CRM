<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-primary" style="border-color: #428bca">
    <div class="text-right" style="margin: 5px">
        <button type="button" class="btn btn-default" style="color: #428bca">Добавить
            компанию
        </button>
    </div>
    <div class="text-center"
         style="margin-bottom: 10px; font-size: xx-large; color: #428bca">
        Список компаний
    </div>
    <div>
        <table id="companiesTable" class="display table table-striped table-bordered">
            <thead style="color: #428bca">
            <tr>
                <th>Компания (Название)</th>
                <th>Телефон</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="comp" items="${companies}">
                <tr>
                    <td><p><a href="javascript:void(0)">${comp.companyName}</a></p></td>
                    <td><p>${comp.phoneNumber}</p></td>
                    <td><p>${comp.email}</p></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dataTable.css"/>