<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.becomejavasenior.User" %>
<%@ page import="java.util.List" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2 col-md-2 sidebar">
            <jsp:include page="fragments/leftMenu.jsp"/>
        </div>
        <div class="col-sm-5 col-sm-offset-2 col-md-5 col-md-offset-2 main">


            <h2 class="page-header">Добавить контакт</h2>
               <form id="addContactForm" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label for="contact_name" class="control-label col-xs-2">Имя Фамилия</label>

                        <div class="col-xs-10">
                            <input type="text" class="form-control" id="contact_name" name="contact_name" placeholder="Имя Фамилия">
                        </div>
                    </div>

                   <div class="form-group">
                       <label for="tegs" class="control-label col-xs-2">Теги</label>

                       <div class="col-xs-10">
                           <input type="text" class="form-control" id="tegs" name="tegs" placeholder="Теги">
                       </div>
                   </div>

                    <div class="form-group">
                        <label for="responsible" class="control-label col-xs-2">Ответственный</label>
                        <div class="col-xs-10">
                            <select class="form-control" id="responsible" name="responsible">
                                <% List<User> users = (List<User>) request.getAttribute("userList");
                                    for (User user  : users ) { %>
                                        <option value="<% out.print(user.getId()); %>"><% out.println(user.getName()); %></option>
                                    <%
                                    }
                                %>
                            <%--<c:forEach items="${userList}" var="user">--%>

                                <%--<jsp:useBean id="user" scope="page" type="com.becomejavasenior.User"/>--%>
                                <%--<option value="${user.id}">${user.name}</option>--%>
                            <%--</c:forEach>--%>
                            </select>

                        </div>
                    </div>

                   <div class="form-group">
                       <label for="position" class="control-label col-xs-2">Должность</label>

                       <div class="col-xs-10">
                           <input type="text" class="form-control" id="position" name="position" placeholder="Должность">
                       </div>
                   </div>

                   <div class="form-group">
                       <label for="phone_number" class="control-label col-xs-2">Номер телефона</label>

                       <div class="col-xs-3">
                           <select class="form-control" id="phone_type" name="phone_type">
                               <option value="1">Мобильный</option>
                               <option value="2">Домашний</option>
                               <option value="3">Рабочий</option>
                               <option value="4">Факс</option>
                           </select>
                       </div>
                       <div class="col-xs-7">
                           <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="Номер телефона">
                       </div>
                   </div>

                   <div class="form-group">
                       <label for="email" class="control-label col-xs-2">Email</label>

                       <div class="col-xs-10">
                           <input type="text" class="form-control" id="email" name="email" placeholder="email">
                       </div>
                   </div>

                   <div class="form-group">
                       <label for="skype" class="control-label col-xs-2">skype</label>

                       <div class="col-xs-10">
                           <input type="text" class="form-control" id="skype" name="skype" placeholder="skype">
                       </div>
                   </div>

                   <div class="form-group">
                       <label for="note" class="control-label col-xs-2">Примечание</label>

                       <div class="col-xs-10">
                           <textarea class="form-control" id="note" name="note" placeholder="Примечание"></textarea>
                       </div>
                   </div>

                   <div class="form-group">
                       <label for="file" class="control-label col-xs-2">Добавить файлы</label>

                       <div class="col-xs-10">
                           <input type = "file" id = "file">
                       </div>
                   </div>


                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-3">
                            <button type="submit" class="btn btn-primary">Добавить контакт</button>
                        </div>
                    </div>
                </form>

        </div>

        <div class="col-sm-5 col-md-5 main">
            <h2 class="page-header">Запланировать действие</h2>
            <form id="addTaskForm" method="post" class="form-horizontal">
                <div class="form-group">

                    <div class="col-xs-6">
                        <input type="date" class="form-control" id="date_from" name="date_from" >
                    </div>

                    <div class="col-xs-6">
                        <select class="form-control" id="from_time" name="from_time">
                            <option value="1">05:00</option>
                            <option value="2">06:00</option>
                            <option value="3">07:00</option>
                            <option value="4">08:00</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">

                    <div class="col-xs-6">
                        <input type="date" class="form-control" id="date_to" name="date_to" >
                    </div>

                    <div class="col-xs-6">
                        <select class="form-control" id="time_to" name="time_to">
                            <option value="1">05:00</option>
                            <option value="2">06:00</option>
                            <option value="3">07:00</option>
                            <option value="4">08:00</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">

                    <div class="col-xs-6">
                        <select class="form-control" id="period" name="period">
                            <option value="1">Следующая неделя</option>
                            <option value="2">Весь день</option>
                            <option value="3">Следующий меся</option>
                            <option value="4">Сегодня</option>
                        </select>
                    </div>

                    <div class="col-xs-6">
                        <label><input type="checkbox" value=""> Весь день</label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="responsible_task" class="control-label col-xs-2">Ответственный</label>
                    <div class="col-xs-10">
                        <select class="form-control" id="responsible_task" name="responsible">
                            <% for (User user  : users ) { %>
                            <option value="<% out.print(user.getId()); %>"><% out.println(user.getName()); %></option>
                            <%
                                }
                            %>
                            <%--<c:forEach items="${userList}" var="user">--%>

                            <%--<jsp:useBean id="user" scope="page" type="com.becomejavasenior.User"/>--%>
                            <%--<option value="${user.id}">${user.name}</option>--%>
                            <%--</c:forEach>--%>
                        </select>

                    </div>
                </div>

                <div class="form-group">
                    <label for="task_type" class="control-label col-xs-2">Тип задачи</label>
                    <div class="col-xs-10">
                        <select class="form-control" id="task_type" name="task_type">
                            <option value="1">Встреча</option>
                            <option value="2">Домашний</option>
                            <option value="3">Рабочий</option>
                            <option value="4">Факс</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="task_text" class="control-label col-xs-2">Текст задачи</label>

                    <div class="col-xs-10">
                        <textarea class="form-control" id="task_text" name="task_text" placeholder="Текст задачи"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-3">
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="col-sm-5 col-sm-offset-2 col-md-5 col-md-offset-2 main">
            <h2 class="page-header">Быстрое добавление сделки</h2>
            <form id="addDeal" method="post" class="form-horizontal">
                <div class="form-group">
                    <label for="deal_name" class="control-label col-xs-2">Название</label>

                    <div class="col-xs-10">
                        <input type="text" class="form-control" id="deal_name" name="contact_name" placeholder="Название">
                    </div>
                </div>

                <div class="form-group">
                    <label for="phase" class="control-label col-xs-2">Этап</label>
                    <div class="col-xs-10">
                        <select class="form-control" id="phase" name="phase">
                            <option value="1">Первичный контакт</option>
                            <option value="2">Домашний</option>
                            <option value="3">Рабочий</option>
                            <option value="4">Факс</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="budget" class="control-label col-xs-2">Название</label>

                    <div class="col-xs-10">
                        <input type="text" class="form-control" id="budget" name="budget" placeholder="Бюджет">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-3">
                        <button type="submit" class="btn btn-primary">Добавить сделку</button>
                    </div>
                </div>
            </form>
        </div>



        <div class="col-sm-5 col-md-5 main">
            <h2 class="page-header">Добавить компанию</h2>
            <form id="setCompanyForm" method="post" class="form-horizontal">
                <div class="form-group">
                    <label for="company" class="control-label col-xs-2">Компания</label>
                    <div class="col-xs-10">
                        <select class="form-control" id="company" name="phase">
                            <option value="1">Компания1</option>
                            <option value="2">Компания2</option>
                            <option value="3">Компания3</option>
                            <option value="4">Компания4</option>
                        </select>
                    </div>
                </div>
            </form>
            <h3 class="page-header">Новая компания</h3>
            <form id="addCompanyForm" method="post" class="form-horizontal">
                <div class="form-group">
                    <label for="conpany_name" class="control-label col-xs-2">Название</label>

                    <div class="col-xs-10">
                        <input type="text" class="form-control" id="conpany_name" name="conpany_name" placeholder="Название">
                    </div>
                </div>

                <div class="form-group">
                    <label for="company_phone" class="control-label col-xs-2">Телефон</label>

                    <div class="col-xs-10">
                        <input type="text" class="form-control" id="company_phone" name="company_phone" placeholder="Телефон">
                    </div>
                </div>

                <div class="form-group">
                    <label for="web_address" class="control-label col-xs-2">Web-адрес</label>

                    <div class="col-xs-10">
                        <input type="text" class="form-control" id="web_address" name="web_address" placeholder="Web-адрес">
                    </div>
                </div>

                <div class="form-group">
                    <label for="company_address" class="control-label col-xs-2">Адрес</label>

                    <div class="col-xs-10">
                        <input type="text" class="form-control" id="company_address" name="company_address" placeholder="Адрес">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-3">
                        <button type="submit" class="btn btn-primary">Добавить компанию</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>


</body>
</html>
</body>

