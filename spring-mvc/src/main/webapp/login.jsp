<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Login CRM-ATLAS</title>
    <!-- CORE CSS-->

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/webjars/materializecss/0.97.5/css/materialize.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/loginBody.css">

    <!-- ================================================
  Scripts
  ================================================ -->
    <script type="text/javascript"
            src="<c:url value="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.min.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="${pageContext.request.contextPath}/webjars/materializecss/0.97.5/js/materialize.min.js"/>"></script>
    <script src="resources/js/userValidation.js"></script>

</head>

<body>


<div id="login-page" class="row">
    <div class="col s12 z-depth-6 card-panel">
        <form id="login-form" method="post" class="login-form" style="min-width: 350px; max-width: 350px">
            <div class="row">
                <div class="input-field col s12 center">
                    <img src="/resources/img/logo.png" alt="" class="responsive-img valign profile-image-login">
                    <p class="center login-form-text">CRM-ATLAS SYSTEM</p>
                </div>
            </div>
            <div class="row margin">
                <div class="col s12">
                    <span class="error flow-text col s11 offset-s1 left"></span>
                </div>
                <div class="input-field col s12">
                    <i class="mdi-communication-email prefix"></i>
                    <input class="validate" id="email" type="email" placeholder="Email">
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <i class="mdi-action-lock-outline prefix"></i>
                    <input id="password" type="password" placeholder="Password">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m12 l12  login-text">
                    <input type="checkbox" id="remember-me"/>
                    <label for="remember-me">Remember me</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" value="Login" class="btn waves-effect waves-light col s12">Login</button>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6 m6 l6">
                    <p class="margin medium-small"><a href="<c:url value="/register.jsp" />">Register Now!</a></p>
                </div>
                <div class="input-field col s6 m6 l6">
                    <p class="margin right-align medium-small"><a href="<c:url value="/forgot-password.jsp" />">Forgot
                        password?</a></p>
                </div>
            </div>

        </form>
    </div>
</div>

</body>

</html>