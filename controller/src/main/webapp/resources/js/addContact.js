$(document).ready(function() {


    var addContactForm = $('#addContactForm');

    $(addContactForm).formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            contact_name: {
                // The messages for this field are shown as usual
                validators: {
                    notEmpty: {
                        message: 'Введите имя контакта'
                    },
                }
            },
        }
    });


    $('#setCompanyToContactButton').click(function(){
        $(addContactForm).formValidation('addField', 'company', getNotEmptyValidator('Выберите компанию'));
    });

    $('#addDealToContactButton').click(function(){
        $(addContactForm).formValidation('addField', 'deal_name', getNotEmptyValidator('Введите название сделки'));
        $(addContactForm).formValidation('addField', 'deal_budget', getNotEmptyValidator('Введите бюджет'));
    });

    $('#addCompanyToContactButton').click(function(){
        $(addContactForm).formValidation('addField', 'company_name', getNotEmptyValidator('Введите название компании'));
    });

    $('#addTaskToContactButton').click(function(){
        $(addContactForm).formValidation('addField', 'task_name', getNotEmptyValidator('Введите название задачи'));
    });


});

function getNotEmptyValidator(message){
    var notEmptyValidator = {
        validators: {
            notEmpty: {
                message: message
            }
        }
    };
    return notEmptyValidator;
}
