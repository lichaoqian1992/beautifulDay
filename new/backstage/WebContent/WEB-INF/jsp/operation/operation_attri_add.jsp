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
  <title>满集数据总后台</title>
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
            <jsp:include page="/WEB-INF/jsp/banner.jsp" flush="true"/>
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
      <jsp:include page="/WEB-INF/jsp/menu.jsp" flush="true"/>
    </div>

    <div class="page-content">

      <div class="container-fluid">
        <div class="row-fluid">
          <div class="span12">
            <h3 class="page-title" style="margin:20px 0px 15px 0px">
                    用户所用属性模板<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">模板配置</a><i class="icon-angle-right"></i></li>
              <li><a href="selAttri"> 用户所用属性模板</a></li>
            </ul>
          </div>
        </div>

        <div class="row-fluid margin-bottom-20">

        </div>

        <div class="row-fluid">

          <div class="span12">

            <div class="portlet box blue tabbable">

              <div class="portlet-title">
                <div class="caption">
                  <span class="hidden-480">新增属性信息</span>
                </div>
              </div>
              <div class="portlet-body form">
              
                <div class="tabbable portlet-tabs">
                  <ul class="nav nav-tabs">
                    <li><a href="#portlet_tab3" data-toggle="tab">&nbsp;</a></li>
                  </ul>
                  
                  <div class="tab-content">
                    <div class="tab-pane active" id="portlet_tab1">
                      <!-- BEGIN FORM-->
                      
                       <form action="#" class="form-horizontal">
                        <!-- <input type="hidden" id="hiddenid"value=""> -->
                         <div class="control-group">
                          <label class="control-label">对应频道：</label>
                          <div class="controls">
                            <select id="channel_id"></select>
                          </div>
                        </div>
                        <div class="control-group">
                          		<label class="control-label">对应分类：</label>
                          				<div class="span2" style="margin-left:1.3%">
										<input type="hidden" id="categoryid"> <select
											id="firstcate" class="small m-wrap">
										</select>
									</div>
									<div class="span2">
										<select id="secondcate" class="small m-wrap">
										</select>
									</div>
									<div class="span2">
										<select id="thirdcate" class="small m-wrap">
										</select>
									</div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">内容：</label>
                          <div class="controls">
                            <input id="content" type="text"   class="m-wrap medium" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否生效：</label>
                          <div class="controls">
<!--                             <input id="status" type="text"   class="m-wrap medium" /> <span class="help-inline">Some hint here</span>
 -->                            <select id="status" class="m-wrap small" >
                            	<option value="1">是</option>
                            	<option value="0">否</option>
                            </select>
                          </div>
                        </div>
                        <div class="control-group" style="display:none">
                          <label class="control-label">添加时间：</label>
                          <div class="controls">
                            <input id="addtime" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group" style="display:none">
                          <label class="control-label">更新时间：</label>
                          <div class="controls">
                            <input id="updatetime" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">更新说明：</label>
                          <div class="controls">
                            <input id="remark" type="text"   class="m-wrap medium" /><!--  <span class="help-inline">Some hint here</span> -->
                          </div>
                        </div>
                        
                        
                        <div class="form-actions">

                          <a type="submit" class="btn blue" id="subbutton">
                            <i class="icon-ok"></i> 保存
                          </a>

                          <a type="button" class="btn"  href="selAttri">返回</a>

                        </div>

                      </form>

                      <!-- END FORM-->

                    </div>

                    

                    

                  </div>

                </div>

              </div>

            </div>

            <!-- END SAMPLE FORM PORTLET-->

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

        App.init(); 
        menuact("03_03_04");
        firstcate();
        $.ajax({
			type : "GET",
			url : "queryChannel",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : {},
			success : function(data) {
				console.log(data);
				var item = data.result.dataList;
				for(var i=0;i<item.length;i++){
					$('#channel_id').append('<option value='+item[i].id+'>'+item[i].title+'</option>');
				}
		    	$('#channel_id option[value="'+7+'"]').prop('selected',true);  
			}
			})
			
			
			
    });
  
  
  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "addAttri",
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
        channel_id:$("#channel_id").val(),
        category_id:$("#categoryid").val(),
        content:$("#content").val(),
        status:$("#status").val(),
        add_time:$("#addtime").val(),
        update_time:$("#updatetime").val(),
        remark:$("#remark").val()
        },
    success : function(data) {
    	console.log(data);
      if(data.status==0){
         location.reload();
      }else{
        alert("添加失败,该类下已有属性");
      }
    },
    })
  });
  
  function firstcate() {
		$("#firstcate").prepend("<option value='0'>全部一级分类</option>");
		$("#secondcate").prepend("<option value='0'>全部二级分类</option>");
		$("#thirdcate").prepend("<option value='0'>全部三级分类</option>");
		$.ajax({
			type : "GET",
			url : "firstCategory",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : [],
			success : function(data) {
				var catelist = data.result;
				$.each(catelist, function(i, cateitem) {
					var id = cateitem.id;
					var title = cateitem.title;
					$("#firstcate")
							.append(
									"<option value='"+id+"'>" + title
											+ "</option>");
				});
			}
		});
		
		$("#firstcate").change(
				function() {
					$("#secondcate").empty();
					$("#secondcate").prepend(
							"<option value='0'>全部二级分类</option>");
					$("#thirdcate").empty();
					$("#thirdcate")
							.prepend("<option value='0'>全部三级分类</option>");
					var fcateid = $("#firstcate").val();
					$("#categoryid").val(fcateid);

					$.ajax({
						type : "GET",
						url : "secondCategory",
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						data : {
							id : $("#categoryid").val()
						},
						success : function(data) {
							var catelist = data.result;
							$.each(catelist, function(i, cateitem) {
								var id = cateitem.id;
								var title = cateitem.title;
								$("#secondcate").append(
										"<option value='"+id+"'>" + title
												+ "</option>");
							});
							/* $("#secondcate").prepend("<option value='0'>全部二级分类</option>");  */
						}
					});

				});

		$("#secondcate").change(
				function() {
					$("#thirdcate").empty();
					$("#thirdcate").prepend("<option value='0'>全部三级分类</option>");
					var scateid = $("#secondcate").val();
					$("#categoryid").val(scateid);

					$.ajax({
						type : "GET",
						url : "secondCategory",
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						data : {
							id : $("#categoryid").val()
						},
						success : function(data) {
							var catelist = data.result;
							$.each(catelist, function(i, cateitem) {
								var id = cateitem.id;
								var title = cateitem.title;
								$("#thirdcate").append(
										"<option value='"+id+"'>" + title
												+ "</option>");
							});
						}
					});

				});

		$("#thirdcate").change(function() {

			var tcateid = $("#thirdcate").val();
			$("#categoryid").val(tcateid);
		});

	};
	
	
  

  </script>

</body>

</html>