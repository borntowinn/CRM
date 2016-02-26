<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Password Recovery</title>
    <!-- CORE CSS-->

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/webjars/materializecss/0.97.5/css/materialize.min.css">
    <link rel="stylesheet"
          href="resources/css/loginBody.css">

    <!-- ================================================
  Scripts
  ================================================ -->

    <script type="text/javascript"
            src="<c:url value="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.min.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/js/bootstrap.min.js"/>"></script>
    <script src="resources/js/userValidation.js"></script>
</head>

<body>


<div id="recovery-page" class="row" style="min-width: 350px">
    <div class="col s12 z-depth-6 card-panel">
        <form id="recovery-form" method="post" class="login-form">
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
                    <i class="mdi-social-person-outline prefix"></i>
                    <input class="validate" id="email" type="email" placeholder="Email">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input type="submit" value="Recover my Password" class="btn waves-effect waves-light col s12"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6 m6 l6">
                    <p class="margin medium-small"><a href="<c:url value="/register.jsp" />">Register Now!</a></p>
                </div>
                <div class="input-field col s6 m6 l6">
                    <p class="margin right-align medium-small"><a href="<c:url value="/login.jsp" />">Login</a></p>
                </div>
            </div>

        </form>
    </div>
</div>


</body>

</html>