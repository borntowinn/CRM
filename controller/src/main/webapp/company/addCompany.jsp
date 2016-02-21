<%--
  Created by IntelliJ IDEA.
  User: Default71721
  Date: 06.02.16
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>

<head>
    <style>
        div {
            line-height: 70%;
        }

        #addCompanyForm .has-error .control-label,
        #addCompanyForm .has-error .help-block,
        #addCompanyForm .has-error .form-control-feedback {
            color: #f39c12;
        }

        #addCompanyForm .has-success .control-label,
        #addCompanyForm .has-success .help-block,
        #addCompanyForm .has-success .form-control-feedback {
            color: #18bc9c;
        }
    </style>
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2 col-md-2 sidebar">
            <jsp:include page="../fragments/leftMenu.jsp"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <form id="addCompanyForm" class="form-horizontal" role="form" method="POST" action="/company"
                  accept-charset="UTF-8">
                <div class="col-sm-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Добавление компании</h3>
                        </div>
                        <br><br>

                        <div class="form-group" style="line-height: 70%">
                            <label class="control-label col-sm-5" for="companyName">Название компании:</label>
                            <div class="col-sm-5">
                                <input type="text" id="companyName" name="companyName" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="responsibleList">Ответственный:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="responsibleList" name="responsibleName">
                                    <c:forEach var="user" items="${userList}">
                                        <option value="${user.id}">${user.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="phoneNumber">Номер телефона:</label>
                            <input type="text" id="phoneNumber" name="phoneNumber">
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="email">Email:</label>
                            <input type="email" id="email" name="email">
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="webAddress">Web адрес:</label>
                            <input type="text" id="webAddress" name="webAddress">
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="address">Адрес:</label>
                            <input type="text" id="address" name="address">
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="note">Примечание:</label>
                            <div class="col-sm-7">
                                <textarea class="from-control" rows="4" id="note" name="note"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5">Добавить файлы</label>
                            <input id="input-files" name="inputFiles[]" type="file" class="file"
                                   multiple data-show-upload="false" data-show-caption="true">
                        </div>

                    </div>
                    <%--panel--%>
                </div>

                <div class="col-sm-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Добавление контакта</h3>
                        </div>
                        <br><br>


                        <div class="form-group">
                            <label class="control-label col-sm-5" for="nameSurname">Имя Фамилия:</label>
                            <div class="col-sm-5">
                                <input type="text" id="nameSurname" name="nameSurname">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="responsibleListContact">Ответственный:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="responsibleListContact" name="responsibleNameContact">
                                    <c:forEach var="user" items="${userList}">
                                        <option value="${user.id}">${user.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="position">Должность:</label>
                            <input type="text" id="position" name="position">
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="phoneTypeList">Тип телефона:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="phoneTypeList" name="phoneType">
                                    <c:forEach var="entry" items="${phoneTypeMap}">
                                        <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="contactPhoneNumber">Номер телефона:</label>
                            <input type="text" id="contactPhoneNumber" name="contactPhoneNumber">
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="contactEmail">Email:</label>
                            <input type="email" id="contactEmail" name="contactEmail">
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="skype">Skype:</label>
                            <input type="text" id="skype" name="skype">
                        </div>
                        <br><br>
                        <br><br>
                        <br><br>
                    </div>
                    <%--col-sm-9--%>
                </div>
                <%--col-sm-9--%>
                <div class="col-sm-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Быстрое добавление сделки</h3>
                        </div>
                        <br><br>


                        <div class="form-group">
                            <label class="control-label col-sm-5" for="dealName">Название сделки:</label>
                            <div class="col-sm-5">
                                <input type="text" id="dealName" name="dealName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="phaseList">Этап:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="phaseList" name="phase">
                                    <c:forEach var="phase" items="${phaseList}">
                                        <option value="${phase.id}">${phase.phase}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="position">Бюджет:</label>
                            <input type="text" id="budget" name="budget">
                        </div>

                    </div>
                    <%--col-sm-9--%>

                </div>
                <div class="col-sm-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Быстрое добавление сделки</h3>
                        </div>
                        <br><br>


                        <div class="form-group">
                            <label class="control-label col-sm-5" for="dealName">Название сделки:</label>
                            <div class="col-sm-5">
                                <input type="text" id="dealName" name="dealName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="phaseList">Этап:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="phaseList" name="phase">
                                    <c:forEach var="phase" items="${phaseList}">
                                        <option value="${phase.id}">${phase.phase}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="position">Бюджет:</label>
                            <input type="text" id="budget" name="budget">
                        </div>

                    </div>
                    <%--col-sm-9--%>

                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Добавить</button>
                    </div>
                </div>
            </form>
            <%--row--%>
        </div>
        <%--main--%>
    </div>
    <%--row--%>
</div>
<%--container-fluid--%>
<jsp:include page="../fragments/footer.jsp"/>
<script src="http://formvalidation.io/vendor/formvalidation/js/formValidation.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/framework/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $('#addCompanyForm').formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                companyName: {
                    validators: {
                        notEmpty: {
                            message: 'The name of the company is required'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: 'Email is required'
                        }
                    }
                },
                phoneNumber: {
                    validators: {
                        notEmpty: {
                            message: 'Phone number is required'
                        }
                    }
                },
                address: {
                    validators: {
                        notEmpty: {
                            message: 'Address is required'
                        }
                    }
                },
                webAddress: {
                    validators: {
                        notEmpty: {
                            message: 'Web address is required'
                        }
                    }
                }
            }
        });
    });
</script>
</body>
</html>
<%--<script type="text/javascript" src="<c:url value="${pageContext.request.contextPath}/resources/js/task.js"/>"></script>--%>


