$(function(){
    DateTraversal(1);
    
    $("#Date").on("click","a",function(){
    	$.ajax({
            url: '/sheet/manage/flowState',
            type: 'GET',
            dataType: "json",
            data:{
               "sheetId":$(this).parent().parent().find("input:eq(0)").val(),
               "status":$(this).parent().parent().find("input:eq(1)").val()
            }
    	});
    })
})

function DateTraversal(page) {
	$.ajax({
        url: '/sheet/manage/list',
        type: 'GET',
        dataType: "json",
        data:{
            "page":page,
            "sourceCode":$("#sourceCode").val(),
            "typeCode":$("#typeCode").val(),
            "startTime":$("#startTime").val(),
            "endTime":$("#endTime").val(),
            "search":$("#search").val(),
            "status":$(".status").val(),
            "ajax":"ajax"
        },
        success: function (data) {
            var txt = "";
            $("#Date").children().remove();
            if(data.totalRow>0) {
                $(data.list).each(function () {
                    var status = "";
                    var nav=$(".nav").val();
                    switch (this.status) {
                        case 1:
                            status = "待受理";
                            break;
                        case 2:
                            status = "不受理";
                            break;
                        case 3:
                            status = "处理中";
                            break;
                        case 4:
                            status = "已处理";
                            break;
                        case 5:
                            status = "已完成";
                            break;
                        case 6:
                            status = "已解决";
                            break;
                        default :
                            status = "暂无数据";
                    }
                    // class='kf_table_even'
                    txt += "<tr><input type='hidden' value='"+this.id+"'/><input type='hidden' value='"+this.status+"'/><td>" + this.sumId + "</td>" +
                        "<td>" + this.sheet_no + "</td>" +
                        "<td>" + this.start_time + "</td>" +
                        "<td>" + this.typeCode + "</td>" +
                        "<td>" + this.sponsor + "</td>" +
                        "<td>" + this.sourceCode + "</td>" +
                        "<td>" + status + "</td>" +
                        "<td>" + this.receiver + "</td>" +
                        "<td>" + this.exe_dept + "</td>" +
                        "<td><a class='kf_table_a' href='/sheet/manage/acceptDetail?id="+this.id+"&nav="+nav+"'>详情</a></td></tr>";
                });
            }else{
                txt="<tr class='kf_table_even'><td colspan='10' style='text-align: center;'>暂无记录</td></tr>";
            }
            $("#Date").append(txt);
            pageDate(data);
        }
	});
}


function toExcel(){
	window.location.href = "/sheet/manage/toExcel?sourceCode="+$("#sourceCode").val()+"&typeCode="+$("#typeCode").val()+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&search="+$("#search").val()+"&status="+$(".status").val();
}
