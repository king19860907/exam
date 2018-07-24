
$(document).ready(function(){


    var l = $('#button').ladda();
    l.click(function(){
        l.ladda( 'start' );

        setTimeout(function(){
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
                    if(data.flag==1){
                        alert("提交成功!");
                        l.ladda( 'stop' );
                        location.href="/list/"+data.result;
                    }else{
                        alert(data.msg);
                        l.ladda( 'stop' );
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    l.ladda( 'stop' );
                }

            });
        },1000)

    });


   /* $("#button").click(function(){
        var answers = [];
        $('input:radio:checked').each(function(){
            answers.push({
                questionId:this.name,
                optionId:this.value,
            });
        });
        console.log(answers);



        /!*$.ajax({
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

        });*!/

    });*/

});
