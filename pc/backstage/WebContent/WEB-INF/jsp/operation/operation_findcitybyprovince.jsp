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
        <div class="row-fluid">
          <div class="span12">
            <h3 class="page-title">
                    行政地区数据<!-- <small>正常账户列表</small> -->
            </h3>
            <ul class="breadcrumb">
              <li><i class="icon-home"></i> <a href="*">主页</a><i class="icon-angle-right"></i></li>
              <li><a href="#">地理数据</a><i class="icon-angle-right"></i></li>
              <li><a href="#">行政地区数据</a></li>
            </ul>
          </div>
        </div>

        <div class="row-fluid margin-bottom-20">

        </div>

        <div class="row-fluid">

          <div class="span12">

            <div class="portlet box grey">
              <div class="portlet-title">
                <div class="caption"> </div>
                <div class="actions">
                  <a  class="btn blue" id="add"><i class="icon-pencil"></i>新增</a>
                </div>
              </div>
              
              <div class="portlet-body">
               <div class="span2" style="display:none;">用户ID：<input type="text" id="userid" class="small m-wrap" ></div>
              
              
              <div class="row-fluid ">
                <%-- <input type="hidden" id="state" value="${accountstate } "> --%>
<!--                 <div class="span2">用户ID：<input type="text" id="useridstr"class="small m-wrap" ></div>
 -->                
                <div class="span2">
	                  <select id="province" class="small m-wrap" >
	                            <option id="selectProvince">请选择省份</option>
	                  </select>
                </div>
                <div class="span2">
	                  <select id="city" class="small m-wrap" >
	                            <option id="selectCity">请选择城市</option>
	                  </select>
                </div>
                <div class="span2">
	                  <select id="county" class="small m-wrap" >
	                            <option id="selectCounty">请选择区/县</option>
	                  </select>
                </div>
                <div class="span2">
	                  <select id="town" class="small m-wrap" >
	                            <option id="selectTown">请选择城镇</option>
	                  </select>
                </div>
                <div class="span2">
	                  <select id="village" class="small m-wrap" >
	                            <option id="selectVillage">请选择乡村</option>
	                  </select>
                </div>
                
               
                
              </div>
                 
              <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                
                  <table class="table table-striped table-bordered table-hover dataTable" id="sample_2" aria-describedby="sample_2_info">

                    <thead>
                      <tr role="row" id="showTable">
                      </tr>
                    </thead>

                    <tbody role="alert" aria-live="polite" aria-relevant="all" id="tablebody">
                    </tbody>
                    </table>
                  <div class="row-fluid">
                    <div class="span6">
                      <div class="dataTables_info" id="dividersummery"></div>
                    </div>
                    <div class="span6">
                      <div class="dataTables_paginate paging_bootstrap pagination" id="devider">
                        
                      </div>
                    </div>
                  </div>

                </div>   
                  

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

  
  var provinceVal = null;
  var provinceId = null;
  var url = null;
  var data = null;
  var callback = null;
  var index = null;
  $(document).ready(function() {    

        App.init(); 
        onkey(1);
        queryinfo(1);
        setactive("141");
    });
  
  
   function getData(url,data,callback){
	    $.ajax({
	      type:"GET",
	      url : url,
	      dataType : "json",
	      contentType : "application/json; charset=utf-8",
	      data : data,
	      success : callback
	  })
	  } 
  
  function queryinfo(index){
		  
		  findProvince();
		  getTable(index);
  }; 
  
  /* 获取所有省份 */
  function findProvince(){
	  var url = "findProvince";
	  data = {};
	  callback = function(provincedata){
		  for(var i=0;i<provincedata.result.length;i++){
			  $("#province").append('<option id="'+provincedata.result[i].province_id+'" >'+provincedata.result[i].name+'</option>');
		  }
	  }
	  getData(url,data,callback);
	  province();
  }
  
  /* 获取数据 */
  function getTable(index){
	  url = "queryApoProvince";
	  data = "index="+index;
	  callback = function(freshdata){
		  console.info(freshdata);
		  showProvinceTable(index,freshdata);
	  }
	  getData(url,data,callback);
  }
  
  /* 展示省份数据 */
  function showProvinceTable(index,freshdata) {
	  if(index==0||index==""){
          index =1;
        };
		  $("#add").attr('href','insApoProvince');
      $("#showTable").empty();
      $("#showTable").append('<th>序号</th>').append('<th>标识ID</th>')
      .append('<th>省份识别</th>').append('<th>省份名称</th>').append('<th>省份全名</th>').append('<th>操作</th>');
      if(freshdata.status ==0){
            var page =freshdata.result;
            var pageindex =page.index;
            var totalcount =page.totalCount;
            var pagecount =page.pageCount;
            var pagenum =page.pageNum;
            var data =page.dataList;
            
            var tablehtml="";
            $.each(data,function(i,item){
              var no =Number(i+1);
              
              var province_id=item.province_id;
              var code=item.code;
              var name=item.name;
              var fullname=item.fullname;
              var rowhtml ="<tr>"+
               "<td>"+no+"</td>"+
               "<td>"+province_id+"</td>"+
               "<td>"+code+"</td>"+
               "<td>"+name+"</td>"+
               "<td>"+fullname+"</td>"+
               "<td>"+"<a href='readApoProvince?province_id="+province_id+"'>修改</a><a href='delApoProvince?province_id="+province_id+"'>删除</a>"+"</td>"+
               "</tr>";
              
              tablehtml+=rowhtml;
                   
            })
               $("#tablebody").html(tablehtml);
            
            deviderset(totalcount,pagecount,pageindex);
      };       
            
  }
  
  /* 展示城市数据 */
  function showCityTable(index,citydata) {
    if(index==0||index==""){
          index =1;
        };
      $("#add").attr('href','insApoCity');
      $("#showTable").empty();
      $("#showTable").append('<th>序号</th>').append('<th>标识ID</th>').append('<th>身份识别</th>')
      .append('<th>市名称</th>').append('<th>所属省份ID</th>').append('<th>操作</th>');
      if(citydata.status ==0){
            var page =citydata.result;
            var pageindex =page.index;
            var totalcount =page.totalCount;
            var pagecount =page.pageCount;
            var pagenum =page.pageNum;
            var data =page.dataList;
            
            var tablehtml="";
            $.each(data,function(i,item){
              var no =Number(i+1);
              
              var city_id=item.city_id;
              var code=item.code;
              var name=item.name;
              var province_id=item.province_id;
              var rowhtml ="<tr>"+
               "<td>"+no+"</td>"+
               "<td>"+city_id+"</td>"+
               "<td>"+code+"</td>"+
               "<td>"+name+"</td>"+
               "<td>"+province_id+"</td>"+
               "<td>"+"<a href='readApoCity?city_id="+city_id+"'>修改</a><a href='delApoCity?city_id="+city_id+"'>删除</a>"+"</td>"+

               "</tr>";
              
              tablehtml+=rowhtml;
                   
            })
               $("#tablebody").html(tablehtml);
            
            deviderset(totalcount,pagecount,pageindex);
      };       
  }
  
  /* 展示区县数据 */
  function showCountyTable(index,countydata) {
    if(index==0||index==""){
          index =1;
        };
      $("#add").attr('href','insApoCounty');
      $("#showTable").empty();
      $("#showTable").append('<th>序号</th>').append('<th>标识ID</th>')
      .append('<th>身份识别</th>').append('<th>县名称</th>').append('<th>所属市级ID</th>').append('<th>操作</th>');
      if(countydata.status ==0){
            var page =countydata.result;
            var pageindex =page.index;
            var totalcount =page.totalCount;
            var pagecount =page.pageCount;
            var pagenum =page.pageNum;
            var data =page.dataList;
            
            var tablehtml="";
            $.each(data,function(i,item){
              var no =Number(i+1);
              
              var county_id=item.county_id;
              var code=item.code;
              var name=item.name;
              var city_id=item.city_id;
              var rowhtml ="<tr>"+
               "<td>"+no+"</td>"+
               "<td>"+county_id+"</td>"+
               "<td>"+code+"</td>"+
               "<td>"+name+"</td>"+
               "<td>"+city_id+"</td>"+
               "<td>"+"<a href='readApoCounty?county_id="+county_id+"'>修改</a><a href='delApoCounty?county_id="+county_id+"'>删除</a>"+"</td>"+
               "</tr>";
              
              tablehtml+=rowhtml;
                   
            })
               $("#tablebody").html(tablehtml);
            
            deviderset(totalcount,pagecount,pageindex);
      };       
  }
  
  
  /* 展示城镇数据 */
  function showTownTable(index,towndata) {
    if(index==0||index==""){
          index =1;
        };
      $("#add").attr('href','insApoTown');
      $("#showTable").empty();
      $("#showTable").append('<th>序号</th>').append('<th>标识ID</th>')
      .append('<th>身份识别</th>').append('<th>乡镇名称</th>').append('<th>所属区县ID</th>').append('<th>操作</th>');
      if(towndata.status ==0){
            var page =towndata.result;
            var pageindex =page.index;
            var totalcount =page.totalCount;
            var pagecount =page.pageCount;
            var pagenum =page.pageNum;
            var data =page.dataList;
            
            var tablehtml="";
            $.each(data,function(i,item){
              var no =Number(i+1);
              
              var town_id=item.town_id;
              var code=item.code;
              var name=item.name;
              var county_id=item.county_id;
              var rowhtml ="<tr>"+
               "<td>"+no+"</td>"+
               "<td>"+town_id+"</td>"+
               "<td>"+code+"</td>"+
               "<td>"+name+"</td>"+
               "<td>"+county_id+"</td>"+
               "<td>"+"<a href='readApoTown?town_id="+town_id+"'>修改</a><a href='delApoTown?town_id="+town_id+"'>删除</a>"+"</td>"+
               "</tr>";
              
              tablehtml+=rowhtml;
                   
            })
               $("#tablebody").html(tablehtml);
            
            deviderset(totalcount,pagecount,pageindex);
      };       
  }
  
  
  
  /* 展示乡村数据 */
  function showVillageTable(index,villagedata) {
    if(index==0||index==""){
          index =1;
        };
      $("#add").attr('href','insApoVillage');
      $("#showTable").empty();
      $("#showTable").append('<th>序号</th>').append('<th>身份识别</th>').append('<th>村级名称</th>').append('<th>所属城镇ID</th>').append('<th>操作</th>');
      if(villagedata.status ==0){
            var page =villagedata.result;
            var pageindex =page.index;
            var totalcount =page.totalCount;
            var pagecount =page.pageCount;
            var pagenum =page.pageNum;
            var data =page.dataList;
            
            var tablehtml="";
            $.each(data,function(i,item){
              var no =Number(i+1);
              
              var code=item.code;
              var name=item.name;
              var town_id=item.town_id;
              var rowhtml ="<tr>"+
               "<td>"+no+"</td>"+
               "<td>"+code+"</td>"+
               "<td>"+name+"</td>"+
               "<td>"+town_id+"</td>"+
               "<td>"+"<a href='readApoVillage?code="+code+"'>修改</a><a href='delApoVillage?code="+code+"'>删除</a>"+"</td>"+
               "</tr>";
              
              tablehtml+=rowhtml;
                   
            })
               $("#tablebody").html(tablehtml);
            
            deviderset(totalcount,pagecount,pageindex);
      };       
  }
  
  
  function province(){
	  $("#province").change(function(){
          provinceVal = $("#province").val();
          $("#province").find('option').each(function(){
             if($(this).val()==provinceVal && $(this).val()!="请选择省份"){
                provinceId = $(this).attr('id');
                url = "findCityByProvince";
                data = {province_id:provinceId};
                callback = function(citydata){
                console.log(citydata);
                $("#city").empty();
                $("#city").append('<option>请选择城市</option>');
                $("#county").empty();
                $("#county").append('<option>请选择区/县</option>');
                $("#town").empty();
                $("#town").append('<option>请选择城镇</option>');
                $("#village").empty();
                $("#village").append('<option>请选择乡村</option>');
                if(citydata.result.length!=0){
                  for(var i=0;i<citydata.result.length;i++){
                    $("#city").append('<option id="'+citydata.result[i].city_id+'" >'+citydata.result[i].name+'</option>');
                  }
                }
              };
              getData(url,data,callback);
              getCityData(provinceId);
            }else if($("#province").val()=="请选择省份"){
                      $("#city").empty();
                      $("#city").append('<option id="selectCity">请选择城市</option>');
              }
          });
        });
	   city();
  }
  
  function city(){
	     $("#city").change(function(){
             console.log("aaa");
              var cityVal = null;
              var cityId = null;
              cityVal = $("#city").val();
              $("#city").find('option').each(function(){
                  if($(this).val()==cityVal && $(this).val()!="请选择城市"){
                      cityId = $(this).attr('id');
                      url = "findCountyByCity";
                      data = {city_id:cityId};
                      console.log("bbb");
                      callback = function(countydata){
                      console.log(countydata);
                      $("#county").empty();
                      $("#county").append('<option>请选择区/县</option>');
                      $("#town").empty();
                      $("#town").append('<option>请选择城镇</option>');
                      $("#village").empty();
                      $("#village").append('<option>请选择乡村</option>');
                      if(countydata.result.length!=0){
                        for(var i=0;i<countydata.result.length;i++){
                          $("#county").append('<option id="'+countydata.result[i].county_id+'" >'+countydata.result[i].name+'</option>');
                        }
                      }
                    };
                    getData(url,data,callback);
                    getCountyData(cityId);
                  }else if($("#city").val()=="请选择城市"){
                            $("#county").empty();
                            $("#county").append('<option id="selectCounty">请选择区/县</option>');
                    }
              });
           });
	     county();
  }
  
  function county(){
	  $("#county").change(function(){
           var countyVal = null;
           var countyId = null;
           countyVal = $("#county").val();
           $("#county").find('option').each(function(){
               if($(this).val()==countyVal && $(this).val()!="请选择区/县"){
                   countyId = $(this).attr('id');
                   url = "findTownByCounty";
                   data = {county_id:countyId};
                   console.log("ppp");
                   callback = function(towndata){
                   console.log(towndata);
                   $("#town").empty();
                   $("#town").append('<option>请选择城镇</option>');
                   $("#village").empty();
                   $("#village").append('<option>请选择乡村</option>');
                   if(towndata.result.length!=0){
                     for(var i=0;i<towndata.result.length;i++){
                       $("#town").append('<option id="'+towndata.result[i].town_id+'" >'+towndata.result[i].name+'</option>');
                     }
                   }
                 };
                 getData(url,data,callback);
                 getTownData(countyId);
               }else if($("#county").val()=="请选择区/县"){
                         $("#town").empty();
                         $("#town").append('<option id="selectTown">请选择城镇</option>');
                 }
           });
        });
	  town();
  }
  
  function town(){
	  $("#town").change(function(){
          var townVal = null;
          var townId = null;
          townVal = $("#town").val();
          $("#town").find('option').each(function(){
              if($(this).val()==townVal && $(this).val()!="请选择城镇"){
                  townId = $(this).attr('id');
                  url = "findVillageByTown";
                  data = {town_id:townId};
                  callback = function(villagedata){
                  console.log(villagedata);
                  $("#village").empty();
                  $("#village").append('<option>请选择乡村</option>');
                  if(villagedata.result.length!=0){
                    for(var i=0;i<villagedata.result.length;i++){
                      $("#village").append('<option id="'+villagedata.result[i].code+'" >'+villagedata.result[i].name+'</option>');
                    }
                  }
                };
                getData(url,data,callback);
                getVillageData(townId);
              }else if($("#town").val()=="请选择城镇"){
                        console.log("请选择城镇！");
                        $("#village").empty();
                        $("#village").append('<option id="selectVillage">请选择乡村</option>');
                }
          });
       });
  }
  
  
  function getCityData(provinceId){
	  url = "findCityByProvinceId";
	  data = {province_id:provinceId};
	  callback = function(data){
		  showCityTable(1,data);
	  }
	  getData(url,data,callback);
	  
  }
  
  function getCountyData(cityId){
	  url = "findCountyByCityId";
    data = {city_id:cityId};
    callback = function(data){
      showCountyTable(1,data);
    }
    getData(url,data,callback);
  }
  
  function getTownData(countyId){
	    url = "findTownByCountyId";
	    data = {county_id:countyId};
	    callback = function(data){
	      showTownTable(1,data);
	    }
	    getData(url,data,callback);
	  }
  
  function getVillageData(townId){
	    url = "findVillageByTownId";
	    data = {town_id:townId};
	    callback = function(data){
	      showVillageTable(1,data);
	    }
	    getData(url,data,callback);
	  }
  
  function deviderset(totalcount,pagecount,pageindex){
	    console.log(totalcount);
	    console.log(pagecount);
	    console.log(pageindex);
	    var summery ="共"+totalcount+"条数据--共"+pagecount+"页";
	    $("#dividersummery").html(summery);
	    
	    var deviderhtml="";
	    if(pageindex==1){
	      deviderhtml+="<ul><li  class='prev ' style='display:none'><a  onclick='queryinfo("+Number(pageindex-1)+")'>← <span class='hidden-480'>Prev</span></a></li>";
	    }else{
	      deviderhtml+="<ul><li  id='firstdiv' class='prev '><a  onclick='queryinfo("+Number(pageindex-1)+")'>← <span class='hidden-480'>Prev</span></a></li>";
	    }
	    
	    
	    if(pagecount<6){
	      
	      for(var i=1;i<pageindex;i++){
	        
	        deviderhtml+="<li ><a onclick='queryinfo("+i+")'>"+i+"</a></li>";
	        
	      }
	      
	      
	    }else{
	      
	      if(pageindex<3){
	        for(var j=1;j<6;j++){
	          
	          deviderhtml+="<li ><a onclick='queryinfo("+j+")'>"+j+"</a></li>";
	          
	        }
	        
	      }else if(pageindex<=pagecount-2){
	        
	        var m =pageindex-2;
	        for(var k=0;k<5;k++){
	          
	          deviderhtml+="<li ><a onclick='queryinfo("+m+")'>"+m+"</a></li>";
	          m++;
	        }
	        
	        
	      }else if(pageindex>pagecount-2){
	        var n =pageindex-4;
	        for(var q=0;q<5;q++){
	          deviderhtml+="<li ><a onclick='queryinfo("+n+")'>"+n+"</a></li>";
	          n++;
	        }
	      }
	      
	    }
	    

	    if(pageindex ==pagecount){
	      deviderhtml+="<li id='lastdiv ' style='display:none'><a  onclick='queryinfo("+Number(pageindex+1)+")'> <span class='hidden-480'>Next</span> → </a></li></ul>";
	    }else{
	      deviderhtml+="<li id='lastdiv '><a  onclick='queryinfo("+Number(pageindex+1)+")'> <span class='hidden-480'>Next</span> → </a></li></ul>";
	    }
	    
	    $("#devider").html(deviderhtml);
	  }
  

  </script>

</body>

</html>