<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

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
               <form id="addContactForm" class="form-horizontal" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="contact_name" class="control-label col-xs-2">Имя Фамилия</label>

                        <div class="col-xs-10">
                            <input type="text" class="form-control" id="contact_name" name="contact_name" placeholder="Петр Иванов" required >
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
                                <c:forEach items="${userList}" var="user">
                                    <option value="${user.id}">${user.name}</option>
                                </c:forEach>
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
                           <input id="file" type="file" name="file" multiple data-show-upload="false" data-show-caption="true" />
                       </div>
                   </div>


                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-3">
                            <button type="submit" name="submitButton" class="btn btn-primary">Добавить контакт</button>
                        </div>
                    </div>
                </form>

        </div>

        <div class="col-sm-5 col-md-5 main">
            <h2 class="page-header">Запланировать действие</h2>
            <form id="addTaskForm" method="post" class="form-horizontal">
                <div class="form-group">

                    <div class="form-group">
                        <label for="task_name" class="control-label col-xs-2">Название задачи</label>

                        <div class="col-xs-10">
                            <input type="text" class="form-control" id="task_name" name="task_name" placeholder="Уточнить детали заказа">
                        </div>
                    </div>

                    <div class="col-xs-3">
                        <input type="date" class="form-control" id="date_to" name="date_to" >
                    </div>

                    <div class="col-xs-3">
                        <select class="form-control" id="to_time" name="to_time">
                            <c:forEach items="${hourList}" var="hour">
                                <option value="${hour}">${hour}</option>
                            </c:forEach>
                        </select>
                    </div>


                </div>


                <div class="form-group">

                    <div class="col-xs-6">
                        <select class="form-control" id="task_period" name="task_period">
                            <c:forEach items="${taskPeriods}" var="period">
                                <option value="${period}">${period}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-xs-6">
                        <label><input type="checkbox" value=""> Весь день</label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="task_responsible" class="control-label col-xs-2">Ответственный</label>
                    <div class="col-xs-10">
                        <select class="form-control" id="task_responsible" name="task_responsible">
                            <c:forEach items="${userList}" var="user">
                                <option value="${user.id}">${user.name}</option>
                            </c:forEach>
                        </select>

                    </div>
                </div>

                <div class="form-group">
                    <label for="task_type" class="control-label col-xs-2">Тип задачи</label>
                    <div class="col-xs-10">
                        <select class="form-control" id="task_type" name="task_type">
                            <c:forEach items="${taskTypeList}" var="taskType">
                                <option value="${taskType}">${taskType}</option>
                            </c:forEach>
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
                        <button type="button" class="btn btn-primary" id="addTaskToContactButton">Добавить задачу и контакт</button>
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
                        <input type="text" class="form-control" id="deal_name" name="deal_name" placeholder="Название">
                    </div>
                </div>

                <div class="form-group">
                    <label for="phase" class="control-label col-xs-2">Этап</label>
                    <div class="col-xs-10">
                        <select class="form-control" id="phase" name="phase">
                            <c:forEach items="${phaseList}" var="phase">
                                <option value="${phase.id}">${phase.phase}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="budget" class="control-label col-xs-2">Бюджет</label>

                    <div class="col-xs-10">
                        <input type="text" class="form-control" id="budget" name="budget" placeholder="Бюджет">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-3">
                        <button type="button" class="btn btn-primary" id="addDealToContactButton">Добавить сделку и контакт</button>
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
                        <select class="form-control" id="company" name="company">
                            <option selected value="0">Выберите компанию</option>
                            <c:forEach items="${companyList}" var="company">
                                <option value="${company.id}">${company.companyName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-3">
                        <button type="button" class="btn btn-primary" id="setCompanyToContactButton" >Выбрать компанию и добавить контакт</button>
                    </div>
                </div>

            </form>
            <h3 class="page-header">Новая компания</h3>
            <form id="addCompanyForm" method="post" class="form-horizontal">
                <input type="hidden" id="company_id" value="0"/>
                <div class="form-group">
                    <label for="company_name" class="control-label col-xs-2">Название</label>

                    <div class="col-xs-10">
                        <input type="text" class="form-control" id="company_name" name="company_name" placeholder="Название">
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
                        <button type="button" class="btn btn-primary" id="addCompanyToContactButton">Добавить контакт и компанию</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
<script src="http://formvalidation.io/vendor/formvalidation/js/formValidation.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/framework/bootstrap.min.js"></script>
<script src="resources/js/addContact.js"></script>


</body>
</html>
</body>

