/**
 * Created by pudding on 2017-7-3.
 */
function finddisputeByorderNo(){
    var order_no=$('#order_no').val();
    $.ajax({
        url: '/sheet/shopApp/findsheetsByorderNo',
        type: 'GET',
        dataType: "json",
        data:{order_no:order_no},
        success:function(data){
            var txt="";
            var img="";
            for (var i=0;i<data.length;i++){
                if(data[i].sus==5){
                    img="<img class='item_left_img1' src='/sheet/static/shopApp/images/report_progress_icon5.png' />"
                }else{
                    img="<img class='item_left_img1' src='/sheet/static/shopApp/images/report_progress_icon4.png' />"
                }
                txt+="<li class='item' onclick='finddispute(this)'><div class='item_left'> <p class='p1'><span class='span1'>纠纷单号:</span><span class='span2'>"+data[i].sheet_no+"</span></p> <p class='p2'><span class='span1'>订单编号:</span><span class='span2'>"+data[i].order_no+"</span></p> <div class='div1'> <img class='img1' src='"+data[i].img_url+"' /> <p class='div1_p1'>"+data[i].goods_title+"</p> </div>"+img+"</div> </li> <input type='hidden' value='"+data[i].id+"'/>"
            }
            $('.list1').html(txt);
        }
    })
}

function finddispute(sheet_id){
    var id=$(sheet_id).next().val();
    window.location.href="/sheet/shopApp/finddispute?id="+id+"";
}


