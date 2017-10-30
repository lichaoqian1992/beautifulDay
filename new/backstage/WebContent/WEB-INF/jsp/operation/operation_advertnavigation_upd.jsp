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
  <style>
  .auto_completion_content{
        overflow-y: auto;
        height: 261px;
    }
    
    .auto_completion_content li{
        padding: 10px 0;
        cursor: pointer;
        border-bottom: 1px solid #e0e0e0;
        overflow: hidden;
        zoom:1;
        
    }
    /*
    .auto_completion_content li:hover{
        background-color: #fafafa;
    }
    .auto_completion_content li span{
        display: inline-block;
        line-height: 60px;
        margin-left: 10px;
    } */
  
  </style>
  
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
            <h3 class="page-title">
                   广告导航信息<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="toMain">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">导航信息</a><i class="icon-angle-right"></i></li>
              <li><a href="selAdvertNavigation">广告导航信息</a></li>
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
                  <span class="hidden-480">修改广告导航信息</span>
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
                      
                      <form action="updAdvertNavigation" class="form-horizontal" method="post" enctype="multipart/form-data">
                        <input type="hidden" id="hiddenid"value="" name="id">
                        <div class="control-group">
                          <label class="control-label">站点编号：</label>
                          <div class="controls">
                            <input id="siteid" type="text" name="site_id" class="m-wrap small" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">标题：</label>
                          <div class="controls">
                            <input id="title" type="text" name="title" class="m-wrap small" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">图标：</label>
                          <div class="controls">
                          	<img id="icon" name="icon"  style="height:100px;">
	                          	<input id="upFile" type='file' name='file' style="display: none">
								<input type="hidden" id="imgUrl" name="imgUrl" />
								<a id="upAgain" onclick="uploadpicdiv()" style="margin-left:5%">重新上传</a>
                            
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">链接：</label>
                          <div class="controls">
                            <input id="url" type="text" name="url" class="m-wrap large" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">父类名称：</label>
                          <div class="controls">
                          	<input id="parentitle" type="text" autocomplete="off"   name="ptitle" class="m-wrap medium" /><span class="help-inline " id="fatherName" style="color:red;display:none">请选择正确的父类名称</span>
                          </div>
                          <div class="control-group">
                            <ul class="auto_completion_content" id="flieinfo"  style="display:none;width:214px;margin-left:12%;">
	                    	</ul>
	                      </div>	
                            <input id="parentid" type="hidden"  name="parent_id" class="m-wrap small" />
                        </div>
                        <div class="control-group">
                          <label class="control-label">类别：</label>
                          <div class="controls">
                          	<select class="m-wrap medium" id="type" name="type">
							</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">属性：</label>
                          <div class="controls">
                            <input id="attvalue" type="text" name="att_value" class="m-wrap small" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">显示内容类别：</label>
                          <div class="controls">
                          	<select class="m-wrap small" id="contenttype" name="content_type">
                          			<option value="-1"></option>
									<option value="0">商品</option>
									<option value="1">商家</option>
									<option value="4">人力</option>
									<option value="5">文章</option>
									<option value="6">外卖</option>
									<option value="7">便民</option>
									<option value="8">酒店</option>
									<option value="9">海外购</option>
									<option value="10">商家列表</option>
								</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">显示模板/内容：</label>
                          <div class="controls">
                            <input id="content" type="text" name="content" class="m-wrap small" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">显示内容数据：</label>
                          <div class="controls">
                            <input id="contentvalue" type="text" name="content_value" class="m-wrap small" />
                          </div>
                        </div>
                        <div class="control-group" style="display:none">
                          <label class="control-label">内容更新时间：</label>
                          <div class="controls">
                            <input id="updatetime" type="text" name="update_time" class="m-wrap medium" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">排序数字：</label>
                          <div class="controls">
                            <input id="sortid" type="text" name="sort_id" class="m-wrap small" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">Pc端排序数字：</label>
                          <div class="controls">
                            <input id="pcsortid" type="text" name="pc_sort_id" class="m-wrap small" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">显示地区：</label>
                          <div class="controls">
                           <!--  <input id="showarea" type="text" name="showarea" class="m-wrap small" /> -->
                            <select class="m-wrap small" id="showarea" name="show_area">
									<option value="0">主站</option>
									<option value="1">省市</option>
									<option value="2">区县</option>
									<option value="3">乡镇</option>
							</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">是否启用：</label>
                          <div class="controls">
                            	<select class="m-wrap small" id="status" name="status">
									<option value="0">停用</option>
									<option value="1">启用</option>
								</select>
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">类别深度：</label>
                          <div class="controls">
                            <input id="classlayer" type="text" name="class_layer" class="m-wrap small" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">热门程度：</label>
                          <div class="controls">
                            <input id="ishot" type="text" name="is_hot" class="m-wrap small" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">类别总深度：</label>
                          <div class="controls">
                            <input id="countclasslayer" type="text" name="count_class_layer" class="m-wrap small" />
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">显示类别：</label>
                          <div class="controls">
                            <input id="showtype" type="text" name="show_type" class="m-wrap small" />
                          </div>
                        </div>
                        
                        <div class="form-actions">

                          <button  class="btn blue">
							<i class="icon-ok"></i> 保存
						</button>

                          <a type="button" class="btn"  href="selAdvertNavigation">返回</a>

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
        menuact("04_01_03");
        infoset();
        $('#parentitle').keyup(function(){
            setTimeout(findTitle(),1000);
            if($('#parentitle').val() == "" || $('#parentitle').val() == null){
            	
            	$("#flieinfo").hide();
            }
        });
        
        var ang=${advertnavigationinfo };
        console.info(ang);
        console.info("11");
      
         $("#parentid").val(ang.parent_id);
    });
    
    /* 通过父类名称获取更多父类名称 */
  function findTitle(){
    	var parTitle = $("#parentitle").val();
    	if(parTitle == ""){
    		$("#flieinfo").empty();
    		return;
    	}
    	
        $.ajax({
            type : "GET",
            url : "getTitleId", 
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : {
             
              title:parTitle
                },
            async : false,
            success : function(data) {
            if(data.result.length == 0 || parTitle==""){
            	$("#flieinfo").hide();
            	$("#fatherName").show();
            }else{
            	$("#flieinfo").show();
            	$("#fatherName").hide();
            }
             console.log(data);
             $("#flieinfo").empty();
             var txt = "";
             for(var i=0;i<data.result.length;i++){
            	 txt += "<li onclick='clickimginfo(this)'>  <span>"+data.result[i].title+"("+data.result[i].type+")"+"</span> </li><input type='hidden' value='"+data.result[i].parent_id+"'/>"
             }
             $("#flieinfo").append(txt);
            }
            })
            
           
    }
  function clickimginfo(data){
      //图片id $(data).next().val()
      $('#parentitle').val($(data).find('span').html());
      console.info($(data).next().val());
      $("#parentid").val($(data).next().val());
      $("#flieinfo").empty();
      $("#flieinfo").hide();
  } 
    
    
  function infoset(){
    var parent = ${parent};
    console.log(parent);
    var parent_name;
    if(parent == null){
    	parent_name = "";
    }else{
    	parent_name = parent.title;
    }
    var advertnavigationinfo=${advertnavigationinfo };
    var id=advertnavigationinfo.id;
    var site_id=advertnavigationinfo.site_id;
    var title=advertnavigationinfo.title;
    var icon=advertnavigationinfo.icon;
    var url=advertnavigationinfo.url;
    var parent_id=advertnavigationinfo.parent_id;
    var type=advertnavigationinfo.type;
    var att_value=advertnavigationinfo.att_value;
    var content_type=advertnavigationinfo.content_type;
    var content=advertnavigationinfo.content;
    var content_value=advertnavigationinfo.content_value;
    var update_time=advertnavigationinfo.update_time;
    var sort_id=advertnavigationinfo.sort_id;
    var pc_sort_id=advertnavigationinfo.pc_sort_id;
    var show_area=advertnavigationinfo.show_area;
    var status=advertnavigationinfo.status;
    var class_layer=advertnavigationinfo.class_layer;
    var is_hot=advertnavigationinfo.is_hot;
    var count_class_layer=advertnavigationinfo.count_class_layer;
    var show_type=advertnavigationinfo.show_type;

    var at = ${advertType};
    console.log(at);
     for(var i in at){
    	if(at[i] != null){
    		
	    	$("#type").append("<option>"+at[i].type+"</option>");
    	}else{
    		continue;
    	}
    		
    		
    	
    }
     
    $("#hiddenid").val(id);
    $("#siteid").val(site_id);
    $("#title").val(title);
    $("#icon").attr('src',icon);
    $("#imgUrl").val(icon);
    $("#url").val(url);
    $("#parentitle").val(parent_name);
    /* $("#parentid").val(parent_id); */
    $("#type").val(type);
    $("#attvalue").val(att_value);
    $("#contenttype").val(content_type);
    $("#content").val(content);
    $("#contentvalue").val(content_value);
    $("#updatetime").val(update_time);
    $("#sortid").val(sort_id);
    $("#pcsortid").val(pc_sort_id);
    $("#showarea").val(show_area);
    $("#status").val(status);
    $("#classlayer").val(class_layer);
    $("#ishot").val(is_hot);
    $("#countclasslayer").val(count_class_layer);
    $("#showtype").val(show_type);

  };
  
  
  $("#subbutton").on("click",function(){

    $.ajax({
    type : "GET",
    url : "updAdvertNavigation", 
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    data : {
      id:$("#hiddenid").val(),
      site_id:$("#siteid").val(),
      title:$("#title").val(),
      icon:$("#icon").val(),
      url:$("#url").val(),
      parent_id:$("#parentid").val(),
      type:$("#type").val(),
      att_value:$("#attvalue").val(),
      content_type:$("#contenttype").val(),
      content:$("#content").val(),
      content_value:$("#contentvalue").val(),
      update_time:$("#updatetime").val(),
      sort_id:$("#sortid").val(),
      pc_sort_id:$("#pcsortid").val(),
      show_area:$("#showarea").val(),
      status:$("#status").val(),
      class_layer:$("#classlayer").val(),
      is_hot:$("#ishot").val(),
      count_class_layer:$("#countclasslayer").val(),
      show_type:$("#showtype").val()
        },
    success : function(data) {
      if(data.status==0){
        location.reload(); 
      }else{
        alert("修改失败");
      }
    }
        
    })
  });
  
  function uploadpicdiv(){
	  	$("#imgUrl").val("");
		$('#icon').hide();
		$('#upFile').show();
		$('#upAgain').hide();
	}

  </script>

</body>

</html>