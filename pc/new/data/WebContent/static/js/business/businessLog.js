 /**
 * Created by Administrator on 2017/8/9.(LFL)
 */
 $(function(){
     bindBusinessLog(1);
 });


 //绑定基础数据
 function bindBusinessLog(page){

     var businessLogTxt="";

     if($("#startTime").val() != "" && $("#endTime").val() != ""){
       if(new Date($("#startTime").val()).getTime() > new Date($("#endTime").val()).getTime()){
           layer.confirm('开始日期不能大于结束日期！', {
				title:"提示",
			  btn: ['确定'] //按钮
			});
           $("#startTime").val("");
           $("#endTime").val("");
           return;
       }
     }
     var index = layer.load(1, {shade: false});
     $.ajax({
         type:"GET",
         url:"/data/business/businessLogData",
         dataType:"json",
         data:{
             pageNumber:page,
             businessName:$("#businessName").val(),
             startTime:$("#startTime").val(),
             endTime:$("#endTime").val()
         },
         success:function (businessLog) {
             $("#BLogData").children().remove();

             if(businessLog.totalRow>0){
                 $(businessLog.list).each(function(index){

                     var timeSlot="";
                     if(Number(this.timeSlot)<12){
                         timeSlot="上午";
                     }else if(12<=Number(this.timeSlot)){
                         timeSlot="下午";
                     }

                     businessLogTxt+=["<tr>",
                         "<td>"+(index+1)+"</td>",
                         "<td>"+this.person_name+"</td>",
                         "<td>"+this.continue_times+"</td>",
                         "<td>"+this.address+"</td>",
                         "<td>"+this.add_time+"</td>",
                         "<td>"+timeSlot+"</td>",
                         "</tr>"].join("");
                 });
             }else{
                 businessLogTxt="<tr colspan='5'>暂无数据</tr>";
             }
             $("#firstPage").val(1);
             $("#nowPage").val(businessLog.pageNumber);
             $("#lastPage").val(businessLog.totalPage);
             $("#pageInfo").html("共"+businessLog.totalRow+"条数据，当前显示第 "+businessLog.pageNumber+" 页  ，到");
             $("#BLogData").html(businessLogTxt);

             layer.closeAll("loading");
         },error:function(){
             layer.closeAll("loading");
             layer.msg('查询失败', {
                 icon: 1,
                 time: 1000
             });
         }
     });
 }

 /**
  * 导出excel
  */
 function toExcel(){
     window.location.href = "/data/business/toBusinessLogExcel?businessName="+$("#businessName").val()+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val();
 }

 //点击首页
 function firstPage(){
     if(1 == $("#nowPage").val()){
         layer.msg('已经是第一页', {
             icon: 1,
             time: 1000
         });
         return;
     }
     bindBusinessLog(1);
 }
 //点击上一页
 function upPage(){
     var page = $("#nowPage").val()*1-1;
     if(page<1){
         layer.msg('已经是第一页', {
             icon: 1,
             time: 1000
         });
         return;
     }
     bindBusinessLog(page);
 }
 //点击下一页
 function nextPage(){
     var page = $("#nowPage").val()*1+1;
     if(page>$("#lastPage").val()){
         layer.msg('已经是最后一页', {
             icon: 1,
             time: 1000
         });
         return;
     }
     bindBusinessLog(page);
 }
 //点击末页
 function lastPage(){
     var page = $("#lastPage").val();
     if(page == $("#nowPage").val()){
         layer.msg('已经是最后一页', {
             icon: 1,
             time: 1000
         });
         return;
     }
     bindBusinessLog(page);
 }
 //跳转到指定页
 function toPage(){
     var page = $("#toPage").val();
     if(Number(page)>Number($("#lastPage").val())){
         layer.msg('没有那么多页', {
             icon: 1,
             time: 1000
         });
         return;
     }
     bindBusinessLog(page);
 }
