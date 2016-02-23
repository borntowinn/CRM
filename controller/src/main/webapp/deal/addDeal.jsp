<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CRM:add deal</title>
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

            <form class="form-horizontal" role="form" method="POST" action="/deal"
                  accept-charset="UTF-8" enctype="multipart/form-data">

                <div class="col-sm-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">+ Добавить сделку</h3>
                        </div>
                        <br><br>


                        <div class="form-group">
                            <label class="control-label col-sm-5" for="dealName">Название:</label>
                            <div class="col-sm-5">
                                <input type="text" id="dealName" name="dealName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="tags">Теги:</label>
                            <div class="col-sm-7">
                                <textarea class="from-control" rows="4" id="tags" name="tags"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="budget">Бюджет:</label>
                            <div class="col-sm-5">
                                <input type="text" id="budget" name="budget">
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
                            <label class="control-label col-sm-5" for="note">Примечание:</label>
                            <div class="col-sm-7">
                                <textarea class="from-control" rows="4" id="note" name="note"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <input type="file" id="fileData" name="fileData">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="companyList">Компания:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="companyList" name="company">
                                    <c:forEach var="entry" items="${companyMap}">
                                        <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="companyList">Контакт:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="contactList" name="contact">
                                    <c:forEach var="entry" items="${contactMap}">
                                        <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="addTask">Добавить таск:</label>
                            <input type="checkbox" id="addTask" name="addTask">
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Добавить контакт</h3>
                        </div>
                        <br><br>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="nameSurname">ФИО:</label>
                            <input type="text" id="nameSurname" name="nameSurname">
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="company">Компания:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="company" name="company">
                                    <c:forEach var="entry" items="${companyMap}">
                                        <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
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
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Запланировать действие</h3>
                        </div>
                        <br><br>

                        <div class="form-group">
                            <label class="control-label col-sm-3" for="type">Время:</label>

                            <div class="col-sm-6">
                                <select class="form-control" id="timeList" name="timeName">
                                    <c:forEach items="${requestScope.timeList}" var="hour" varStatus="loop">
                                        <option value="${hour}">${hour}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" for="periodList">Период:</label>

                            <div class="col-sm-6">
                                <select class="form-control" id="periodList" name="periodName">
                                    <option value="default">Выбрать</option>
                                    <c:forEach var="entry" items="${periodMap}">
                                        <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" for="responsibleList">Ответственный:</label>
                            <div class="col-sm-6">
                                <select class="form-control" id="responsibleList1" name="responsibleName">
                                    <c:forEach var="entry" items="${userMap}">
                                        <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-3" for="type">Тип:</label>

                            <div class="col-sm-6">
                                <select class="form-control" id="type" name="typeName">
                                    <c:forEach var="entry" items="${typeMap}">
                                        <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-10">
                        <button type="submit" class="btn btn-default">Добавить</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
