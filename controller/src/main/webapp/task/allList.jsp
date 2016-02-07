<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>

<body>
<jsp:include page="../fragments/header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <jsp:include page="../fragments/leftMenu.jsp"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="row">
                <jsp:include page="../task/sideBar.jsp"/>

                <c:choose>
                    <c:when test="${empty message}">
                        <div class="col-sm-9">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Дата исполнения/Время/Ответственный</th>
                                    <th>Контакт/Компания/Сделка</th>
                                    <th>Тип/Текст задачи</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${taskList}" var="task">
                                    <tr>
                                        <td><a href="#" >${task.planTime}/${task.responsible.name}</a></td>
                                        <td>${task.taskName}</td>
                                        <td>${task.taskType}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-sm-9">
                            <div class="container">
                                <h2>${message}</h2>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div><%--row--%>
        </div><%--main--%>
    </div><%--row--%>
</div><%--container-fluid--%>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>



