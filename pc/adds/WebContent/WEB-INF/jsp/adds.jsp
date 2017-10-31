<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">

<title>广告管理系统</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link href="${base}/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
<link href="${base}/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
<link href="${base}/css/plugins/switchery/switchery.css" rel="stylesheet">
<link href="${base}/css/animate.css" rel="stylesheet">
<link href="${base}/js/plugins/layer/skin/layer.css" rel="stylesheet">
<link href="${base}/css/style.css?v=2.2.0" rel="stylesheet">
<!-- upload -->
<link href="${base}/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<style type="text/css">
  .form-control[disabled]{
    cursor: text;
  }
</style>
</head>
<body>
  <div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
      <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
          <li class="nav-header">
            <div class="dropdown profile-element">
              <span> <img alt="image" class="img-circle" src="img/profile_small.jpg" />
              </span> <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#"> 
              <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold" id="user-name">张三丰</strong>
                </span> <span class="text-muted text-xs block" id="lv-privilege">超级管理员 <b class="caret"></b></span>
              </span>
              </a>
              <ul class="dropdown-menu animated fadeInRight m-t-xs">
                <li><a href="javascript:;">修改头像</a></li>
                <li><a href="javascript:;">个人资料</a></li>
                <li class="divider"></li>
                <li><a href="javascript:;">安全退出</a></li>
              </ul>
            </div>
            <div class="logo-element">广告管理系统</div>
  
          </li>
          <li><a href="toMain"><i class="fa fa-th-large"></i> <span
              class="nav-label">主页</span></a></li>
  
          <li class="active">
                      <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">广告管理</span><span class="fa arrow"></span></a>
                      <ul class="nav nav-second-level">
                          <li class="active"><a href="toAdds">广告列表</a>
                          </li>
                          <li><a href="toAddAdds">新增广告</a>
                          </li>
                      </ul>
                  </li>
                  <li>
                      <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">广告位管理</span><span class="fa arrow"></span></a>
                      <ul class="nav nav-second-level">
                          <li><a href="toPlace">广告位列表</a>
                          </li>
                          <li><a href="toAddPlace">新增广告位</a>
                          </li>
                      </ul>
                  </li>
                  <li>
                      <a href="javascript:;"><i class="fa fa fa-globe"></i> <span class="nav-label">用户管理</span><span class="fa arrow"></span></a>
                      <ul class="nav nav-second-level">
                          <li><a href="toUser">用户列表</a>
                          </li>
                          <li><a href="toAddUser">添加用户</a>
                          </li>
                      </ul>
                  </li>
        </ul>
      </div>
    </nav>

    <div id="page-wrapper" class="gray-bg dashbard-1">
      <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation"
          style="margin-bottom: 0">
        <div class="navbar-header">
          <a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
            href="#"><i class="fa fa-bars"></i> </a>
        </div>
        <ul class="nav navbar-top-links navbar-right">
          <li><span class="m-r-sm text-muted welcome-message">
            <a href="toMain" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用广告管理系统</span>
          </li>
          <li><a href="javascript:;"> <i class="fa fa-sign-out"></i>退出
          </a></li>
        </ul>

        </nav>
      </div>
      <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
          <h2>广告位管理</h2>
          <ol class="breadcrumb">
            <li>
              <a href="toMain">主页</a>
            </li>
            <li>
              <strong>广告位列表</strong>
            </li>
          </ol>
        </div>
        <div class="col-lg-2"></div>
      </div>
      <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row adds-header ibox-content form-horizontal">
          
          <div class="col-sm-4">
            <div class="form-group">
              <label class="col-sm-4 control-label">广告状态：</label>
              <div class="col-sm-6">
                <select id="state" class="form-control">
                  <option value="">不限</option>
                  <option value="1">开</option>
                  <option value="0">关</option>
                </select>
              </div>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label class="col-sm-4 control-label" for="pl_id">广告位：</label>
              <div class="col-sm-6">
                <input type="text" maxlength="2" class="form-control" id="pl_id" placeholder="广告位为纯数字">
              </div>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label class="col-sm-4 control-label">广告渠道：</label>
              <div class="col-sm-6">
                <select id="channel" class="form-control">
                  <option value="">不限</option>
                  <option value="0">PC端</option>
                  <option value="1">APP端</option>
                </select>
              </div>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label class="col-sm-4 control-label">广告类型：</label>
              <div class="col-sm-6">
                <select class="form-control" required="required" id="type">
                  <option value="">不限</option>
                  <option value="0">商家</option>
                  <option value="1">商品</option>
                  <option value="2">活动</option>
                  <option value="3">APP餐饮美食</option>
                  <option value="4">APP休闲娱乐</option>
                  <option value="5">APP酒店住宿</option>
                  <option value="6">APP旅游观光</option>
                  <option value="7">APP日用百货</option>
                  <option value="8">APP外卖服务</option>
                  <option value="9">APP农村电商</option>
                  <option value="10">APP海外购</option>
                </select>
              </div>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label class="col-sm-4 control-label" for="shop">店铺名：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" id="shop" placeholder="请输入店铺名">
              </div>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label class="col-sm-4 control-label">广告标题：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" id="title" placeholder="请输入广告标题">
              </div>
            </div>
          </div>
          <div class="col-sm-8">
            <div class="form-group">
              <label class="col-sm-2 control-label">投放区域：</label>
              <div class="col-sm-10 row">
                <div class="col-sm-4">
                  <select class="form-control" required="required" id="province">
                    <option>====省===</option>
                  </select>
                </div>
                <div class="col-sm-4">
                  <select class="form-control" id="city">
                    <option>===市===</option>
                  </select>
                </div>
                <div class="col-sm-4">
                  <select class="form-control" id="country">
                    <option>===县===</option>
                  </select>
                </div>
                <input type="hidden" class="form-control" name="region" id="region">
                <input type="hidden" class="form-control" name="code" id="code">
              </div>
            </div>
          </div>
          <div class="col-sm-2 col-sm-offset-1">
            <button class="btn btn-primary" id="search" onclick="getAdds()">查询</button>
          </div>
        </div>
        <div class="row adds-list ibox-content">
            
        </div>
      </div>
      <div class="footer">
        <div class="pull-right">
          By：<a href="javascript:void" target="_blank">manjiwang.com</a>
        </div>
        <div>
          <strong>Copyright</strong> manjiwang.com &copy; 2016
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="showDetail">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <div class="row">
            <div class="col-sm-7 pull-left"><h4 class="modal-title">广告详情</h4></div>
            <div class="col-sm-2 pull-right">
              <button type="button" class="btn btn-info btn-xs" id="adds-update">修改</button>
            </div>
          </div>
        </div>
        <div class="modal-body">
          <form action="" method="POST" role="form" class="form-horizontal">
            <div class="form-group">
              <label class="col-sm-3 control-label">标题：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" id="title_detail" disabled>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">描述：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" id="brief_detail" disabled>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">投放地区：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" id="region_detail" disabled>
                <input type="hidden" name="code" id="code">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">终端：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" id="channel_detail" disabled>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">广告位：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" id="pl_id_detail" disabled>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">状态：</label>
              <div class="col-sm-7">
                <input type="checkbox" class="stateCheck" id="switchery" checked="true" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">类型：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" id="type_detail" disabled>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">店铺名：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" id="shop_detail" disabled>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">点击次数：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" id="count_detail" disabled>
              </div>
            </div>

            <div class="pic-box form-group">
              
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="showUpdate">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">修改广告</h4>
        </div>
        <div class="modal-body" style="height:650px;overflow-y: scroll;">
          <form enctype="multipart/form-data" action="updAdds" method="POST" role="form" id="updateForm" class="form-horizontal">
            <div class="form-group">
              <label class="col-sm-3 control-label">标题：</label>
              <div class="col-sm-7">
                <input type="text" name="title" class="form-control" id="title_update">
                <input type="hidden" name="ad_id" id="addsId">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">描述：</label>
              <div class="col-sm-7">
                <input type="text" name="brief" class="form-control" id="brief_update">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">投放地区：</label>
              <div class="col-sm-9 row" style="margin-left: -15px;">
                <div class="col-sm-4">
                  <select class="form-control" id="province1">
                    <option>====省===</option>
                  </select>
                </div>
                <div class="col-sm-4">
                  <select class="form-control" id="city1">
                    <option>===市===</option>
                  </select>
                </div>
                <div class="col-sm-4">
                  <select class="form-control" id="country1">
                    <option>===县===</option>
                  </select>
                </div>
                <input type="hidden" name="code" id="code-update">
                <input type="hidden" name="region" id="region-update">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">终端：</label>
              <div class="col-sm-7">
                <input type="text" name="channel" class="form-control" id="channel_update">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">广告位：</label>
              <div class="col-sm-7">
                <input type="text" name="pl_id" class="form-control" id="pl_id_update">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">状态：</label>
              <div class="col-sm-7">
                <select name="state" id="inputState" class="form-control">
                  <option value="1">开</option>
                  <option value="0">关</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">类型：</label>
              <div class="col-sm-7">
                <input type="text" name="type" class="form-control" id="type_update">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">店铺名：</label>
              <div class="col-sm-7">
                <input type="text" name="shop" class="form-control" id="shop_update">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">链接地址：</label>
              <div class="col-sm-7">
                <input type="text" name="val" class="form-control" id="val_update">
              </div>
            </div>
            <div class="pic-box form-group">
              
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label" for="state">上传图片：</label>
              <div class="col-sm-7">
                <input id="upload-pic" class="file" name="files" type="file" multiple >
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" onclick="$('#updateForm').submit()">提交</button>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="bigPic">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body text-center">
          <img src="#" style="max-width: 100%; height: auto;" alt="Image">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary delpic2">删除</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Mainly scripts -->
  <script src="js/jquery-2.1.1.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>

  <script src="js/plugins/layer/layer.min.js"></script>

  <!-- Custom and plugin javascript -->
  <script src="js/hplus.js?v=2.2.0"></script>
  <script src="js/plugins/pace/pace.min.js"></script>
  <!-- Switchery -->
  <script src="js/plugins/switchery/switchery.js"></script>
  <!-- upload -->
  <script src="${base}/js/fileinput.js" type="text/javascript"></script>
  
  <!-- Page-Level Scripts -->
  <script>
  var wrap = $(".wrapper-content");
  var listBox = $(".adds-list");
  var _index = 0;
  var num = 16;
    $(document).ready(function () {
      $("#upload-pic").fileinput({
        allowedFileExtensions: ['jpg', 'gif', 'png'],
        showUpload: false, 
        showPreview : false,
        fileType: "any"
      });

      $('.stateCheck').change(function(){
        var state = $(this).is(':checked') ? 1 : 0;
        var ad_id = $("#addsId").val();
        $.ajax({
          type : "GET",
          url : "updAddsState",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : {
            ad_id: ad_id,
            state: state
          },
          success : function(data) {
            if(data.errCode == 0){
              // layer.alert(data.result, 9);
            }
            else{
              // layer.alert(data.errInfo, 8);
            }
            _index = 0;
            listBox.empty()
            //目前这里是修改完成以后，直接加载所有的广告数据，这样不好是因为之前查询的时候选择了查询条件，现在修改了查询条件还存在，但是数据却显示的是所有的数据
            //showAdds(num);
            getAdds();
          }
        })
      })
      $('.adds-list').on("click",'.thumbnail',function(){
        var id = $(this).siblings('.adds-id').val();
        $.ajax({
          type : "GET",
          url : "queryAddsById",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : {
            ad_id: id
          },
          success : function(data) {
            console.log(data)
            if(data.errCode == 0){
              var item = data.result;
              var picBox = $(".pic-box");
              $("#addsId").val(item.ad_id);
              $("#title_detail").val(item.title);
              $("#brief_detail").val(item.brief);
              $("#pl_id_detail").val(item.pl_id);
              $("#region_detail").val(item.region);
              $("#shop_detail").val(item.shop);
              $("#count_detail").val(item.count);

              $("#region-update").val(item.region);
              $("#code-update").val(item.code);
              $("#val_update").val(item.val);
              switch (item.channel){
                case '0': $("#channel_detail").val('电脑端');
                break;
                case '1': $("#channel_detail").val('手机端');
                break;
              }
              switch (item.type){
                case '0': $("#type_detail").val('商家');
                break;
                case '1': $("#type_detail").val('商品');
                break;
                case '2': $("#type_detail").val('活动');
                break;
                case '3': $("#type_detail").val('APP餐饮美食');
                break;
                case '4': $("#type_detail").val('APP休闲娱乐');
                break;
                case '5': $("#type_detail").val('APP酒店住宿');
                break;
                case '6': $("#type_detail").val('APP旅游观光');
                break;
                case '7': $("#type_detail").val('APP日用百货');
                break;
                case '8': $("#type_detail").val('APP外卖服务');
                break;
                case '9': $("#type_detail").val('APP农村电商');
                break;
                case '10': $("#type_detail").val('APP海外购');
                break;
                
              }
              if(item.state == 0){
                $("#switchery").removeAttr("checked");
                $("#switchery").siblings().remove();
                var elem = document.querySelector('.stateCheck');
                var switchery = new Switchery(elem, {
                  color: '#1AB394'
                });
              }else{
                $("#switchery").prop("checked",'true');
                // $(".stateCheck").first().attr("checked");
                $("#switchery").siblings().remove();
                var elem = document.querySelector('.stateCheck');
                var switchery = new Switchery(elem, {
                  color: '#1AB394'
                });
              }
              var picUrl = eval('(' + item.pic_url + ')');
              picBox.empty();
              for(var i = 0; i < picUrl.length; i ++){
                  var pic = '<div class="col-sm-2">'+
                              '<a href="'+picUrl[i]+'" target="_blank" class="thumbnail">'+
                                '<img style="width:60px; height:60px;" src="'+picUrl[i]+'">'+
                              '</a>'+
                            '</div>';
                  picBox.append(pic);
              }
              $("#showDetail").modal('show');
            }
          }
        });
      });
   // 省市县三级联动
      $.ajax({
        type : "GET",
        url : "queryProvince",
        dataType : "json",
        contentType : "application/json; charset=utf-8",
        data : {},
        success : function(data) {
          if(data.errCode == "0"){
            var data = data.result;
            var province = $("#province");
            var province1 = $("#province1");
            province.empty();
            province1.empty();
            province.append('<option value="-1">===省===</option>');
            for(var i = 0; i < data.length; i++){
              var option = '<option value="'+data[i].code+'">'+data[i].region+'</option>';
              province.append(option);
              province1.append(option);
            }
          }
        }
      });
      $("#adds-update").click(function(){
        var id = $("#addsId").val();
        $.ajax({
          type : "GET",
          url : "queryAddsById",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : {
            ad_id: id
          },
          success : function(data) {
            if(data.errCode == 0){
              var item = data.result;
              var picBox = $(".pic-box");
//               var p = item.code.substring(0,2);
//               var c = item.code.substring(0,4);
//               var cc = item.code.substring(0,6);
              $("#addsId").val(item.ad_id);
              $("#title_update").val(item.title);
              $("#brief_update").val(item.brief);
              $("#pl_id_update").val(item.pl_id);
//               $("#region_update").val(item.region);//wu
//               $("#province1").val(p);
//               getCity1(p);//查询到了当前省份下面的城市和城市下面的区县
// //               alert(111);
//               $("#city1").val(c);
//               $("#country1").val(cc);
              /* $("#province1 option[text='"+p[0]+"']").attr("selected",true);
			  $("#city1 option[text='"+p[1]+"']").attr("selected",true);
              $("#country1 option[text='"+p[2]+"']").attr("selected",true); */
              
              $("#shop_update").val(item.shop);
              $("#channel_update").val(item.channel);
//               $("#state_update").val(item.state);//wu  inputState
              $("#inputState").val(item.state);
              $("#type_update").val(item.type);
              
              var picUrl = eval('(' + item.pic_url + ')');
              picBox.empty();
              for(var i = 0; i < picUrl.length; i ++){
                  var pic = '<div class="col-sm-2">'+
                              '<a data-toggle="modal" href="#bigPic" class="thumbnail thumbItem">'+
                                '<img style="width:60px; height:60px;" src="'+picUrl[i]+'">'+
                              '</a>'+
                            '</div>';
                  picBox.append(pic);
              }
            }
          }
        });
        $("#showDetail").modal('hide');
        $("#showUpdate").modal('show');
      });
	
      $("#search").click(function(){
        var pl_id = $("#pl_id").val();
        var channel = $("#channel").val();
        var state = $("#state").val();
        var code = $("#code").val();
        var title = $("#title").val();
        var type = $("#type").val();
        var shop = $("#shop").val();
        //因为页面加载的时候默认是===省===  但是选择其他的以后，然后再选择===省===，此时code="-1",不能查到任何信息，所以选择===省===的时候，如果code="-1",那么code=""
        if(code == "-1"){
        	code = "";
        }
        var query = {
          pl_id: pl_id,
          channel: channel,
          state: state,
          code: code,
          type: type,
          title: title,
          shop: shop
        }
        $.ajax({
          type : "GET",
          url : "queryAdds",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : query,
          success : function(data) {
            if(data.errCode == 0){
              var dataItem = data.result;
              listBox.empty();
              for(var i = 0; i < dataItem.length; i ++){
                var itemw = $('<div class="col-sm-6 col-md-3 adds-info m-b"></div>');
                var item1 = '<div class="thumbnail" style="position:relative;height:300px;">'+
                                              '<img class="adds-pic" src="'+dataItem[i].pic_url+'" alt="'+dataItem[i].title+'">'+
                                              '<div class="caption">'+
                                                '<h3>'+dataItem[i].title+'</h3>'+
                                                '<p>'+dataItem[i].brief+'</p>'+
                                              '</div><div style="position:absolute;top:0;left:0;right:0;bottom:0;background-color:rgba(0,0,0,.75); color:#fafafa;font-size:20px; line-height: 250px;text-align:center; border-radius:3px;">未开启</div>'+
                                            '</div><input type="hidden" class="adds-id" value="'+dataItem[i].ad_id+'">';
                var item2 = '<div class="thumbnail" style="position:relative;height:300px;">'+
                              '<img class="adds-pic" src="'+dataItem[i].pic_url+'" alt="'+dataItem[i].title+'">'+
                              '<div class="caption">'+
                                '<h3>'+dataItem[i].title+'</h3>'+
                                '<p>'+dataItem[i].brief+'</p>'+
                              '</div>'+
                            '</div><input type="hidden" class="adds-id" value="'+dataItem[i].ad_id+'">';
                var item = dataItem[i].state == 0 ? item1 : item2;
                itemw.append(item);
                listBox.append(itemw);
                itemw.fadeIn();
              }
            }
          }
        });
      });


      $(window).scroll(function () {
          var bW = wrap.offset().top + wrap.height() - $(document).scrollTop();
          if (bW < $(window).height()+100) {
              num = _index + 8; 
              showAdds(num);
          }
      });

      showAdds(num);
      function showAdds(num){
        $.ajax({
          type : "GET",
          url : "queryAdds",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : {},
          success : function(data) {
            if(data.errCode == 0){
              var dataItem = data.result;
              for(var i = 0; i < dataItem.length; i ++){
                var itemw = $('<div class="col-sm-6 col-md-3 adds-info m-b"></div>');
                var item1 = '<div class="thumbnail" style="position:relative; height:300px;">'+
                              '<img class="adds-pic" src="'+dataItem[i].pic_url+'" alt="'+dataItem[i].title+'">'+
                              '<div class="caption">'+
                                '<h3>'+dataItem[i].title+'</h3>'+
                                '<p>'+dataItem[i].brief+'</p>'+
                              '</div><div style="position:absolute;top:0;left:0;right:0;bottom:0;background-color:rgba(0,0,0,.75);border-radius:3px; color:#fafafa;font-size:20px; line-height: 250px;text-align:center;">未开启</div>'+
                            '</div><input type="hidden" class="adds-id" value="'+dataItem[i].ad_id+'">';
                var item2 = '<div class="thumbnail" style="position:relative; height:300px;">'+
                              '<img class="adds-pic" src="'+dataItem[i].pic_url+'" alt="'+dataItem[i].title+'">'+
                              '<div class="caption">'+
                                '<h3>'+dataItem[i].title+'</h3>'+
                                '<p style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;">'+dataItem[i].brief+'</p>'+
                              '</div>'+
                            '</div><input type="hidden" class="adds-id" value="'+dataItem[i].ad_id+'">';
                var item = dataItem[i].state == 0 ? item1 : item2;
                if(dataItem.length > _index){
                  itemw.append(item);
                  listBox.append(itemw);
                  itemw.fadeIn();
                  _index ++;
                  if(_index < num){
                    showAdds(num)
                  }
                }/*else{
                  listBox.append('<p class="text-center col-sm-12 text-muted">没有数据了...</p>');
                  $(window).unbind("scroll");
                  break;
                }*/
              }
            }
          }
        });
      }
      $(".pic-box").on("click",".thumbItem",function(){
        var url = $(this).find('img').attr('src');
        var _self = $(this).parent();
        $("#bigPic img").prop('src',url);
        $(".delpic2").click(function(){
          $.ajax({
            type : "GET",
            url : "delPic",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : {
              pic_url: url
            },
            success : function(data) {
              if(data.errCode == 0){
                _self.remove();
                $("#bigPic").modal('hide');
                layer.alert(data.result, 9);
              }
            }
          });
        })
        /**/
      });

      $("#province").change(function(){
        var code = $(this).val();
        $("#code").val(code);
        var region = $("#province option:selected").text();
        $("#region").val(region);
        getCity(code);
      });
      $("#city").change(function(){
        if($(this).val() == '-1'){
          var code = $('#province').val();
          $("#code").val(code);
          var region = $("#province option:selected").text();
          $("#region").val(region);
          console.log(code)
        }else{
          var code = $(this).val();
          $("#code").val(code);
          var region = $("#province option:selected").text() + ',' + $("#city option:selected").text();
          $("#region").val(region);
          console.log(code)
          getCountry(code);
        }
      });
      $("#country").change(function(){
        if($(this).val() == '-1'){
          if($(this).val() == '-1'){
            var code = $('#province').val();
            $("#code").val(code);
            var region = $("#province option:selected").text();
            $("#region").val(region);
            console.log(code)
          }else{
            var code = $(this).val();
            $("#code").val(code);
            var region = $("#province option:selected").text() + ',' + $("#city option:selected").text();
            $("#region").val(region);
            getCountry(code);
          }
        }else{
          var code = $(this).val();
          $("#code").val(code);
          var region = $("#province option:selected").text() + ',' + $("#city option:selected").text() + ',' + $("#country option:selected").text();
          $("#region").val(region);
        }
      });
      function getCity(code){
        $.ajax({
          type : "GET",
          url : "queryCity",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : {
            code: code
          },
          success : function(data) {
            if(data.errCode == "0"){
              var data = data.result;
              var city = $("#city");
              city.empty();
              city.append('<option value="-1">===市===</option>');
              $("#country").empty();
              $("#country").append('<option value="-1">===县===</option>');
              if(data.length == 1){
                var code = data[0].code;
                getCountry(code);
              }
              for(var i = 0; i < data.length; i++){
                var option = '<option value="'+data[i].code+'">'+data[i].region+'</option>';
                city.append(option);
              }
            }
          }
        });
      }
      function getCountry(code){
        $.ajax({
          type : "GET",
          url : "queryCountry",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : {
            code: code
          },
          success : function(data) {
            if(data.errCode == "0"){
              var data = data.result;
              var country = $("#country");
              country.empty();
              country.append('<option value="-1">===县===</option>');
              for(var i = 0; i < data.length; i++){
                var option = '<option value="'+data[i].code+'">'+data[i].region+'</option>';
                country.append(option);
              }
            }
          }
        });
      }
      $("#province1").change(function(){
        var code = $(this).val();
        getCity1(code);
        var region = $("#province1 option:selected").text();
        $("#region-update").val(region);
        $("#code-update").val(code);
      });
      $("#city1").change(function(){
        if($(this).val() == '-1'){
          var code = $('#province1').val();
          var region = $("#province1 option:selected").text();
          $("#region-update").val(region);
          $("#code-update").val(code);
        }else{
          var code = $(this).val();
          getCountry1(code);
          var region = $("#province1 option:selected").text() + ',' + $("#city1 option:selected").text();
          $("#region-update").val(region);
          $("#code-update").val(code);
        }
      });
      $("#country1").change(function(){
        if($(this).val() == '-1'){
          if($(this).val() == '-1'){
            var code = $('#province1').val();
            var region = $("#province1 option:selected").text();
            $("#region-update").val(region);
            $("#code-update").val(code);
          }else{
            var code = $(this).val();
            var region = $("#province1 option:selected").text() + ',' + $("#city1 option:selected").text();
            $("#region-update").val(region);
            $("#code-update").val(code);
          }
        }else{
          var code = $(this).val();
          $("#code-update").val(code);
          var region = $("#province1 option:selected").text() + ',' + $("#city1 option:selected").text() + ',' + $("#country1 option:selected").text();
          $("#region-update").val(region);
        }
      });

      function getCity1(code){
        $.ajax({
          type : "GET",
          url : "queryCity",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : {
            code: code
          },
          success : function(data) {
            if(data.errCode == "0"){
              var data = data.result;
              var city = $("#city1");
              city.empty();
              city.append('<option value="-1">===市===</option>');
              $("#country1").empty();
              $("#country1").append('<option value="-1">===县===</option>');
              if(data.length == 1){
                var code = data[0].code;
                getCountry1(code);
              }
              for(var i = 0; i < data.length; i++){
                var option = '<option value="'+data[i].code+'">'+data[i].region+'</option>';
                city.append(option);
              }
            }
          }
        });
      }
      function getCountry1(code){
        $.ajax({
          type : "GET",
          url : "queryCountry",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : {
            code: code
          },
          success : function(data) {
            if(data.errCode == "0"){
              var data = data.result;
              var country = $("#country1");
              country.empty();
              country.append('<option value="-1">===县===</option>');
              for(var i = 0; i < data.length; i++){
                var option = '<option value="'+data[i].code+'">'+data[i].region+'</option>';
                country.append(option);
              }

            }
          }
        });
      }
      var username="<%=session.getAttribute("username")%>";
	  var privilege="<%=session.getAttribute("privilege")%>";
	  var lPrivilege = ""
	  switch (privilege){
          case "0": 
        	  lPrivilege = "普通用户";
          break;
          
          case "1": 
        	  lPrivilege = "普通用户";
          break;
          
          case "2": 
        	  lPrivilege = "管理员";
          break;
          
          case "3": 
        	  lPrivilege = "管理员";
          break;
          
          case "4": 
        	  lPrivilege = "管理员";
          break;
          
          case "5": 
        	  lPrivilege = "管理员";
          break;
      }
	  $("#user-name").text(username);
	  $("#lv-privilege").text(lPrivilege);
    });
    function getAdds(){
            var pl_id = $("#pl_id").val();
            var channel = $("#channel").val();
            var state = $("#state").val();
            var code = $("#code").val();
            var title = $("#title").val();
            var type = $("#type").val();
            var shop = $("#shop").val();
            //因为页面加载的时候默认是===省===  但是选择其他的以后，然后再选择===省===，此时code="-1",不能查到任何信息，所以选择===省===的时候，如果code="-1",那么code=""
            if(code == "-1"){
            	code = "";
            }
            var query = {
              pl_id: pl_id,
              channel: channel,
              state: state,
              code: code,
              type: type,
              title: title,
              shop: shop
            }
            $.ajax({
              type : "GET",
              url : "queryAdds",
              dataType : "json",
              contentType : "application/json; charset=utf-8",
              data : query,
              success : function(data) {
                if(data.errCode == 0){
                  var dataItem = data.result;
                  listBox.empty();
                  for(var i = 0; i < dataItem.length; i ++){
                    var itemw = $('<div class="col-sm-6 col-md-3 adds-info m-b"></div>');
                    var item1 = '<div class="thumbnail" style="position:relative;height:300px;">'+
                                                  '<img class="adds-pic" src="'+dataItem[i].pic_url+'" alt="'+dataItem[i].title+'">'+
                                                  '<div class="caption">'+
                                                    '<h3>'+dataItem[i].title+'</h3>'+
                                                    '<p>'+dataItem[i].brief+'</p>'+
                                                  '</div><div style="position:absolute;top:0;left:0;right:0;bottom:0;background-color:rgba(0,0,0,.75); color:#fafafa;font-size:20px; line-height: 250px;text-align:center; border-radius:3px;">未开启</div>'+
                                                '</div><input type="hidden" class="adds-id" value="'+dataItem[i].ad_id+'">';
                    var item2 = '<div class="thumbnail" style="position:relative;height:300px;">'+
                                  '<img class="adds-pic" src="'+dataItem[i].pic_url+'" alt="'+dataItem[i].title+'">'+
                                  '<div class="caption">'+
                                    '<h3>'+dataItem[i].title+'</h3>'+
                                    '<p>'+dataItem[i].brief+'</p>'+
                                  '</div>'+
                                '</div><input type="hidden" class="adds-id" value="'+dataItem[i].ad_id+'">';
                    var item = dataItem[i].state == 0 ? item1 : item2;
                    itemw.append(item);
                    listBox.append(itemw);
                    itemw.fadeIn();
                  }
                }
              }
            });
    }
  </script>
</body>
</html>