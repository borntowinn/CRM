<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="../task/headTask.jsp"/>

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
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Добавление задачи</h3>
                        </div>
                        <br><br>

                        <form action="<c:url value="/tasks/add"/>" method="post" class="form-horizontal" role="form" accept-charset="UTF-8">

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="periodList">Период:</label>

                                <div class="col-sm-3">
                                    <select class="form-control" id="periodList" name="periodName">
                                        <option value="default" >Выбрать</option>
                                        <c:forEach var="entry" items="${periodMap}">
                                            <option value="${entry.key}" >${entry.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="calendar">Календарь:</label>
                                <div class="col-sm-10">
                                    <input type="date" id="calendar" name="dateName">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="nameList">Название:</label>

                                <div class="col-sm-3">
                                    <select class="form-control" id="nameList" name="task_name">
                                        <c:forEach var="entry" items="${nameMap}">
                                            <option value="${entry.key}" >${entry.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-sm-3" id="contactDiv">
                                    <select class="form-control" id="contactList" name="contactName">
                                        <c:forEach var="entry" items="${contactMap}">
                                            <option value="${entry.key}" >${entry.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-sm-3" id="companyDiv" style="display: none;">
                                    <select class="form-control" id="companylist" name="companyName">
                                        <c:forEach var="entry" items="${companyMap}">
                                            <option value="${entry.key}" >${entry.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-sm-3" id="dealDiv" style="display: none;">
                                    <select class="form-control" id="dealList" name="dealName">
                                        <c:forEach var="entry" items="${dealMap}">
                                            <option value="${entry.key}" >${entry.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="type">Время:</label>

                                <div class="col-sm-3">
                                    <select class="form-control" id="timeList" name="timeName">
                                        <c:forEach items="${requestScope.timeList}" var="hour" varStatus="loop">
                                            <option value="${hour}" >${hour}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="type">Тип:</label>

                                <div class="col-sm-3">
                                    <select class="form-control" id="type" name="typeName">
                                        <c:forEach var="entry" items="${typeMap}">
                                            <option value="${entry.key}" >${entry.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="comment">Коментарий:</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" name="commentName" rows="5" id="comment"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="responsibleList">Ответственный:</label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="responsibleList" name="responsibleName">
                                        <c:forEach var="entry" items="${userMap}">
                                            <option value="${entry.key}" >${entry.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2" for="createdBy">Автор:</label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="createdBy" name="createdByName">
                                        <c:forEach var="entry" items="${userMap}">
                                            <option value="${entry.key}" >${entry.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default">Добавить</button>
                                </div>
                            </div>
                        </form>

                    </div><%--panel--%>
                </div><%--col-sm-9--%>

            </div><%--row--%>
        </div><%--main--%>
    </div><%--row--%>
</div><%--container-fluid--%>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
<script type="text/javascript" src="<c:url value="${pageContext.request.contextPath}/resources/js/task.js"/>"></script>

