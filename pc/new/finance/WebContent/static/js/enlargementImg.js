;(function($){
	$.fn.enlargement = function(options){
		var defaults = {
//		    controls: true,          //上一张、下一张是否显示，默认是显示true
//		    element: "li",  //图片外层标签
//		    nameClass: ""//一个页面有多个放大，nameClass必须设置不同的值
		}
		
		var opts = $.extend(defaults, options);		
		var lb_wrap = '<div class="enlargement_shield"></div>'+
					'<div class="enlargement_box">'+
						'<div class="enlargement_content">'+
							'<div class="enlargement_content_box">'+
								'<img src="image/loading.gif">'+
							'</div>'+
						'</div>'+
					'</div>';
		$("body").append(lb_wrap);

		
		function imgobj(obj1, obj2){
			//imgObj.height是通过img对象获取的图片的实际高度
			var imgObj = new Image();
			
			imgObj.src = obj1.attr("issrc");
			
			var margintop = 0 - (imgObj.height)/2; 
			obj2.css("margin-top",margintop);
			}		
		
		this.each(function(){
			var obj = $(this);
			var numpic = obj.length;
			var num = 0;
			
			//点击赋值并显示
			obj.click(function(event){	
				event.preventDefault();
				layer.closeAll();
				var src = $(this).attr("issrc");							
				$(".enlargement_content img").attr("src",src);
				imgobj($(this), $(".enlargement_content"));				
				
				$(".enlargement_shield").fadeIn();
				$(".enlargement_box").fadeIn();
				//a(".prev[controls='true']").fadeIn().siblings(".next[controls='true']").fadeIn();
				num = $(this).parent().index();   //获取当前图片的父元素的索引并赋给num为后边点击上一张、下一张服务		
				});
			
//			//上一张
//			$(".prev").click(function(){								
//				if(num == 0){
//					  num = numpic;
//				  }				
//				var src = obj.find(opts.element).eq(num - 1).find("img").attr("src");
//				$(".lg_img").attr("src",src);				
//				imgobj($(".lg_img"), $(".lightbox"));
//				
//				num--;
//				});
//				
//			//下一张
//			$(".next").click(function(){
//				if(num == numpic-1){
//					  num = -1;
//				}				
//				var src = obj.find(opts.element).eq(num + 1).find("img").attr("src");
//				$(".lg_img").attr("src",src);				
//				imgobj(a(".lg_img"), a(".lightbox"));
//				
//				num++;
//				});
				
			//点击除了上一张、下一张之外的其他地方隐藏
			$(".enlargement_box").click(function(e){
				  var e = e || window.event;
				  var elem = e.target || e.srcElement;
				  while(elem){
					  if (elem.className && elem.className.indexOf('prev')>-1) {
						  return;
					  }
					  if(elem.className && elem.className.indexOf('next')>-1){
						  return;				  
						  }
					  elem = elem.parentNode;
				  }
				    $(this).find("img").attr("src","image/loading.gif");      //隐藏后，再将默认的图片赋给lightbox中图片的src
				    //a(this).find(".prev").hide().siblings(".next").hide();
				 	$(".enlargement_box").fadeOut();
				 	$(".enlargement_shield").fadeOut();
				});  
			});
		}
	})(jQuery);