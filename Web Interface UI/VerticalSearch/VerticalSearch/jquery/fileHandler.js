var urlGradeMap = {};
function writeToQrel() {
    urlGradeMap = {};
    var lstOfRadioButtons = $("#tblResults>tbody input:checked");
    getUrlGradingMap(lstOfRadioButtons);
    var assessorId = $("#assessorDropdown").val();
    var queryId = $("#queryDropdown").val();
    $.ajax({
        type: 'POST',
        url: 'verticalSearchAssessment.aspx/storeAssessment',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify({ "jsonResponse": JSON.stringify(urlGradeMap), "queryId": queryId, "assessorId": assessorId }),
        success: function (response) {
            console.log(response.d);
        },
        error: function (e) {
            console.log("Errrr");
        }
    });
}

function getUrlGradingMap(lstOfRadioButtons) {
    for (var entry = 0; entry < lstOfRadioButtons.length; entry++) {
        urlGradeMap[lstOfRadioButtons[entry].name] = lstOfRadioButtons[entry].defaultValue;
    }
}