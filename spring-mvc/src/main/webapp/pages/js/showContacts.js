$(document).ready(function () {
    //$('#whenCreated').selectmenu(); jQuery UI functionality will be added later
    $('#contactsTable').DataTable({
            scrollY: 395,
            paging: false,
            colReorder: false
        }
    );
    $('#mycontacts').click(function () {
        tableAjax("showContacts", "#contactsTable", false);
    });
    $('#mycompanies').click(function () {
        tableAjax("showCompanies", "#companiesTable", true);
    });
});
function tableAjax(showAction, tableId, colReordered) {
    $.get('contacts', {user: showAction}, function (html) {
        $('#myContactsCompaniesTable').html(html);
        $(tableId).DataTable({
                scrollY: 395,
                paging: false,
                colReorder: colReordered
            }
        );
    });
}
