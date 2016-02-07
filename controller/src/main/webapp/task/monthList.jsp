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

                    <div id='calendar'></div>

                </div><%--col-sm-9--%>
            </div><%--row--%>
        </div><%--main--%>
    </div><%--row--%>
</div><%--container-fluid--%>

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title text-center" id="modalHeader"></h4>
            </div>
            <div class="modal-body">
                <form class="form-inline">
                    <label for="modalText">Текст Задачи:</label>
                    <div class="form-group">
                        <div id="modalText"></div><hr>
                    </div><hr>

                    <label for="modalTargetName" id="modalTarget"></label>
                    <div class="form-group">
                        <div id="modalTargetName"></div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
</div>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>

<script type="text/javascript" src="<c:url value="${pageContext.request.contextPath}/resources/js/moment.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="${pageContext.request.contextPath}/resources/js/fullcalendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="${pageContext.request.contextPath}/resources/js/ru.js"/>"></script>
<script type="text/javascript" src="<c:url value="${pageContext.request.contextPath}/resources/js/taskListWeekMonth.js"/>"></script>





