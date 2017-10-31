<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>满集生活缴费平台</title>
	
	<style type="text/css">
	.font-exLarge1{
    font-size: 26px;
    font-weight: bold;
    line-height: 54px;
   }
	</style>
	
</head>
<body>
	<div class="pure-g">
	<div  class="pure-u-1">
	<%@ include file="../header.jsp" %>
	</div>
	</div>
	<div id="main" class="pure-g">
	<div  class="pure-u-1-8">
	<%@ include file="../menu.jsp" %>
	</div>
	<div id="maincontent" class="pure-u-5-6 mainbackground">
		 <div style="border-bottom: 1px solid #dcdcdc;margin-bottom: 10px;">
				 <h2>
			                           固话宽带充值&nbsp;&nbsp;   
				     
			     </h2>
		 </div>
		 <div class="well well-sm">
		 <form action="${ctx}/gk/ghkdCreateBill" method="post" class="pure-form pure-form-aligned"> 
			<!-- 套餐充值:选择商品运营商/选择充值类型/选择充值面值/ -->
			
		  	<div>
		          <table class="form form-large orderForm">
                    <tbody>
                    <tr>
						  	 <c:choose>
						  	 <c:when test="${errMsg!=null}">
						  	 	<div><label id="wh" style="margin-left:58px;color:#F00;">${errMsg}</label></div>
						  	 </c:when>
						  	 </c:choose>	
						  	 </tr>
                    <tr>
                       <td class="label-td"><label for="">区&nbsp;&nbsp;&nbsp;域：</label></td>
                       <td>
	                       <div style="display: inline;">
					        <select style="width: 120px ;padding-top: 5px; "  id="province">
								<option value="v2070" selected="selected" >重庆</option>
								<option value="v2129">甘肃</option>
								<option value="v1955">浙江</option>
								<option value="v2089">天津</option>
								<option value="v2101">安徽</option>
								<option value="v1953">北京</option>
								<option value="v2282">湖北</option>
								<option value="v2159">山西</option>
								<option value="v2210">山东</option>
								<option value="v2171">辽宁</option>
								<option value="v1967">河南</option>
								<option value="v1986">海南</option>
								<option value="v2043">内蒙古</option>
								<option value="v2198">河北</option>
								<option value="v2091">贵州</option>
								<option value="v1918">湖南</option>
								<option value="v2142">新疆</option>
								<option value="v1933">四川</option>
								<option value="v1988">西藏</option>
								<option value="v1904">黑龙江</option>
								<option value="v2228">广东</option>
								<option value="v2280">上海</option>
								<option value="v2186">江西</option>
								<option value="v2260">宁夏</option>
								<option value="v2269">陕西</option>
								<option value="v2118">广西</option>
								<option value="v2056">江苏</option>
								<option value="v2072">云南</option>
								<option value="v1892">吉林</option>
								<option value="v2250">青海</option>
								<option value="v2297">福建</option>
				            </select>
				            <select style="width: 120px; padding-top: 5px;"  id="city">
				            </select>
					      </div>
                       </td>
                    </tr>
                    
                    
                    
                    <tr style="" id="unitListTr">
                        <td class="label-td"><label for="">运营商：</label></td>
                        <td>
                         <ul id="unitList" class="horizonal valueSelection valueSelection-radio">
	                         <li>
								<label id="classnameYxs"  name="0" class="classname selected">电信</label>
								
							 </li> 
							 <li>
								 <label  name="1" class="classname">联通</label>
							 </li>
                         </ul>
                        </td>
                      </tr>
                    <tr style="" id="chargeTypeListTr">
                        <td class="label-td"><label for="">充值类型：</label></td>
                        <td>
                        <ul class="horizonal valueSelection valueSelection-radio" id="chargeTypeList">
                         <li>
                         <label id="classczlxItemId" class="classczlx selected" name="0">宽带</label>
                         </li>
                         <li>
                         <label class="classczlx"  name="1">固话</label>
                         </li>
                        </ul>
                        </td>
                    </tr>
                    <tr style="display: none;" id="payMode">
                        <td class="label-td"><label for="">缴费方式：</label></td>
                        <td>
                            <ul class="horizonal valueSelection valueSelection-radio" id="payType" name="payType"><li><input type="radio" class="input-r" name="payTypeRadio" id="v2622" value="v2622"><label for="v2622" state="1" class="selected">账号</label> </li></ul>
                        </td>
                    </tr>
                    <tr>
                        <td class="label-td"><label id="payTypeLabel" for="">账&nbsp;&nbsp;&nbsp;号：</label></td>
                        <td>
                            <input   type="text" class="input-xlarge input-t pushr valid" style="height: 30px;" maxlength="12" id="account" name="account" autocomplete="off">
                            <span id="gkjy" style="color: red"> </span>
                           <br>
                           <div style="height: 5px;"></div>
                           <span class="label">示例：18888888888 或 010-88888888</span>
                           <div id="exLargeNumberTr" style="display: none;margin-top: 10px;">
			            	<!-- 展示号码 -->
							<span class="font-exLarge orange"  id="enlargeNum"></span>
		    			   </div>
                   		  </td>
                    </tr>
                    <tr id="faceValueTr" style="display: none;">
                        <td class="label-td"><label for="">选择面值：</label></td>
                        <td>
                                 
                               <ul id="faceValues"   class="horizonal valueSelection valueSelection-radio">
                               
                               </ul>
                        </td>
                    </tr>
                    <tr class="spjg"  style="display: none;">
                        <td class="label-td"><label for="">商品名称：</label></td>
                        <td>
                          <span id="spmc"></span>
                        </td>
                    </tr>
                    <tr class="spjg" style="display: none;">
                        <td class="label-td"><label for="">零售价格：</label></td>
                        <td>
                           <span id="lsjg"  class="font-exLarge1 orange"></span>
                        </td>
                    </tr>
                  	<tr>
                  		<td></td>
	                  	<td>
	                  	<!-- 订单编号 -->
	                  	<input type="hidden" value="" id="itemId" name="itemId" >
	                  	<input type="text" value = "提交订单" disabled="disabled" id="placesubmit" class="btn btn-warning btn-large hide">
	                  	<input class="btn btn-warning btn-large"  id="tjal" disabled="disabled"  type="submit" style=" width: 100px; text-align:center;"
						 value="提交订单">
	                  	</td>
                  	</tr>
		          </tbody>
		          </table>
		    </div>
		</form>
	</div>
	</div>
	<div id="btn2" class="sgBtn"  style="display: none;" > 弹窗2(提示)</div>
<script type="text/javascript">

	$(".index4").addClass("check");

	/* 固话号码验证 */
	$("#account").keyup(function(){
		/* 展示输入信息 */
		var str=$("#account").val();
		$("#exLargeNumberTr")[0].style.display="block";
		$("#enlargeNum")[0].innerText =str;
	})

</script>


<script  type="text/javascript">

	var provincevid;
	var arr=new Array();
    var dataList= new Array();
    var b=0;
    $(document).ready(function() {
    	
             if($("#province").val()=='v2070'){
            	
                    provincevid=$("#province").val();//省编号
                
                    $("#city").append('<option selected="selected"> 重庆 </option>');
            }else{
                    $("#province").append('<option value="'+ province.vid +'">'+ province.vname +'</option>');
            } 
             
        $("#province").on("change", function() {
            $("#city").empty();
            provincevid = $(this).val();
            if (provincevid == '') {
                return;
            }
                var provinceName=$(this).find("option:selected").text(); //获取select选择的Text :
            	
                $("#city").append('<option selected="selected"  >--请选择城市--</option>');
            
            	switch (provinceName) {
            	
				case "重庆":
	                $("#city").append('<option value="">'+provinceName+'</option>');
					break;
					
				case "北京":
	                $("#city").append('<option value="">'+provinceName+'</option>');

					break;
				case "上海":
	                $("#city").append('<option value="">'+provinceName+'</option>');

					break;
				case "天津":
	                $("#city").append('<option value="">'+provinceName+'</option>');

					break;
				case "内蒙古":
					
	                $("#city").append('<option value="">'+provinceName+'全区</option>');

					break;
					
				case "新疆":
					
	                $("#city").append('<option value="">'+provinceName+'全区</option>');

					break;
					
				case "西藏":
	                $("#city").append('<option value="">'+provinceName+'全区</option>');

					break;
					
				case "宁夏":
					
	                $("#city").append('<option value="">'+provinceName+'全区</option>');

					break;
					
				case "上海":
	                $("#city").append('<option value="">'+provinceName+'</option>');

					break;
				case "全国":
					
	                $("#city").append('<option value="">'+provinceName+'</option>');

					break;
					
				default:
					
	                $("#city").append('<option value="">'+provinceName+'全省</option>');
					
					break;
				}
            	
                 $("#city").trigger("chosen:updated");
        });
        
        $("#city").on("change", function() {
            //清空账号输入款
             $("#account").val("");
			//清空展示
			$("#enlargeNum").text(" ");
            //隐藏商品列表
        	$("#faceValueTr").hide();
        	//隐藏商品名称和价格
        	$(".spjg").hide();
        	//隐藏提交按钮
            $('#tjal').attr("disabled","true");
        	
        	
        	var yxs=$(".classname.selected").attr("name");

        	if(yxs!=0){
        		$(".classname.selected").removeClass("selected");
        		$("#classnameYxs").addClass("selected");
        	}
        	
        	var itemId=$(".classczlx.selected").attr("name");
        	
        	if(itemId!=0){
        		
        		$(".classczlx.selected").removeClass("selected");
        		$("#classczlxItemId").addClass("selected");

        	}
        	
            $("#faceValues").empty();//添加前先清空
        	
            GhkdItemListRequest()//固话宽带充值列表
        })
        
         GhkdItemListRequest()//固话宽带充值列表
        
    });   
    
    function GhkdItemListRequest() {
    //固话宽带充值列表
          $.ajax({
          url: "${ctx}/gk/GhkdItemListRequest",
          type: "post",
          dataType:"json",
          data: 'provincevid='+provincevid,
          success: function (data) {
        	b=0;//初始b
      	    arr=[];//清空arr
      	    dataList=[];//清空数据
      	    dataList=data;
        	  if(data==0){
        		var txt=  "该地区暂未提供服务";
      			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
      			return false;  
        	  } 
               //json变量现在就是一个数组对象，直接读取每个数组对象。结合属性进行输出 
               var dianxin =dataList[0];
               //   console.log(dianxin+"电信"); IE8以及以下不支持
               var dkd=dianxin[0]; 
               if(dkd==""){
                   $("#faceValueTr").show();//展示充值列表
            	   var li="<li>"
                       +"<label>该类别暂无商品请选择其他类别 </label>"
                       +"</li>";
              	       $("#faceValues").append(li); 
              	       return false;
               }
               for (var i = 0; i < dkd.length; i++) {
              	 var yuan=dkd[i].itemName;//获取每一个商品元素
              	 var string=yuan.split(' ');//分组
              	 var len=string[7];//商品金额
              	 var je=len.substring(2,len.length-1);
              	 if(je!="任意"){
                 	   arr[b]=dkd[i];
                 	   b=b+1;
                 	}
  		     }
               info();
          }
        }); 
	}
    
    function info() {
        $("#faceValues").empty();//添加前先清空
      //冒泡排序法排序
    if(0<arr.length){
       	  for(var i=0;i<arr.length;i++){
           	for(var j=0; j<arr.length-1;j++){
           		
   				 var len1=arr[j+1].itemName;
	   			 var string1=len1.split(' ');//分组
	          	 var price1=string1[7];//商品金额
	          	 var je1=price1.substring(2,len1.length-1);
   				
   				 var len=arr[j].itemName;
   				 var string=len.split(' ');//分组
	          	 var price=string[7];//商品金额
	          	 var je=price.substring(2,len.length-1);

   				
   				if(parseInt(je) >parseInt(je1)){
   					var temp=0;
   					temp =arr[j];
   					arr[j]=arr[j+1];
   					arr[j+1] =temp;
   				}
   			} 
     	}
        //遍历已经赋值的arry数组并展示数据
        $("#faceValueTr").show();//展示充值列表
       	for(var i=0;i<arr.length;i++){
       		
       		var itemName=arr[i].itemName;
       		
       		var string1=itemName.split(' ');//分组
       		
         	var price1=string1[7];//商品金额
         	 
            var je1=price1.substring(2,len1.length);
         	
          	 var li="<li>"
               +"<label onclick='itemIds(this)' class='itemIds' name="+arr[i].itemId+">"+je1+"</label>"
               +"</li>"
      	      $("#faceValues").append(li); 
       	}
      }
	}
    
    //运营商点击换商品信息
    $(".classname").on("click",function(){
    	
    	$(".spjg").hide();
    	$("#tjal").attr("disabled",true);
    	$(".classname").removeClass("selected");
    	$(this).addClass("selected");
    	//获取运营商
    	var yxs=$(".classname.selected").attr("name");
    	var yxsList =dataList[yxs];
    	//获取充值类型
    	var czlx=$(".classczlx.selected").attr("name");
        var czlxList=yxsList[czlx]; 
        if(czlxList==""){
            $("#faceValues").empty();//添加前先清空
            $("#faceValueTr").show();//展示充值列表
     	   var li="<li>"
                +"<label>该类别暂无商品请选择其他类别 </label>"
                +"</li>";
       	       $("#faceValues").append(li); 
       	       return false;
        }
        //清空arr
         arr=[];
         var b=0;
         for (var i = 0; i < czlxList.length; i++) {
        	 var yuan=czlxList[i].itemName;//获取每一个商品元素
        	 var string=yuan.split(' ');//分组
        	 var len=string[7];//商品金额
        	 var je=len.substring(2,len.length-1);
        	 if(je!="任意"){
           	   arr[b]=czlxList[i];
           	   b=b+1;
           	}
	     }
         info();
	})
    
    //点击充值类型换商品信息
    $(".classczlx").on("click",function(){
    	
    	$(".spjg").hide();
    	$("#tjal").attr("disabled",true);
    	
    	$(".classczlx").removeClass("selected");
    	$(this).addClass("selected");
       //获取运营商编号
    	var yxs=$(".classname.selected").attr("name");
    	var yxsList =dataList[yxs];
    	//获取充值类型编号
    	var czlx=$(".classczlx.selected").attr("name");
        var czlxList=yxsList[czlx]; 
        if(czlxList==""){
            $("#faceValues").empty();//添加前先清空
            $("#faceValueTr").show();//展示充值列表
     	   var li="<li>"
                +"<label>该类别暂无商品请选择其他类别 </label>"
                +"</li>";
       	       $("#faceValues").append(li); 
       	       return false;
        }
        //清空arr
         arr=[];
         var b=0;
         for (var i = 0; i < czlxList.length; i++) {
        	 var yuan=czlxList[i].itemName;//获取每一个商品元素
        	 var string=yuan.split(' ');//分组
        	 var len=string[7];//商品金额
        	 var je=len.substring(2,len.length-1);
        	 if(je!="任意"){
           	   arr[b]=czlxList[i];
           	   b=b+1;
           	}
	     }
         
        
         
         info();
	})
	
    //点击商品价格创建商品订单,返回商品价格
   function itemIds(obj){
    	$(".itemIds").removeClass("selected");
    	$(obj).addClass("selected");
    	//获取商品编号
    	var itemId=$(".itemIds.selected").attr("name");
    	//获取账号加校验
		var account=$("#account").val();    
    	var c=0;
    	if(account!=''&&account!=null){
    		/* 固话号码验证 */
    		$("#gkjy").text('');
    		var gu=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;//固话
    		var kd=/^1[3|4|5|7|8][0-9]{9}$/;//宽带

    		 if(gu.test(account)){
    			c=1;
    		}else  if (kd.test(account)) {
    			c=1;
    		}
    		
    		if(parseInt(c)==0){
    			$("#gkjy").text("固话或宽带号码格式错误");
        		return false;
    		}
    		
    	}else{
    		var txt=  "账号不能为空";
  			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
  			return false;
    	}
    	
    	$("#itemId").attr("value",itemId);
    	//异步加载获取价格
    	 $.ajax({
          url: "${ctx}/gk/getItemInfo",
          type: "get",
          dataType:"json",
          data: 'itemId='+itemId,
          success: function (data) {
        	  if(data==0){
        		    var txt="价格查询失败请稍后重试";
        			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
        			return false;
        	  }
        	  var data=eval(data);
        	 /*"payState":"0",	//订单付款状态 0 未付款1 已付款
              "itemName":"重庆 电信 固话宽带新 账号 宽带 直充20元",//标准商品名乐
              "rechargeState":"0",//	订单充值状态 0充值中 1成功 9撤销
              "account":"18716386095",//固话宽带充值账号或户号
              "salePrice":"20.000",//	售价，即应收金额
              "outerTid":"1471235291463694474",//	外部订单号
              "tid":"S1608158546763",//	订单编号
              "itemId":"16125511" */ //下单的标准商品编号
        	  $("#spmc").text(data.itemName);
        	  $("#lsjg").text(data.facePriceValue);
        	  $(".spjg").show();
        	  $('#tjal').removeAttr("disabled");
             }
          })
    	
	}
 // 判断是否维护
	if($("#wh").length){
		$("#placesubmit").removeClass("hide");
		$("#tjal").addClass("hide");
	}else{
		$("#placesubmit").addClass("hide");
		$("#tjal").removeClass("hide");
	}
</script>
</body>
</html>