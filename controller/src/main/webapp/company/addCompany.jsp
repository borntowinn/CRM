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

<body>
<jsp:include page="../fragments/header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <jsp:include page="../fragments/leftMenu.jsp"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="col-sm-6">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">Добавление компании</h3>
                    </div>
                    <br><br>

                    <form class="form-horizontal" role="form" method="POST" action="/company?action=createCompany"
                          accept-charset="UTF-8">

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="companyName">Название компании:</label>
                            <div class="col-sm-5">
                                <input type="text" id="companyName" name="companyName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="responsibleList">Ответственный:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="responsibleList" name="responsibleName">
                                    <c:forEach var="entry" items="${userMap}">
                                        <option value="${entry.key}">${entry.value}</option>
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
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Добавить</button>
                            </div>
                        </div>
                    </form>

                </div>

                <%--panel--%>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">Добавление контакта</h3>
                    </div>
                    <br><br>

                    <form class="form-horizontal" role="form" method="POST" action="/company?action=createContact"
                          accept-charset="UTF-8">

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
                                    <c:forEach var="entry" items="${userMap}">
                                        <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="companyList">Компания:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="companyList" name="companyId">
                                    <c:forEach var="entry" items="${companyMap}">
                                        <option value="${entry.key}">${entry.value}</option>
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
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Добавить</button>
                            </div>
                        </div>
                    </form>

                </div>
                <%--col-sm-9--%>

            </div>
            <div class="col-sm-6">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">Быстрое добавление сделки</h3>
                    </div>
                    <br><br>

                    <form class="form-horizontal" role="form" method="POST" action="/company?action=quickAddDeal"
                          accept-charset="UTF-8">

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
                                    <c:forEach var="entry" items="${phaseMap}">
                                        <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="position">Бюджет:</label>
                            <input type="text" id="budget" name="budget">
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Добавить</button>
                            </div>
                        </div>
                    </form>

                </div>
                <%--col-sm-9--%>

            </div>

            <%--row--%>
        </div>
        <%--main--%>
    </div>
    <%--row--%>
</div>
<%--container-fluid--%>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
<%--<script type="text/javascript" src="<c:url value="${pageContext.request.contextPath}/resources/js/task.js"/>"></script>--%>


