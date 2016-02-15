<%--
<%--
  Created by IntelliJ IDEA.
  User: Katia
  Date: 08.02.2016
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <script type="text/javascript" src="js/jquery-1.8.0.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar">
            <jsp:include page="fragments/leftMenu.jsp"/>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="row">
                <div class="col-sm-3">

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
                            С простроченными
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                            Удаленные
                        </label>
                    </div>
                    <div class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">За все время <b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                        </ul>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-sm">Созданы</button>
                        <button type="submit" class="btn btn-sm">Изменены</button>
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
                    <div class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">Не учитывать <b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                        </ul>
                    </div>
                    <div class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">Теги <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                        </ul>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-sm">Очистить</button>
                        <button type="submit" class="btn btn-sm">Применить</button>
                    </div>

                </div>
                <%--col-sm-3--%>

                <div class="col-sm-9">
                    <div class="panel panel-success">
                        <div style="margin-left: 650px">
                            <button type="button" class="btn btn-default">Создать</button>
                        </div>
                        <h3 class="page-header">Список компаний</h3>

                        <table class="table table-bordered">
                            <tr>
                                <td class="active">Наименования</td>
                                <td class="active">Телефон</td>
                                <td class="active">Email</td>
                            </tr>
                            <c:forEach var="company" items="${companies}">
                                <tr>
                                    <td class="active"><p><a href="javascript:void(0)">${company.companyName}</a></p></td>
                                    <td class="active"><p><a href="javascript:void(0)">${company.phoneNumber}</a></p></td>
                                    <td class="active"><p><a href="javascript:void(0)">${company.email}</a></p></td>
                                </tr>
                            </c:forEach>
                        </table>


                    </div>
                </div>

            </div>


        </div>
    </div>
</div>
</div>


</body>
</html>
