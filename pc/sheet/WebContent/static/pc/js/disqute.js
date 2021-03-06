//提交建议弹窗
$(".add_confirm").click(function(){
	var h=$("body").height();
	var w=$("body").width();
	$(".suggset_pop_bg").show();
	$('body').css("overflow","hidden");
	$(".suggset_pop_bg").css({
		width:w,
		height:h,
		display:"block",
	});
	$(".suggset_pop").show();
});
	
//取消提交按钮
$(".suggset_pop_btn .cancel").click(function(){
	$(".suggset_pop_bg").hide();
	$(".suggset_pop").hide();
})
//投诉内容文字计数
$(".textarea_box textarea").keyup(function(){
	$(".textarea_num span").text($(this).val().length);
});

//建议类型选择
$(".Bar").on("click","li",function(){
	$(".Bar li").removeClass("active_Bar");
	$(this).addClass("active_Bar");
	$("#hidename").val($(this).attr("dataid"));
});

//上传图片长度判断
function lenght(){
	var lenght=$(".uploader-list li").length;
	if(lenght>=5){
		$(".webuploader-pick").css("display","none");
	}else{
		$(".webuploader-pick").css("display","block");
	}
}

//填写建议页面上次图片-删除图片功能
$("#fileList").on("click","span",function(){
	var arry=new Array;
	var indexs=$(this).parent('li').index();
	
	var text=$(".filebox").val().split(",");//获取img的vlue值
		console.log(text)
	arry.push(text);
	arry[0].splice(indexs,1);//删除选中的图片地址
		
		
	$(".filebox").val(arry[0]);//将删除的数组重新赋值给隐藏域；
	$(this).parent("li").remove();
	em();
	lenght();
});

function em(){
	var leng=$(".uploader-list li").length;
	$(".add_title em").text(leng);
	$(".picNum").text(leng);
	
};

//图片上传插件
	$(function(){
		var $list=$("#fileList");   //这几个初始化全局的百度文档上没说明.。  
	   	var $btn =$("#ctlBtn");   //开始上传  
	   	var thumbnailWidth = 120;   //缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档  
	   	var thumbnailHeight = 120; 
	   	
	   	var uploader = WebUploader.create({  
	       // 选完文件后，是否自动上传。  
	       auto: true,  
	
	       // swf文件路径  
	       swf: 'js/Uploader.swf',  
	
	       // 文件接收服务端。  
	       server: 'http://shop.manjiwang.com/tools/upload_ajax.ashx',  
	
	       // 选择文件的按钮。可选。  
	       // 内部根据当前运行是创建，可能是input元素，也可能是flash.  
	       pick: {
	    	   id : "#filePicker",
	    	   multiple: false
	       },  
	
	       // 只允许选择图片文件。  
	       accept: {  
	           title: 'Images',  
	           extensions: 'gif,jpg,jpeg,bmp,png',  
	           mimeTypes: 'image/jpg,image/jpeg,image/png'  
	       },  
	       method:'POST',
	     fileNumLimit: 5,
	   });
	   
	    uploader.on( 'fileQueued', function( file ) {  // webuploader事件.当选择文件后，文件被加载到文件队列中，触发该事件。等效于 uploader.onFileueued = function(file){...} ，类似js的事件定义。  
	    	var $li = $(
	               '<li id="' + file.id + '" class="file-item thumbnail">' +
	                   '<img>' +
	               '</li>'
	               ),  
	           $img = $li.find('img');  
	
	       // $list为容器jQuery实例  
	       $list.append( $li );
	       lenght();
	       // 创建缩略图  
	       // 如果为非图片文件，可以不用调用此方法。  
	       // thumbnailWidth x thumbnailHeight 为 120 x 120  
	       uploader.makeThumb( file, function( error, src ) {   //webuploader方法  
	           if ( error ) {  
	               $img.replaceWith('<span>不能预览</span>');  
	               return;  
	           }  
	
	           $img.attr( 'src', src );  
	       }, thumbnailWidth, thumbnailHeight );  
	   }); 
	   // 文件上传过程中创建进度条实时显示。  
	   uploader.on( 'uploadProgress', function( file, percentage ) {  
	       var $li = $( '#'+file.id ),  
	           $percent = $li.find('.progress span');  
	
//	       // 避免重复创建  
//	       if ( !$percent.length ) {  
//	           $percent = $('<p class="progress"><span></span></p>')  
//	                   .appendTo( $li )  
//	                   .find('span');  
//	       }  
	       // 避免重复创建  
	       if ( !$percent.length ) {  
	           $percent = $('<p class="progress"></p>')
	                   .appendTo( $li )  
	                   .find('span');  
	       }
	
	       $percent.css( 'width', percentage * 100 + '%' );  
	   });  
	
	   // 文件上传成功，给item添加成功class, 用样式标记上传成功。  
	   uploader.on( 'uploadSuccess', function( file,response ) {
	   	
	   	uploader.removeFile(file);


	   	var imgval=$(".filebox").val();
	   	if(imgval==""){
	   		 $(".filebox").val(response.path);
	   	}else{
	   		 $(".filebox").val(imgval+","+response.path);
	   	};
	   	
	       $( '#'+file.id ).addClass('upload-state-done'); 
	       $( '#'+file.id ).find("img").attr("src",response.path); //将服务器上的地址传到img里
	       $( '#'+file.id ).find("img").parent().append("<span></span>");
	     	lenght();
	     	em();
	   });  
	   // 文件上传失败，显示上传出错。  
	   uploader.on( 'uploadError', function( file ) { 
	       var $li = $( '#'+file.id ),  
	           $error = $li.find('div.error');  
	
	       // 避免重复创建  
	       if ( !$error.length ) {  
	           $error = $('<div class="error"></div>').appendTo( $li );  
	       }  
	
	       $error.text('上传失败');
	   });  
	
	   // 完成上传完了，成功或者失败，先删除进度条。  
	   uploader.on( 'uploadComplete', function( file ) {
	       $( '#'+file.id ).find('.progress').remove();
	   });    
	});