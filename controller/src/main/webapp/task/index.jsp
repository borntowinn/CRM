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
                <div class="col-sm-3">
                    <a href="<c:url value="/tasks">
    <c:param name="action" value="create" />
    </c:url>" class="btn btn-info" role="button">+ Добавить задачу</a>
                    <br><br>


                    <div class="list-group">
                        <a href="#" class="list-group-item">Сегодня/Завтра</a>
                        <a href="#" class="list-group-item">День</a>
                        <a href="#" class="list-group-item">Неделя</a>
                        <a href="#" class="list-group-item">Месяц</a>
                        <a href="#" class="list-group-item">Список</a>
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

                <c:choose>
                    <c:when test="${message ne null}">
                        <div class="col-sm-9">
                            <div class="container">
                                <h2>${message}</h2>
                            </div>
                        </div>
                    </c:when>
                </c:choose>

            </div><%--row--%>
        </div><%--main--%>
    </div><%--row--%>
</div><%--container-fluid--%>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>



