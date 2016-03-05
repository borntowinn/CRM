<jsp:useBean id="contact" scope="request" type="com.becomejavasenior.Contact"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRM:edit contact</title>
</head>
<jsp:include page="../fragments/headTag.jsp"/>
<body>
<jsp:include page="../fragments/header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2 col-md-2 sidebar">
            <jsp:include page="../fragments/leftMenu.jsp"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <form class="form-horizontal" role="form" method="POST" action="/contact?contactId=?"
                  accept-charset="UTF-8" enctype="multipart/form-data">

                <div class="col-sm-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Добавить контакт</h3>
                        </div>
                        <br><br>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="nameSurname">Имя фамилия:</label>
                            <input type="text" value = "${contact.getNameSurname}" id="nameSurname" name="nameSurname">
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="tags">Теги:</label>
                            <div class="col-sm-7">
                                <textarea class="from-control" rows="4" id="tags" name="tags"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="responsibleList">Ответственный:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="responsibleList" name="responsible">
                                    <c:forEach var="entry" items="${userMap}">
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
                            <input type="text" id="contactPhoneNumber" name="phoneNumber">
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="contactEmail">Email:</label>
                            <input type="email" id="contactEmail" name="email">
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="skype">Skype:</label>
                            <input type="text" id="skype" name="skype">
                        </div>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Добавление компании</h3>
                        </div>
                        <br><br>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="companyName">Название компании:</label>
                            <input type="text" id="companyName" name="companyName">
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-10">
                                <button type="button" class="btn btn-default" href="#">Перейти в редактор компании</button>
                                <button type="button" class="btn btn-default">Открепить</button>
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
                            <input type="text" id="webAddress" name="website">
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="address">Адрес:</label>
                            <input type="text" id="address" name="address">
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-10">
                                <button type="button" class="btn btn-default">Отмена</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-10">
                        <button type="button" class="btn btn-default" href="#">Добавить задачу</button>
                        <button type="button" class="btn btn-default">Добавить примечание</button>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5" for="note">Примечание:</label>
                    <div class="col-sm-7">
                        <textarea class="from-control" rows="4" id="note" name="note"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="file" id="fileData" name="fileData">
                    </div>
                </div> <%--make it show or hire--%>
                <div class="form-group">
                    <label class="control-label col-sm-5" for="phaseList">Примечания компании:</label>
                    <div class="col-sm-5">
                        <select class="form-control" id="commentsCompany" name="comCompany">
                            <c:forEach var="entry" items="${commentsCompany}">
                                <option value="${entry.key}">${entry.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5" for="phaseList">Примечания сделки:</label>
                    <div class="col-sm-5">
                        <select class="form-control" id="commentsDeal" name="comDeal">
                            <c:forEach var="entry" items="${commentsDeal}">
                                <option value="${entry.key}">${entry.value}</option>
                            </c:forEach>
                        </select>
                    </div>

                <div class="col-sm-6">
                    <div class="panel panel-success">
                        <div class="table-responsive">
                            <table id="contactsTable" class="display table table-striped table-bordered">
                                <thead style="color: #428bca">
                                <tr>
                                    <th>Название сделки</th>
                                    <th>Этап</th>
                                    <th>Бюджет</th>
                                    <th>Валюта</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="deal" items="${deals}">
                                    <tr>
                                        <td><p><a href="javascript:void(0)">${deal.dealName}</a></p></td>
                                        <td><p><a href="javascript:void(0)">${deal.phase.phase}</a></p></td>
                                        <td><p>${deal.budget}</p></td>
                                        <%--<td><p>${deal.currency}</p></td>--%>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel-heading">
                            <h3 class="panel-title">Добавить сделку</h3>
                        </div>
                        <br><br>


                        <div class="form-group">
                            <label class="control-label col-sm-5" for="dealName">Название:</label>
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
                            <label class="control-label col-sm-5" for="budget">Бюджет:</label>
                            <div class="col-sm-5">
                                <input type="text" id="budget" name="budget">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-10">
                                <button type="button" class="btn btn-default">Отмена</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-10">
                        <button type="submit" class="btn btn-default">Добавить</button>
                    </div>
                </div>
                </div>
            </form>
        </div> <%--class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"--%>
    </div><%--class="row"--%>
</div><%--class="container-fluid"--%>

</body>
</html>
