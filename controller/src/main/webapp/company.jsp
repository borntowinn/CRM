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
                    <h3>выпад меню</h3>
                    <div class="dropdown">
                        <!-- Link or button to toggle dropdown -->
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                            <li><a tabindex="-1" href="#">Действие</a></li>
                            <li><a tabindex="-1" href="#">Другое действие</a></li>
                            <li><a tabindex="-1" href="#">Еще ссылка</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" href="#">Доп. ссылка</a></li>
                        </ul>
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

                </div>
                <%--col-sm-3--%>

                <div class="col-sm-9">
                    <div class="panel panel-success">
                        <h3 class="page-header">Список компаний</h3>

                        <table class="table table-bordered">
                            <tr>
                                <td class="active">Наименования</td>
                                <td class="active">Телефон</td>
                                <td class="active">Email</td>
                            </tr>
                            <c:forEach var="company" items="${companies}">
                                <tr>
                                    <td class="active">
                                        <p>${company.companyName}</p></td>
                                    <td class="active"><p>${company.phoneNumber}</p></td>
                                    <td class="active"><p>${company.email}</p></td>
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
