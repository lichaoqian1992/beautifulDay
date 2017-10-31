/**
 * Created by pudding on 2017-7-1.
 */
//跳转到举报详情页面
function myreportInfo(sheet_id){
    var id=$(sheet_id).next().val();
    window.location.href="/sheet/shopApp/findmySheetInfo?id="+id+"";
}