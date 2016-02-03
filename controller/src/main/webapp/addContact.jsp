<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <jsp:include page="fragments/leftMenu.jsp"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="page-header">Добавить контакт</h2>
               <form id="addContactForm" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label for="contact_name" class="control-label col-xs-1">Имя Фамилия</label>

                        <div class="col-xs-11">
                            <input type="text" class="form-control" id="contact_name" name="contact_name" placeholder="contact_name">
                        </div>
                    </div>

                   <div class="form-group">
                       <label for="tegs" class="control-label col-xs-1">Теги</label>

                       <div class="col-xs-11">
                           <input type="text" class="form-control" id="tegs" name="tegs" placeholder="tegs">
                       </div>
                   </div>

                    <div class="form-group">
                        <label for="responsible" class="control-label col-xs-1">Ответственный</label>
                        <div class="col-xs-11">
                            <select class="form-control" id="responsible" name="responsible">
                            <c:forEach items="${users}" var="user">
                                <option value="<c:out value="${user.id}""> <c:out value="${user.name}" </option>
                            </c:forEach>
                                <option value="1">Иванов Иван Иванович</option>
                                <option value="2">Петров Петр Петрович</option>
                                <option value="3">Сидоров Семен Михайлович</option>
                                <option value="4">Татарко Игорь Петрович</option>
                            </select>
                        </div>
                    </div>

                   <div class="form-group">
                       <label for="position" class="control-label col-xs-1">Должность</label>

                       <div class="col-xs-11">
                           <input type="text" class="form-control" id="position" name="position" placeholder="position">
                       </div>
                   </div>

                   <div class="form-group">
                       <label for="phone_number" class="control-label col-xs-1">Номер телефона</label>

                       <div class="col-xs-1">
                           <select class="form-control" id="phone_type" name="phone_type">
                               <option value="1">Мобильный</option>
                               <option value="2">Домашний</option>
                               <option value="3">Рабочий</option>
                               <option value="4">Факс</option>
                           </select>
                       </div>
                       <div class="col-xs-10">
                           <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="phone_number">
                       </div>
                   </div>

                   <div class="form-group">
                       <label for="email" class="control-label col-xs-1">Email</label>

                       <div class="col-xs-11">
                           <input type="text" class="form-control" id="email" name="email" placeholder="email">
                       </div>
                   </div>

                   <div class="form-group">
                       <label for="skype" class="control-label col-xs-1">skype</label>

                       <div class="col-xs-11">
                           <input type="text" class="form-control" id="skype" name="skype" placeholder="skype">
                       </div>
                   </div>


                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-3">
                            <button type="submit" class="btn btn-primary">Добавить контакт</button>
                        </div>
                    </div>
                </form>

        </div>
    </div>
</div>


</body>
</html>
</body>

