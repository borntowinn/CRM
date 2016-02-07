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
                <div class="col-sm-9">
                    <%--<div class="span3 taskList-scroll">--%>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th class="col-md-3 info">На сегодня</th>
                            <th class="col-md-3 success">На завтра</th>
<c:if test="${not empty overdueList}">
                            <th class="col-md-3 danger">Просроченые</th>
    </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <td>
                            <c:if test="${not empty todayList}">
                                <div class="panel-group">
                                    <c:forEach items="${todayList}" var="task">
                                        <fmt:parseDate value="${task.planTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                                        <fmt:formatDate value="${parsedDate}" var="stdDatum" type="date" pattern="dd.MM.yyyy" />
                                        <fmt:formatDate value="${parsedDate}" var="stdTime" type="time" pattern="HH:mm" />

                                        <div class="panel panel-info">
                                            <div class="panel-heading">${stdDatum}</div>
                                            <table class="table">
                                                <tbody>
                                                <tr>
                                                    <td><h5><strong>Время:</strong></h5></td>
                                                    <td><h5><p class="text-left">${stdTime}</p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td><h5><strong>Ответственный:</strong></h5></td>
                                                    <td><h5><p class="text-left">${task.responsible.name}</p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-1"><h5><strong>Тип:</strong></h5></td>
                                                    <td><h5><p class="text-left">${task.taskType}</p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td><h5><strong>Текст Задачи:</strong></h5></td>
                                                    <td><h5><p class="text-left"></p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td><h5><strong>Наименование:</strong></h5></td>
                                                    <td><h5><p class="text-left">${task.taskName}</p></h5></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div><%--panel--%>
                                    </c:forEach>
                                </div><%--panel-group--%>
                            </c:if>
                        </td><%--На Сегодня--%>

                        <td>
                            <c:if test="${not empty tomorrowList}">

                                <div class="panel-group">
                                    <c:forEach items="${tomorrowList}" var="task">
                                        <fmt:parseDate value="${task.planTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                                        <fmt:formatDate value="${parsedDate}" var="stdDatum" type="date" pattern="dd.MM.yyyy" />
                                        <fmt:formatDate value="${parsedDate}" var="stdTime" type="time" pattern="HH:mm" />

                                        <div class="panel panel-success">
                                            <div class="panel-heading">${stdDatum}</div>
                                            <table class="table">
                                                <tbody>
                                                <tr>
                                                    <td><h5><strong>Время:</strong></h5></td>
                                                    <td><h5><p class="text-left">${stdTime}</p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td><h5><strong>Ответственный:</strong></h5></td>
                                                    <td><h5><p class="text-left">${task.responsible.name}</p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-1"><h5><strong>Тип:</strong></h5></td>
                                                    <td><h5><p class="text-left">${task.taskType}</p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td><h5><strong>Текст Задачи:</strong></h5></td>
                                                    <td><h5><p class="text-left"></p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td><h5><strong>Наименование:</strong></h5></td>
                                                    <td><h5><p class="text-left">${task.taskName}</p></h5></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div><%--panel--%>
                                    </c:forEach>
                                </div><%--panel-group--%>
                            </c:if>
                        </td><%--На Завтра--%>


                            <c:if test="${not empty overdueList}">
                        <td>
                                <div class="panel-group">
                                <c:forEach items="${overdueList}" var="task">
                                        <fmt:parseDate value="${task.planTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                                        <fmt:formatDate value="${parsedDate}" var="stdDatum" type="date" pattern="dd.MM.yyyy" />
                                        <fmt:formatDate value="${parsedDate}" var="stdTime" type="time" pattern="HH:mm" />

                                        <div class="panel panel-danger">
                                            <div class="panel-heading">${stdDatum}</div>
                                            <table class="table">
                                                <tbody>
                                                <tr>
                                                    <td><h5><strong>Время:</strong></h5></td>
                                                    <td><h5><p class="text-left">${stdTime}</p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td><h5><strong>Ответственный:</strong></h5></td>
                                                    <td><h5><p class="text-left">${task.responsible.name}</p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-1"><h5><strong>Тип:</strong></h5></td>
                                                    <td><h5><p class="text-left">${task.taskType}</p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td><h5><strong>Текст Задачи:</strong></h5></td>
                                                    <td><h5><p class="text-left"></p></h5></td>
                                                </tr>
                                                <tr>
                                                    <td><h5><strong>Наименование:</strong></h5></td>
                                                    <td><h5><p class="text-left">${task.taskName}</p></h5></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div><%--panel--%>
                                </c:forEach>
                                </div><%--panel-group--%>
                        </td><%--Просроченые--%>
                            </c:if>

                        </tbody>
                    </table>
                    <%-- </div>--%>
                </div>
            </div><%--row--%>
        </div><%--main--%>
    </div><%--row--%>
</div><%--container-fluid--%>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>



