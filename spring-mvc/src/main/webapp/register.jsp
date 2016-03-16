<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Signup CRM-ATLAS</title>
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
            src="<c:url value="${pageContext.request.contextPath}/webjars/materializecss/0.97.5/js/materialize.min.js"/>"></script>
    <script src="resources/js/userValidation.js"></script>
</head>

<body>


<div id="registration-page" class="row">
    <div class="col s12 z-depth-6 card-panel">
        <form id="registration-form" method="post" class="login-form" style="min-width: 350px; max-width: 350px">
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
                    <input id="username" type="text" class="validate" placeholder="Username">
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <i class="mdi-communication-email prefix"></i>
                    <input id="email" type="email" class="validate" placeholder="Email">
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <i class="mdi-action-lock-outline prefix"></i>
                    <input id="password" type="password" class="validate" placeholder="Password">
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <i class="mdi-action-lock-outline prefix"></i>
                    <input id="password-again" type="password" placeholder="Re-type password">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" value="Register Now" class="btn waves-effect waves-light col s12">Register
                        Now
                    </button>
                </div>
                <div class="input-field col s12">
                    <p class="margin center medium-small sign-up">Already have an account? <a
                            href="<c:url value="/login.jsp" />">Login</a></p>
                </div>
            </div>
        </form>
    </div>
</div>


</body>

</html>