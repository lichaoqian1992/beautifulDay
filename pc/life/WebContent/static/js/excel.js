var langL = "";
var typeL = "";
var columnL = "";
function searchInfo(){
	 langL = "";
	 typeL = "";
	 columnL = "";
	var lang = document.getElementsByName("lang");
	var type = document.getElementsByName("type");
	var column = document.getElementsByName("column");
	for(var i=0;i<lang.length;i++){
		if(lang[i].checked){
			langL += lang[i].value+";";
		}
	}
	for(var i=0;i<type.length;i++){
		if(type[i].checked){
			typeL += type[i].value;
		}
	}
	for(var i=0;i<column.length;i++){
		if(column[i].checked){
			columnL += column[i].value+";";
		}
	}
	if(langL.length == 0){
		alert("请至少选择一个工作组");
		return;
	}
	if($("#startTime").val() == "" || $("#endTime").val() == ""){
		alert("时间必选");
		return;
	}
	if(new Date($("#startTime").val()).getTime() > new Date($("#endTime").val()).getTime()){
		alert("开始时间不能大于结束时间");
		return;
	}
	if(typeL.length == 0){
		alert("请选择类型");
		return;
	}
	if(columnL.length == 0){
		alert("请至少选择一个要显示的字段");
		return;
	}
	var x = 0;
	for(var i=0;i<column.length;i++){
		if(column[i].checked){
			if(column[i].value == "b.groupname" || column[i].value == "xuhao"){
				x++;
			}
		}
	}
	if(x != 2){
		alert("必须选择工作组和序号");
		return;
	}
	$.ajax({
		type:"get",
		url:"/life/excel/query",
		dataType:"json",
		data:{
			langL:langL,
			startTime:$("#startTime").val(),
			endTime:$("#endTime").val(),
			typeL:typeL,
			columnL:columnL
		},success:function(data){
			var txt = "";
			var title = "";
			console.log(data);
			if(data.length>0){
				var a = columnL.split(";");
				for(var m=0;m<a.length;m++){
					if(a[m] == "b.groupname"){
						title += "<th>工作组</th>";
					}else if(a[m] == "xuhao"){
						title += "<th>序号</th>";
					}else if(a[m] == "s.title"){
						title += "<th>需求</th>";
					}else if(a[m] == "t.name"){
						title += "<th>功能</th>";
					}else if(a[m] == "t.deadline"){
						title += "<th>结束时间</th>";
					}else if(a[m] == "b.realname"){
						title += "<th>负责人</th>";
					}else if(a[m] == "a.content"){
						title += "<th>最后反馈</th>";
					}else if(a[m] == "a.date"){
						title += "<th>反馈时间</th>";
					}else if(a[m] == "a.realname"){
						title += "<th>反馈人</th>";
					}else if(a[m] == "td.content"){
						title += "<th>今日反馈</th>";
					}
				}
				for(var i=0;i<data.length;i++){
					
					var x = "<tr class='gradeX'>";
					var b = "";
					for(var n=0;n<a.length-1;n++){
						if(a[n] == "b.groupname"){
							b += "<td>"
								+data[i].XuQiu1
								"</td>";
						}else if(a[n] == "xuhao"){
							b += "<td>"
								+data[i].xuqiu
								"</td>";
						}
						if(n == 2){
							b += "<td style='width:350px;' class='xuqiu'>"
								+data[i].XuQiu3
								"</td>";
						}else if(n == 3){
							b += "<td style='width:500px;' class='gongneng'>"
								+data[i].XuQiu4
								"</td>";
						}else if(n == 4){
							b += "<td>"
								+data[i].XuQiu5
								"</td>";
						}else if(n == 5){
							b += "<td>"
								+data[i].XuQiu6
								"</td>";
						}else if(n == 6){
							b += "<td style='width:300px;'>"
								+data[i].XuQiu7
								"</td>";
						}else if(n == 7){
							b += "<td>"
								+data[i].XuQiu8
								"</td>";
						}else if(n == 8){
							b += "<td>"
								+data[i].XuQiu9
								"</td>";
						}else if(n == 9){
							b += "<td>"
								+data[i].XuQiu10
								"</td>";
						}
						
					}
					var c = "</tr>";
					txt += x+b+c;
				}
				
				$("#title").html(title);
				$("#content").html(txt);
				
				var sum=(a.length-1)*300;
				$(".table").css("width",sum+"px");
			}
		}
	})
}
function toExcel(){
	var t = document.getElementById("content");
	if($(t).find("tr").length == 0){
		alert("没有可导出的数据");
		return;
	}
	window.location.href = "/life/excel/toExcel?langL="+langL+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&typeL="+typeL+"&columnL="+columnL;
	
}