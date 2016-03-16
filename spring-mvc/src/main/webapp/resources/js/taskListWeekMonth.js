$(document).ready(function(){

    var calendarView;
    var urlArray = location.pathname.split("/");
    if ( urlArray.length > 2 && urlArray[2] == "listWeek" ) {
        calendarView = 'agendaWeek'//basicWeek
    } else {
        calendarView = 'month';
    }

    $('#calendar').fullCalendar({

        defaultView: calendarView,

        header: {
            left: 'prev,next',
            center: 'title',
            right: 'today'
        },

        views: {
            month:{
                timeFormat: 'HH:mm'
            }
            //agendaWeek:{
            //    //timeFormat: 'HH:mm'
            //}

        },

        events: function(start, end, timezone, callback) {
            $.ajax({
                url: 'ajax/getPeriod',
                type: "post",
                data: {
                    start: start.unix(),
                    end: end.unix()
                },
                success: function(data) {
                    console.log(data);
                    var events = [];

                    for(var i = 0; i < data.length; i++) {
                        var task = data[i];

                        var task_id = task.id;
                        var year = task.planTime.date.year;
                        var month = task.planTime.date.month;
                        var day = task.planTime.date.day;
                        var hours = task.planTime.time.hour;
                        var minutes = task.planTime.time.minute;
                        var start = new Date(year, (month-1), day, hours, minutes);
                        var end = new Date(year, (month-1), day, (hours+1), minutes);
                        var allDayFlag = (hours == 0) ? true : false;
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

                        events.push({
                            id: task_id,
                            title: task.taskType,
                            start: start,
                            end: end,
                            allDay: allDayFlag,
                            taskComment: task.comment,
                            target: target,
                            targetName: targetName
                        });
                    }

                    callback(events);
                }//success
            });//ajax
        },//events

        eventClick: function(event) {
            $('#modalHeader').text(event.title);
            $('#modalTarget').text(event.target);
            $('#modalTargetName').text(event.targetName);
            $('#modalText').text(event.taskComment);
            $('#myModal').modal('toggle');
        }

    })//calendar
});//document-ready