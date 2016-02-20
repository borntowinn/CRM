<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                    <input type="date" id="dayCalendar" onchange="getDay();">
                    <div id="responseMessage"></div><br>

                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th class="col-md-1 info">Время:</th>
                            <th class="col-md-8 info text-center" id="currentDate"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.timeList}" var="hour">
                            <tr id="tr_${hour}">
                                <td><h5><strong>${hour}</strong></h5></td>
                                <td id="td_${hour}">
                                    <%--ajax inserts content here --%>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div><%--col-sm-9--%>
            </div><%--row--%>
        </div><%--main--%>
    </div><%--row--%>
</div><%--container-fluid--%>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
<script type="text/javascript" src="<c:url value="${pageContext.request.contextPath}/resources/js/task.js"/>"></script>





