$(document).ready(function(){
    $("#calendar").val(getDate("today"));
});

getDate = function(id){
    var now = new Date();

    if(id == "today"){
        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);

        return now.getFullYear()+"-"+(month)+"-"+(day);
    } else if (id == "tomorrow"){
        var day =   ("0" + (now.getDate() +  1)).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);

        return now.getFullYear()+"-"+(month)+"-"+(day);
    } else if (id == "nextWeek"){
        Date.prototype.setDay = function(dayOfWeek) {
            this.setDate(this.getDate() - this.getDay() + dayOfWeek);
        };
        var d = new Date();
        d.setDay(8);

        var day =   ("0" + d.getDate()).slice(-2);
        var month = ("0" + (d.getMonth() + 1)).slice(-2);

        return now.getFullYear()+"-"+(month)+"-"+(day);
    } else if (id == "nextMonth"){

        if (now.getMonth() == 11) {
            var current = new Date(now.getFullYear() + 1, 0, 1);

            var day = ("0" + current.getDate()).slice(-2);
            var month = ("0" + (current.getMonth()+1)).slice(-2);

            return current.getFullYear()+"-"+(month)+"-"+(day);

        } else {
            var month = ("0" + (now.getMonth() + 2)).slice(-2);
            return now.getFullYear()+"-"+(month)+"-01";
        }

    } else if (id == "nextYear"){
        var current = new Date(now.getFullYear() + 1, 0, 1);
        return current.getFullYear()+"-01-01";
    }
};

$("#periodList").change(function(){
    var value = $(this).val();

    if(value == "allday"){
        $("#timeList").prop("disabled",true);
    } else {
        $("#timeList").prop("disabled",false);
    }
    var calcuatedDate = getDate(value);
    $("#calendar").val(calcuatedDate);
});

$("#nameList").change(function(){
    var value = $(this).val();

    if(value == "company"){
        $("#dealDiv").css("display","none");
        $("#contactDiv").css("display","none");
        $("#companyDiv").css("display","block");
    }else if(value == "deal"){
        $("#companyDiv").css("display","none");
        $("#contactDiv").css("display","none");
        $("#dealDiv").css("display","block");
    }else if(value == "contact"){
        $("#dealDiv").css("display","none");
        $("#companyDiv").css("display","none");
        $("#contactDiv").css("display","block");
    }
});

getDay = function(){

    var date = $('#dayCalendar').val();

    $('td[id^=td_]').empty();
    $('#responseMessage').empty();
    $('tr[id^=tr_]').removeClass('success');
    $('#currentDate').text(date);

    $.ajax({
        url: 'ajax/getDay',
        data: {date: date},
        type: "post",
        success : function(data) {
            if(data != "NO DATA"){
                processData(data);
            } else {
                $('#responseMessage').html('<h4><strong>Задания на выбраную дату отсутствуют</strong></h4>');
            }
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });//ajax
};

processData = function(data){
    var json = data;
    console.log(json);

    for(var i=0; i<json.length; i++){
        var task = json[i];

        var hour = task.planTime.time.hour + '\\:00';
        var id = (hour.length == 6) ? hour : ('0' + hour);

        var taskType = task.taskType;
        var target;
        var targetName;

        if(task.deal){
            target = "Зделка:";
            targetName = task.deal.dealName;
        } else if(task.contact){
            target = "Контакт:";
            targetName = task.contact.nameSurname;
        } else if(task.company){
            target = "Компания:";
            targetName = task.company.companyName;
        }

        insertTaskDiv(id, taskType, target, targetName);
    }
};

insertTaskDiv = function(id, taskType, target, targetName){

    $('#tr_' + id).addClass('success');

    $('#td_' + id).append('<div class="col-md-6"><div class="panel panel-danger">'+
        '<div class="panel-heading text-center">' + taskType + '</div><table class="table">' +
        '<tbody><tr><td><h5><strong>Текст Задачи:</strong></h5></td>'+
        '<td><h5><p class="text-left"></p></h5></td></tr>'+
        '<tr><td class="col-md-1"><h5><strong>' + target + '</strong></h5></td>'+
        '<td><h5><p class="text-left">' + targetName + '</p></h5></td></tr>' +
        '</tbody></table></div></div>');
};