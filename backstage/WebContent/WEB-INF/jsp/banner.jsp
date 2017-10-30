<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<ul class="nav" id="bannerid">

<!-- <li>

		<span class="hor-menu-search-form-toggler">&nbsp;</span>

								<div class="search-form hidden-phone hidden-tablet" style="display: none;">

									<form class="form-search">

										<div class="input-append">

											<input type="text" placeholder="Search..." class="m-wrap">

											<button type="button" class="btn"></button>

										</div>

									</form>

								</div>

							</li> -->
</ul>
<script>
function onkey(key){
	key ="0"+key;
	if(key.length==2){
			
	}else{
		key=key.substring(1);
	}
	

	$('#menuid li').css("display","none");  
	 var li=key+" ul li"; 
	 $('.k'+key).css("display","block");
	 $('.k'+li).css("display","block"); 
	
	
	
}
      
function setactive(number){
	var len =number.length;
	var number3 =number;
	var number2 =number.substring(0,len-1);
	var number1 =number.substring(0,len-2);
	/* alert(number3+' '+number2+' '+number1); */
	$('.'+number3).addClass("active");
	$('.'+number2).addClass("active");
	$('.'+number1).addClass("active");
	
}


function  menuact(str){
	
	 setmenu();
	 
	var len =str.length;
	var tmenuid =str;
	var smenuid =str.substring(0,len-3);
	var fmenuid =str.substring(0,len-6);
	
	$('.'+tmenuid).addClass("active");
	$('.m'+smenuid).addClass("active open");
  	$('.n'+fmenuid).addClass("active"); 
  	 $('.page-sidebar-menu li').css("display","none"); 
 	 var li=fmenuid+" ul li"; 
	 $('.k'+fmenuid).css("display","block");
	 $('.k'+li).css("display","block"); 
	
	
}


function setmenu(){
	
	var menu =${sessionScope.menu};
	for(var i=0;i<menu.length;i++){
		var item =menu[i];
		var layer =item.layer;
		var title =item.title;
		var address =item.address;
		switch(layer){
		case "1":
			var menustr="";
			switch(address){
			case "01":
				menustr ="<li class='n01' onclick='onkey(01)' ><a data-toggle='dropdown' class='dropdown-toggle' >用户</a></li>";
				break;
			case "02":
				menustr ="<li class='n02' onclick='onkey(02)'><a data-toggle='dropdown' class='dropdown-toggle' >商家</a></li>";
				break;
			case "03":
				menustr ="<li class='n03' onclick='onkey(03)'><a data-toggle='dropdown' class='dropdown-toggle' >配置</a></li>";
				break;
			case "04":
				menustr ="<li class='n04' onclick='onkey(04)'><a data-toggle='dropdown' class='dropdown-toggle' >运维</a></li>";
				break;
			case "05":
				menustr ="<li class='n05' onclick='onkey(05)'><a data-toggle='dropdown' class='dropdown-toggle' >角色管理</a></li>";
				break;
			case "06":
				menustr ="<li class='n06' onclick='onkey(06)'><a data-toggle='dropdown' class='dropdown-toggle' >监控</a></li>";
				break;
			case "07":
				menustr ="<li class='n07' onclick='onkey(07)'><a data-toggle='dropdown' class='dropdown-toggle' >代理商</a></li>";
				break;
			case "08":
				menustr ="<li class='n08' onclick='onkey(08)'><a data-toggle='dropdown' class='dropdown-toggle' >账户</a></li>";
				break;
			case "09":
				menustr ="<li class='n09' onclick='onkey(09)'><a data-toggle='dropdown' class='dropdown-toggle' >订单</a></li>";
				break;
			case "10":
				menustr ="<li class='n10' onclick='onkey(10)'><a data-toggle='dropdown' class='dropdown-toggle' >消息日志</a></li>";
				break;
			case "11":
				menustr ="<li class='n11' onclick='onkey(11)'><a data-toggle='dropdown' class='dropdown-toggle' >系统管理</a></li>";
				break;
			case "12":
				menustr ="<li class='n12' onclick='onkey(12)'><a data-toggle='dropdown' class='dropdown-toggle' >新建查询</a></li>";
				break;
			default :
				
				break;
			}
			
			
			
			
			$("#bannerid").append(menustr)
			break;
		case "2":
			var menustr ="";
			var menutree =address.substring(0,address.length-3);
			
			
			menustr ="<li class='k" 
						+menutree+ " m"+address+
						"'> <a href='javascript:;'> </i> <span class='title'>"
						+title+
						"</span> <span class=''></span> <span class='arrow open'></span></a><ul class='sub-menu "
						+address+
						"'></ul></li>";
			
			$("#menuid").append(menustr);
			
			break;
			
		case "3" :
			
			var url =item.url;
			var menustr ="";
			var menutree=address.substring(0,address.length-3);
			
			menustr+=" <li class='"
						+address+
						"'><a href='"
						+url+
						"'>"
						+title+
						"</a></li>";
			
			$("."+menutree).append(menustr);
			break;
		default :
			
			break;
		
		}
		
	}
	
}


</script>