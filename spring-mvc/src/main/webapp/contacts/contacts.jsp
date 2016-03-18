<%--
<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 12.02.2016
  Time: 00:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="/fragments/headTag.jsp"/>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/fragments/header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar">
            <jsp:include page="/fragments/leftMenu.jsp"/>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="row">
                <div class="col-sm-3">
                    <div type="button">
                        <button id="mycontacts" type="button" class="btn btn-default" style="color: #428bca">Контакты
                        </button>
                        <button id="mycompanies" type="button" class="btn btn-default" style="color: #428bca">Компании
                        </button>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="optionsRadios" value="option1" checked>

                            Полный список
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="optionsRadios" value="option2">
                            Без задач
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
                            С просроченными
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                            Удаленные
                        </label>
                    </div>
                    <form action="#">
                        <fieldset>
                            <select style="width: 180px" name="when" id="whenCreated">
                                <option selected="selected">За все время</option>
                                <option>За сегодня</option>
                                <option>За 3 дня</option>
                                <option>За неделю</option>
                                <option>За месяц</option>
                                <option>За квартал</option>
                                <option>За период</option>
                            </select>
                        </fieldset>
                    </form>
                    <div>
                        <button type="submit" class="btn btn-default" style="color: #428bca">Созданы</button>
                        <button type="submit" class="btn btn-default" style="color: #428bca">Изменены</button>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Без сделок
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Без открытых сделок
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Первичный контакт
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Переговоры
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Принимают решения
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Согласование договора
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Успешно реализована
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Закрыто
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Не реализована
                        </label>
                    </div>
                    <div class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">Менеджеры <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                        </ul>
                    </div>
                    <form style="margin-top: 10px;" action="#">
                        <fieldset>
                            <select style="width: 180px" name="period" id="period">
                                <option selected="selected">Не учитывать</option>
                                <option>На сегодня</option>
                                <option>На завтра</option>
                                <option>На этой неделе</option>
                                <option>В этом месяце</option>
                                <option>В этом квартале</option>
                                <option>Нет задач</option>
                                <option>Просрочены</option>
                            </select>
                        </fieldset>
                    </form>
                    <div class="dropdown" style="margin-bottom: 10px">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">Теги <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                        </ul>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-default" style="color: #428bca">Очистить</button>
                        <button type="submit" class="btn btn-default" style="color: #428bca">Применить</button>
                    </div>

                </div>
                <%--col-sm-3--%>

                <div class="col-sm-9">
                    <div id="myContactsCompaniesTable">
                        <jsp:include page="/tagsToContacts/contactsTable.jsp"/>
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>
</div>
<jsp:include page="../fragments/footer.jsp"/>

<script type="text/javascript"
        src="<c:url value="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.min.js"/>"></script>
<script type="text/javascript"
        src="<c:url value="${pageContext.request.contextPath}/webjars/jquery-ui/1.11.4/jquery-ui.js"/>"></script>
<script type="text/javascript"
        src="<c:url value="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
        src="<c:url value="${pageContext.request.contextPath}/webjars/datatables/1.10.10/js/jquery.dataTables.min.js"/>"></script>
<script type="text/javascript"
        src="<c:url value="${pageContext.request.contextPath}/webjars/datatables-colreorder/1.2.0/js/dataTables.colReorder.js"/>"></script>
<script type="text/javascript"
        src="<c:url value="${pageContext.request.contextPath}/resources/js/showContacts.js"/>"></script>
</body>
</html>
