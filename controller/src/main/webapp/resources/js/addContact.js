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

    $('#addDeal').click(function(){
        addHiddenFieldToForm(addContactForm, 'deal_name', $('#deal_name').val() );
        addHiddenFieldToForm(addContactForm, 'phase', $('#phase').val() );
        addHiddenFieldToForm(addContactForm, 'budget', $('#budget').val() );
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
