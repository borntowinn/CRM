<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
    <script type="text/javascript"
            src="<c:url value="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.min.js"/>"></script>
    <script src="resources/js/userValidation.js"></script>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="register.jsp" style="color: #ffffff">CRM-ATLAS</a>
            <a class="navbar-brand" href="#Profile" style="color: #ffffff">
                <c:out value="${sessionScope.user.name}"/>
            </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#Profile">Profile</a></li>
                <li id="log-out"><a href="<c:url value="/login.jsp" />">Logout</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>
</body>


