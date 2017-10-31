<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <title>Document</title>
  <link rel="stylesheet" type="text/css" href="../../css/frozen.css">
  <link rel="stylesheet" type="text/css" href="../../css/appstyle.css">
</head>
<body>
  <section id="main">
    <header class="ui-header ui-header-positive ui-border-b">
      <i class="ui-icon-return" onclick="history.back()"></i><h1>配置列表 </h1>
    </header>
    <section class="ui-container">
      <div class="ui-btn-wrap">
        <button class="ui-btn ui-btn-danger addLine">
          新增
        </button>
      </div>

      <section>
	      <table id="shopList" class="ui-table ui-border">
	        <thead>
	          <tr><th>名称</th><th>别名</th><th>描述</th></tr>
	        </thead>
	        <tbody>
	        
	        </tbody>
	      </table>
      </section>
    </section>
  </section>
  
  <!-- 新增配置 -->
  <section id="addLine" class="hide ">
    <header class="ui-header ui-header-positive ui-border-b">
      <i class="ui-icon-return" onclick="showMain()"></i><h1>新增 </h1>
    </header>
    <section class="ui-container">
      <div class="ui-form ui-border-t">
        <form id="addForm" action="#">
          <input type="hidden" name="shopId" value="${shopId}">
          <div class="ui-form-item ui-border-b">
            <label>队列名称</label>
            <input type="text" id="addTypeName" name="lineTypeName" placeholder="例：大包间">
            <a href="#" class="ui-icon-close">
            </a>
          </div>
          <div class="ui-form-item ui-border-b">
            <label>队列别名</label>
            <div class="ui-select">
	            <select id="addTypeAs" name="lineTypeAs" style="display:inline">
	                <option value="A">A</option>
	                <option value="B">B</option>
	                <option value="C">C</option>
	                <option value="D">D</option>
	                <option value="E">E</option>
	                <option value="F">F</option>
	                <option value="G">G</option>
	                <option value="H">H</option>
	                <option value="I">I</option>
	                <option value="J">J</option>
	                <option value="K">K</option>
	                <option value="L">L</option>
	                <option value="M">M</option>
	                <option value="N">N</option>
	                <option value="O">O</option>
	                <option value="P">P</option>
	                <option value="Q">Q</option>
	                <option value="R">R</option>
	                <option value="S">S</option>
	                <option value="T">T</option>
	                <option value="U">U</option>
	                <option value="V">V</option>
	                <option value="W">W</option>
	                <option value="X">X</option>
	                <option value="Y">Y</option>
	                <option value="Z">Z</option>
	            </select>
	          </div>
            <a href="#" class="ui-icon-close">
            </a>
          </div>
         <input type="hidden" id="addVal" name="initalValue" value="1">

          <div class="ui-form-item ui-form-item-textarea ui-border-b">
            <label>描述</label>
            <textarea id="addDesc" name="descroption" placeholder="队列描述"></textarea>
          </div>
        </form>
        <div class="ui-btn-wrap">
          <button id="addSubmit" class="ui-btn-lg ui-btn-danger">
            确定
          </button>
        </div>
      </div>
    </section>
  </section> 
  <!-- 修改配置 -->
  <section id="updateLine" class="hide ">
    <header class="ui-header ui-header-positive ui-border-b">
      <i class="ui-icon-return" onclick="showMain()"></i><h1>修改 </h1>
      <button id="del" class="ui-btn">
        删除
      </button>
    </header>
    <section class="ui-container">
      <div class="ui-form ui-border-t">
        <form id="updateForm" action="#">
          <input type="hidden" name="shopId" value="${shopId}">
          <input type="hidden"  id="listId" value="">
          <div class="ui-form-item ui-border-b">
            <label>队列名称</label>
            <input type="text" id="updateTypeName" name="lineTypeName" placeholder="例：大包间">
            <a href="#" class="ui-icon-close">
            </a>
          </div>
          <div class="ui-form-item ui-border-b">
            <label>队列别名</label>
            <div class="ui-select">
              <select id="updateTypeAs" name="lineTypeAs" style="display:inline">
                  <option value="A">A</option>
                  <option value="B">B</option>
                  <option value="C">C</option>
                  <option value="D">D</option>
                  <option value="E">E</option>
                  <option value="F">F</option>
                  <option value="G">G</option>
                  <option value="H">H</option>
                  <option value="I">I</option>
                  <option value="J">J</option>
                  <option value="K">K</option>
                  <option value="L">L</option>
                  <option value="M">M</option>
                  <option value="N">N</option>
                  <option value="O">O</option>
                  <option value="P">P</option>
                  <option value="Q">Q</option>
                  <option value="R">R</option>
                  <option value="S">S</option>
                  <option value="T">T</option>
                  <option value="U">U</option>
                  <option value="V">V</option>
                  <option value="W">W</option>
                  <option value="X">X</option>
                  <option value="Y">Y</option>
                  <option value="Z">Z</option>
              </select>
            </div>
            <a href="#" class="ui-icon-close">
            </a>
          </div>

          <div class="ui-form-item ui-form-item-textarea ui-border-b">
            <label>描述</label>
            <textarea id="updateDesc" name="descroption" placeholder="队列描述"></textarea>
          </div>
        </form>
        <div class="ui-btn-wrap">
          <button id="updateSubmit" class="ui-btn-lg ui-btn-danger">
            确定
          </button>
        </div>
      </div>
    </section>
  </section>

  <div class="ui-dialog" id="del-confire">
    <div class="ui-dialog-cnt">
      <div class="ui-dialog-bd">
        <div>
          <h4>温馨提示</h4>
          <div>确认删除吗？</div>
        </div>
      </div>
      <div class="ui-dialog-ft ui-btn-group">
        <button type="button" data-role="button" class="select" id="del-ok">确认</button>
        <button type="button" data-role="button" id="cancel">取消</button>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="../../js/zepto.min.js"></script>
  <script type="text/javascript" src="../../js/frozen.js"></script>
  <script type="text/javascript">
    $(function(){
      clearVal();

      $('#shopList tbody').on('tap','.updateLine',function(){
    	  var id = $(this).parent().siblings('.id').val();
    	  $("#listId").val(id);
          $.ajax({
            type: 'GET',
            data: {},
            url: '/lineOL/shop/queryShopConfigInfoById/'+id,
            success: function(data){
            	console.log(data)
              if(data.code == 'SUCCESS'){
            	  $("#updateTypeName").val(data.obj.lineTypeName);
            	  $("#updateTypeAs").val(data.obj.lineTypeAs);
            	  $("#updateVal").val(data.obj.initalValue);
            	  $("#updateDesc").val(data.obj.descroption);
              }
            }
          });
          showUpdate();
      });
      $('.addLine').on('tap',function(){
    	  
        showAdd();
      });

      $('#cancel').tap(function(){
        $('#del-confire').removeClass('show');
      });
      $("#del").tap(function(){
        $('#del-confire').addClass('show');
      });
      $('#del-ok').tap(function(){
    	  var id = $("#listId").val();
    	  var shopId = ${shopId};
    	  $.ajax({
 	        type: 'GET',
 	        data: {
 	          id: id,
 	          shopId: shopId
 	        },
 	        url: '/lineOL/shop/removeShopConfigInfo/'+id+'/'+shopId,
 	        success: function(data){
 	        	var dia=$.dialog({
 	               title:'温馨提示',
 	               content:data.description,
 	               button:["确认"]
 	           });
 	         $('#del-confire').removeClass('show');
 	          showMain();
 	          loadData();
 	        }
    	  });
        
      });
      
      $("#addSubmit").tap(function(){
    	  addSubmit();
      });
      $("#updateSubmit").tap(function(){
          updateSubmit();
        });

    });

    function showUpdate(){
      $('#updateLine').siblings('section').addClass('hide');
      $('#updateLine').removeClass('hide');
    }
    function showMain(){
      $('#main').removeClass('hide');
      $('#main').siblings('section').addClass('hide');
    }
    function showAdd(){
      $('#addLine').removeClass('hide');
      $('#addLine').siblings('section').addClass('hide');
    }
    function clearVal(){
      $('.ui-icon-close').hide();
      $('.ui-icon-close').tap(function(){
        $(this).siblings('input').val(''); 
        $(this).hide();
      });
      $('input').blur(function(){
        $(this).siblings('.ui-icon-close').hide();
      });
      $('input').focus(function(){
        if($(this).val() != ''){
          $(this).siblings('.ui-icon-close').show();
        }else{
          $(this).siblings('.ui-icon-close').hide();
        }
      });
    }
    
    function addSubmit(){
    	var lineTypeName = $('#addTypeName').val();
    	var lineTypeAs = $('#addTypeAs').val();
    	var initalValue = $('#addVal').val();
    	var descroption = $('#addDesc').val();
    	var shopId = ${shopId};
    	
    	var numstr = /^[0-9]+$/;
    	var typeas = /^[a-zA-Z]+$/;
    	if(lineTypeName == '' ){
    		var dia=$.dialog({
            title:'温馨提示',
            content:'队列名称不能为空',
            button:["确认"]
        });
    		return false;
    	}
    	if(lineTypeAs == ''){
    		var dia=$.dialog({
                title:'温馨提示',
                content:'队列别名不能为空',
                button:["确认"]
            });
        return false;
      }
    	if(!(typeas.test(lineTypeAs))){
        var dia=$.dialog({
            title:'温馨提示',
            content:'队列别名只能为字母',
            button:["确认"]
        });
        return false;
      }
    	if(initalValue == '' ){
    		var dia=$.dialog({
                title:'温馨提示',
                content:'号码初始值不能为空',
                button:["确认"]
            });
        return false;
      }
    	if(!(numstr.test(initalValue)) ){
    		var dia=$.dialog({
            title:'温馨提示',
            content:'号码初始值只能为数字',
            button:["确认"]
        });
        return false;
      }
    	if(descroption == '' ){
    		var dia=$.dialog({
            title:'温馨提示',
            content:'描述不能为空',
            button:["确认"]
        });
        return false;
      }

    	$.ajax({
    		type: 'POST',
    		data: {
    			lineTypeName: lineTypeName,
    			lineTypeAs: lineTypeAs,
    			initalValue: initalValue,
    			descroption: descroption,
    			shopId: shopId
    		},
    		url: '/lineOL/shop/addShopConfigInfo',
    		success: function(data){
    			var dia=$.dialog({
              title:'温馨提示',
              content:data.description,
              button:["确认"]
          });
    			showMain();
          loadData();
    		}
    	  
    	});
    }
    function updateSubmit(){
    	var lineTypeName = $('#updateTypeName').val();
      var lineTypeAs = $('#updateTypeAs').val();
      var initalValue = $('#updateVal').val();
      var descroption = $('#updateDesc').val();
      var shopId = ${shopId};
      var id = $("#listId").val();
      
      var numstr = /^[0-9]+$/;
      var typeas = /^[a-zA-Z]+$/;
      if(lineTypeName == '' ){
        var dia=$.dialog({
            title:'温馨提示',
            content:'队列名称不能为空',
            button:["确认"]
        });
        return false;
      }
      if(lineTypeAs == ''){
        var dia=$.dialog({
                title:'温馨提示',
                content:'队列别名不能为空',
                button:["确认"]
            });
        return false;
      }
      if(!(typeas.test(lineTypeAs))){
        var dia=$.dialog({
            title:'温馨提示',
            content:'队列别名只能为字母',
            button:["确认"]
        });
        return false;
      }
      if(initalValue == '' ){
        var dia=$.dialog({
                title:'温馨提示',
                content:'号码初始值不能为空',
                button:["确认"]
            });
        return false;
      }
      if(!(numstr.test(initalValue)) ){
        var dia=$.dialog({
            title:'温馨提示',
            content:'号码初始值只能为数字',
            button:["确认"]
        });
        return false;
      }
      if(descroption == '' ){
        var dia=$.dialog({
            title:'温馨提示',
            content:'描述不能为空',
            button:["确认"]
        });
        return false;
      }
      $.ajax({
        type: 'GET',
        data: {
          lineTypeName: lineTypeName,
          lineTypeAs: lineTypeAs,
          shopId: shopId,
          id: id,
          initalValue: initalValue,
          descroption: descroption
        },
        url: '/lineOL/shop/modifyShopConfigInfo',
        success: function(data){
          var dia=$.dialog({
              title:'温馨提示',
              content:data.description,
              button:["确认"]
          });
          loadData();
          showMain();
        }
        
      });
    }
    loadData();
    function loadData(){
    	var shopId = ${shopId};
      $.ajax({
        type: 'GET',
        data: {
          shopId: shopId
        },
        url: '/lineOL/shop/queryShopConfigInfo/${shopId}',
        success: function(data){
          console.log(data)
          if(data.code == 'SUCCESS'){
        	  var list = data.resultList;
        	  var ul = $("#shopList tbody");
        	  ul.empty();
        	  for(var i = 0; i < list.length; i ++){
        		  var item = '<tr><input type="hidden" class="id" value="'+list[i].id+'"><td>'+list[i].lineTypeName+'</td><td>'+list[i].lineTypeAs+'</td><td>'+list[i].descroption+'</td><td><button style="margin-top:8px;" class="ui-btn-s ui-btn-danger updateLine"> 修改</button></td></tr>'
        		  
              ul.append(item);
        	  }
        	 
          }
        }
        
      });
    }
  </script>
</body>
</html>