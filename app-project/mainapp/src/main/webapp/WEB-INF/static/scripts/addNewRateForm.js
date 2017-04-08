$(document).ready(function(){

        var errorMessageSpan = $("#errorMessage");
        errorMessageSpan.text("");

        var parent_element = $(".options_list").first();
        var options = [];
        var options_from_server;
            $.ajax({
                url: "/mainapp/listOptions",
                success: function (data) {

                    options_from_server = data;

                    for (var i = 0; i < data.length; i++){

                        options[i] = "<div><input type=\"checkbox\" id=\"" + data[i].id + "\"/><label>" + data[i].name + "</label></div>";
                    };

                    parent_element.append($(options.join("")));
                },
                error: function (error) {
                    errorMessageSpan.text(error.responseText);
                }
            });

            var addRate = function(){

                errorMessageSpan.text("");

                if ($("#rateTitle").val().length === 0){

                    $("#rateTitle").addClass("has-error");
                    errorMessageSpan.text("Enter rate title.");
                }
                else if ($("#ratePrice").val() < 0){

                    errorMessageSpan.text("Enter valid price.");
                }
                else {

                    var rate = {};

                    rate.name = $("#rateTitle").val();
                    rate.price = $("#ratePrice").val();
                    rate.optionsIds = [];
                    var checked_options = parent_element.find("input:checked");

                    checked_options.each(function(index,element){

                        rate.optionsIds.push(element.id);
                    });

                    errorMessageSpan.text("");



                    $.ajax({

                        url:"/mainapp/addRate",
                        data: JSON.stringify(rate),
                        contentType: "application/json",
                        type: "POST",
                        dataType: 'json',
                        success: function (data) {
                            alert("SUCCESS");
                        },
                        error: function (error) {
                            errorMessageSpan.text(error.responseText);
                        }
                    });
                };
            };

            $("#addRateButton").click(addRate);
});