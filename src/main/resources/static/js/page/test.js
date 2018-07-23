
$(document).ready(function(){


    $("#button").click(function(){
        var answers = [];
        $('input:radio:checked').each(function(){
            answers.push({
                questionId:this.name,
                optionId:this.value,
            });
        });
        console.log(answers);

        $.ajax({
            type: "post",
            url: "/api/answers",
            contentType: "application/json",
            data: JSON.stringify({
                answers: answers
            }),
            dataType: "JSON",
            async: false,
            success: function (data) {
                console.log(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }

        });

    });

});
