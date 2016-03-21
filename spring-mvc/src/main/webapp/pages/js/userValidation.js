(function () {
    self.Ajax = function (elements) {
        this.elements = elements;
        this.datas_to_send = {};
    };

    self.Ajax.prototype = {
        ajaxProccess: function () {
            var reference = this;
            var url;
            if (this.elements["action"] === "login") {
                this.datas_to_send["email"] = this.elements["email"];
                this.datas_to_send["password"] = this.elements["password"];
                url = "/userLogin";
            } else if (this.elements["action"] === "logout") {
                url = "/userLogout";
            } else if (this.elements["action"] === "register") {
                this.datas_to_send["userName"] = this.elements["userName"];
                this.datas_to_send["email"] = this.elements["email"];
                this.datas_to_send["password"] = this.elements["password"];
                this.datas_to_send["password2"] = this.elements["password2"];
                url = "/userRegistrator";
            } else if (this.elements["action"] === "recovery") {
                this.datas_to_send["email"] = this.elements["email"];
                url = "/passwordRecovery";
            }
            $.ajax({
                data: reference.datas_to_send,
                url: url,
                type: "POST",
                dataType: "json",
                encode: true,
                cache: false,
                success: function (data) {
                    if (data.hasOwnProperty("success")) {
                        $(location).attr('href', 'dashboard.jsp');
                    } else if (data.hasOwnProperty("logout")) {
                        $(location).attr('href', 'login.jsp');
                    } else if (data.hasOwnProperty("recovery")) {
                        $(location).attr('href', 'login.jsp');
                    } else {
                        new Errors(data).displayError();
                    }
                }
            });
        }
    };
})();

(function () {
    self.Errors = function (elements) {
        this.elements = elements;
    };
    self.Errors.prototype = {
        displayError: function () {
            var errors;
            if (this.elements.hasOwnProperty("Error")) {
                errors = $($(".error")[0]);
                $($(".input-field col")[0]).addClass("has-error");
                errors.html(this.elements.Error);
                errors.css("color", "red");
                errors.css("font-size", "95%");
            } else {
                $($(".error")[0]).empty();
                $($(".input-field col")[0]).removeClass("has-error");
            }
        }
    };
})();

$(document).on("ready", function () {

    $("#login-form").on("submit", function (e) {
        e.preventDefault();
        var elements = [];
        elements["email"] = ($($("#login-form :input")[0]).val());
        elements["password"] = ($($("#login-form :input")[1]).val());
        elements["action"] = "login";
        new Ajax(elements).ajaxProccess();
    });

    $("#registration-form").on("submit", function (e) {
        e.preventDefault();
        var elements = [];
        elements["userName"] = ($($("#registration-form :input")[0]).val());
        elements["email"] = ($($("#registration-form :input")[1]).val());
        elements["password"] = ($($("#registration-form :input")[2]).val());
        elements["password2"] = ($($("#registration-form :input")[3]).val());
        elements["action"] = "register";
        new Ajax(elements).ajaxProccess();
    });

    $("#recovery-form").on("submit", function (e) {
        e.preventDefault();
        var elements = [];
        elements["email"] = ($($("#recovery-form :input")[0]).val());
        elements["action"] = "recovery";
        new Ajax(elements).ajaxProccess();
    });

    $("#log-out").on("click", function () {
        var elements = [];
        elements["action"] = "logout";
        new Ajax(elements).ajaxProccess();
    });
});