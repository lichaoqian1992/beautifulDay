/**
 * Created by pudding on 2017-7-23.
 */

$(function(){

    $('#ddisablereport').click(function(){
        if ($('#descr').val()==''){
            $('#divshow').show();
            $(this).attr("disabled","disabled");
            setTimeout(function(){
                $("#divshow").hide();
                $('#ddisablereport').removeAttr("disabled");
            },1000);

        }else{
            $(this).attr("submited","false");
        }

    });
})

