<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html lang="${language}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <a class="navbar-brand" href="#Profile" style="color: #ffffff">CRM-ATLAS</a>
            <a class="navbar-brand" href="#Profile" style="color: #ffffff">
                <c:out value="${sessionScope.user.name}"/>
            </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <%--<fmt:message key="header.profile" var="headerProfile" />--%>
                    <a href="#Profile"><fmt:message key="header.profile"/></a>
                </li>
                <li id="log-out">
                    <%--<fmt:message key="logout" var="logout" />--%>
                    <a href="<c:url value="/pages/login.jsp" />"><fmt:message key="header.logout"/> </a>
                </li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="<fmt:message key="header.search"/>">
            </form>
            <form class="navbar-form navbar-right" style="margin-top: 15px">
                <select id="language" name="language" onchange="submit()">
                    <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
                    <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Russian</option>
                </select>
            </form>
        </div>
    </div>
</nav>
</body>


</html>


