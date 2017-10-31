<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>满集数据后台系统</title>

  <link href="media/css/jqpagination.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/style.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
  <link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
  <link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
  <link rel="shortcut icon" href="media/image/favicon.ico" />
</head>

<body class="page-header-fixed">
  <div class="header navbar navbar-inverse navbar-fixed-top">
     <div class="navbar-inner">
      <div class="container-fluid">

        <a class="brand" href="index.html">
        <img src="media/image/logo.png" alt="logo" />
        </a>
        <div class="navbar hor-menu hidden-phone hidden-tablet">
          <div class="navbar-inner">
            <jsp:include page="/WEB-INF/jsp/banner.html" flush="true"/>
          </div>
        </div>
        <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
        <img src="media/image/menu-toggler.png" alt="" />
        </a>
        <jsp:include page="/WEB-INF/jsp/userinfo.jsp" flush="true"/>          
      </div>
    </div>
  </div>
  
  <div class="page-container row-fluid" >
    <div class="page-sidebar nav-collapse collapse">
      <jsp:include page="/WEB-INF/jsp/menu.html" flush="true"/>
    </div>
    
    <div class="page-content">
      <div class="container-fluid">
        <div class="span12">
            <h3 class="page-title" style="margin:20px 0px 15px 0px">
                                           规格列表<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">规格管理</a><i class="icon-angle-right"></i></li>
              <li><a href="#">规格列表</a></li>
            </ul>
          </div>
      </div>
      
      <div class="row-fluid margin-bottom-20">

      </div>
      
      <div class="row-fluid">
        <div class="span12">
           <div class="portlet box grey">
              <div class="portlet-title" style="height:30px">
              </div>
              <div class="portlet-body">
                <div class="span2" style="display:none;">用户ID：<input type="text" id="userid" class="small m-wrap" ></div>
                <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                  
                  <!-- <form class=""> -->
                    <div class="form-group"> 
	                    <div class="pull-left ">
	                                                                          分类选择：
	                      <span id="categoryinfo"></span>
	                    </div>
                    </div>
                    <br><br>
                    <div class=" row-fluid">
                      <label class="span1 ">图片规格：</label>
                      <div class="span6 spec_pt1 pull-left">
                        <div >
                          <table class="table table-bordered table-hover">
                            <thead>
                              <tr>
                                <th>序号</th>
                                <th>名称</th>
                                <th>数值</th>
                              </tr>
                            </thead>
                            <tbody id="pictablebody">
                              
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <div class="form-group row-fluid">
                      <label class="span1">其他规格：</label>
                      <div class="span6 spec_pt1 pull-left">
                        <div >
                          <table class="table table-bordered table-hover">
                            <thead>
                              <tr>
                                <th>序号</th>
                                <th>名称</th>
                                <th>数值</th>
                              </tr>
                            </thead>
                            <tbody id="tablebody">
                              
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <div class="" style="margin-left:9%">
                      <div class="">
                        <a class="btn btn-w-m btn-success" type="submit" href="chgSpec?category_id=${category_id}">修改</a>
                        <a class="btn btn-w-m btn-default" type="submit"
                          href="selSpec">返 回</a>
                      </div>
                    </div>
                 <!--  </form> -->
              </div>
           </div>
        </div>
      </div>
    </div>
  </div>
 </div>

	
  <script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
  <script src="media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
  <script src="media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
  <script src="media/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
  <script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>  
  <script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>
  <script src="media/js/jquery.uniform.min.js" type="text/javascript" ></script>
  <script src="media/js/app.js"></script>   
              
	<script>
		$(document).ready(function() {
			onkey(1);
			setcateinfo();
			settableinfo();
		});
		

    function onkey(key){
            /* var ul=key+" ul";*/
            var li=key+" ul li"; 

            $('#side-menu li').css("display","none");
            $('.abc').css("display","block");
            $('.k'+key).css("display","block");
            $('.k'+li).css("display","block"); 
            /* $('.k'+key).addClass("active");
            $('.k'+ul).addClass("in"); */
        }
        
		function settableinfo(){
			
			var specs =${speclist };
			var picspec ="";
			var nonepicspecs="";
			var tablestr ="";
			
			
			
			
			$.each(specs,function(i, specitem) {
				
				if(!specitem.name.indexOf("*")){
					picspec =specitem;
					/* alert(specitem.name); */
				}else{
					
					var no =Number(i+1);
					var id =specitem.id;
					var name =specitem.name;
					var secondspeclist =specitem.specList;
					
					var specstr ="";
					$.each(secondspeclist,function(j, secondspecitem) {
						if(j!=0){
							specstr+=",";
						}
						specstr+=secondspecitem.title;
					});
					
					
					
					var rowstr ="<tr>"+
								"<td>"+no+"</td>"+
								"<td>"+name+"</td>"+
								"<td>"+specstr+"</td>"+
								"</tr>";
								
					tablestr +=rowstr;
				} 
			});
			$("#tablebody").html(tablestr);
			
			if(picspec!=""){
				
				var no =1;
				var id =picspec.id;
				var name =picspec.name;
				
				var secondpicspeclist =picspec.specList;
				var picspecstr ="";
				$.each(secondpicspeclist,function(k, secondpicspecitem) {
					if(k!=0){
						picspecstr+=",";
					}
					picspecstr+=secondpicspecitem.title;
				});
				
				var picrowstr ="<tr>"+
				"<td>"+no+"</td>"+
				"<td>"+name+"</td>"+
				"<td>"+picspecstr+"</td>"+
				"</tr>";
				
				$("#pictablebody").html(picrowstr);
				
			};
			
			
		};
		
		function setcateinfo(){
			
			var cateinfo =${catagorymap };
			var catestr =cateinfo.firstName+"—"+cateinfo.secondName+"—"+cateinfo.thirdName;

			$("#categoryinfo").html(catestr);
			
		}
		$('#spec_add').on('click', function() {
			$.layer({
				type : 1,
				shade : [ 0 ],//遮罩透明度                    
				area : [ 'auto', 'auto' ],
				offset : [ '100px', '' ],
				title : false,
				border : [ 0 ],
				page : {
					dom : '.spec_add'
				}
			});
		});
		// $('#spec_update').on('click', function(){
		//    $.layer({
		//         type: 1,                    
		//         shade: [0],
		//         area: ['auto', 'auto'],
		//         offset:['100px', ''],
		//         title: false,
		//         border: [0],                    
		//         page: {dom : '.spec_update'}
		//     });
		// });
		$(".spec_update_a").click(function() {
			$(".spec_update").css("display", "block");
		});
		$(".close_update").click(function() {
			$(".spec_update").css("display", "none");
		});
	</script>








</body>

</html>
