/**
 * Created by Administrator on 2017/8/9.
 */
$(function(){
    //列表商品选择
    $("tbody .w5 input[type='checkbox']").on("click",function(){
        var l=$("tbody .w5 input[type='checkbox']").length;
        var len=$("tbody .w5 input[type='checkbox']:checked").length;
        if($(this).prop("checked")){
            $(this).siblings("i").addClass("active");
        }else{
            $(this).siblings("i").removeClass("active");
        };
        if(l==len){
            $(this).parents("tbody").siblings("thead").find("input[type='checkbox']").prop("checked",true);
            $(this).parents("tbody").siblings("thead").find("i").addClass("active");
        }else{
            $(this).parents("tbody").siblings("thead").find("input[type='checkbox']").prop("checked",false);
            $(this).parents("tbody").siblings("thead").find("i").removeClass("active");
        };
    });

    //头部导航选中状态
    $(".nav li").click(function(){
        $(".nav li").removeClass("active_nav");
        $(this).addClass("active_nav");
    });


    //分类二级菜单选中
    $(".content_nav h3").click(function(){
        $('.content_nav li').removeClass("active_right_nav");
        $(this).parent().addClass("active_right_nav");
        $('.content_nav li').each(function(){
            if($(this).hasClass("active_right_nav")){
                $(this).find(".second_nav").slideDown(40);
            }else{
                $(this).find(".second_nav").slideUp(40);
            };
        });
    });

    $(".second_nav li").click(function(){
        $(".second_nav li").removeClass("active_li");
        $(this).addClass("active_li");
    });
})




