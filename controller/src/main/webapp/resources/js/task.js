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
        $("#companyDiv").css("display","block");
    }else if(value == "deal"){
        $("#companyDiv").css("display","none");
        $("#dealDiv").css("display","block");
    }else if(value == "li_contact"){

    }

//        $.ajax({
//            url: 'http://localhost:8080/task/getName',
//            data: flag,
//            type: "get",
//            success : function(data) {
//                console.log("SUCCESS: ", data);
//                display(data);
//            },
//            error : function(e) {
//                console.log("ERROR: ", e);
//                display(e);
//            },
//            done : function(e) {
//                console.log("DONE");
//            }
//        });
});
