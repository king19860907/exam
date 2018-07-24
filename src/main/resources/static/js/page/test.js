
$(document).ready(function(){


    var l = $('#button').ladda();
    l.click(function(){
        l.ladda( 'start' );

        $(".panel-danger").each(function(){
            $(this).removeClass("panel-danger").addClass("panel-default");
        });

        setTimeout(function(){
            var answers = [];
            var unAnswers = [];
            /*$('input:radio:checked').each(function(){
                answers.push({
                    questionId:this.name,
                    optionId:this.value,
                });
            });*/

            $("[name^='question-']").each(function(){
                var name = $(this).attr("name");
                var questionId = name.substring(name.indexOf("-")+1,name.length);
                var checkedRadio = $("input[name='"+questionId+"']:checked");
                if(typeof(checkedRadio.val()) != 'undefined'){
                    answers.push({
                        questionId:checkedRadio.attr("name"),
                        optionId:checkedRadio.val(),
                    });
                }else{
                    unAnswers.push({
                        questionId:questionId,
                    })
                    checkedRadio.focus();
                }
            });

            //有没做的题目
            if(unAnswers.length>0){
                unAnswers.forEach(function(item){
                    $("#question-"+item.questionId).removeClass("panel-default").addClass("panel-danger");
                });
                l.ladda( 'stop' );
                return;
            }

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

});
