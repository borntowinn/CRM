$(document).ready(function() {

    var addDealForm = $('#addDeal');
    var addContactForm = $('#addContactForm');
    var setCompanyForm = $('#setCompanyForm');
    var addCompanyForm = $('#addCompanyForm');

    //$(addContactForm).formValidation({
    //    framework: 'bootstrap',
    //    icon: {
    //        valid: 'glyphicon glyphicon-ok',
    //        invalid: 'glyphicon glyphicon-remove',
    //        validating: 'glyphicon glyphicon-refresh'
    //    },
    //    fields: {
    //        contact_name: {
    //            // The messages for this field are shown as usual
    //            validators: {
    //                notEmpty: {
    //                    message: 'Введите имя контакта'
    //                },
    //            }
    //        },
    //    }
    //});

    //$(addDealForm).formValidation({
    //    framework: 'bootstrap',
    //    icon: {
    //        valid: 'glyphicon glyphicon-ok',
    //        invalid: 'glyphicon glyphicon-remove',
    //        validating: 'glyphicon glyphicon-refresh'
    //    },
    //    fields: {
    //        deal_name: {
    //            // The messages for this field are shown as usual
    //            validators: {
    //                notEmpty: {
    //                    message: 'Введите название сделки'
    //                },
    //            }
    //        },
    //    }
    //});

    $(addCompanyForm).formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            conpany_name: {
                // The messages for this field are shown as usual
                validators: {
                    notEmpty: {
                        message: 'Введите название компании'
                    },
                }
            },
        }
    });

    $('#setCompanyToContactButton').click(function(){
        addHiddenFieldToForm(addContactForm, 'company_id', $('#company').val() );
        $('#addContactForm').submit();
    });

    $('#addDealToContactButton').click(function(){
        addHiddenFieldToForm(addContactForm, 'deal_name', $('#deal_name').val() );
        addHiddenFieldToForm(addContactForm, 'deal_phase', $('#phase').val() );
        addHiddenFieldToForm(addContactForm, 'deal_budget', $('#budget').val() );
        $('#addContactForm').submit();
    });

    $('#addCompanyToContactButton').click(function(){
        addHiddenFieldToForm(addContactForm, 'company_id', $('#company_id').val() );
        addHiddenFieldToForm(addContactForm, 'company_name', $('#company_name').val() );
        addHiddenFieldToForm(addContactForm, 'company_phone', $('#company_phone').val() );
        addHiddenFieldToForm(addContactForm, 'web_address', $('#web_address').val() );
        addHiddenFieldToForm(addContactForm, 'company_address', $('#company_address').val() );
        $('#addContactForm').submit();
    });

    $('#addTaskToContactButton').click(function(){
        addHiddenFieldToForm(addContactForm, 'task_name', $('#task_name').val() );
        addHiddenFieldToForm(addContactForm, 'datetime_to', ($('#date_to').val() + "T" + $('#to_time').val() + ":00"));
        addHiddenFieldToForm(addContactForm, 'task_period', $('#task_period').val());
        addHiddenFieldToForm(addContactForm, 'task_type', $('#task_type').val());
        addHiddenFieldToForm(addContactForm, 'task_responsible', $('#task_responsible').val());
        $('#addContactForm').submit();
    });


});



function addHiddenFieldToForm(theForm, key, value) {
    // Create a hidden input element, and append it to the form:
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = key;
    input.value = value;
    theForm.append(input);
}
