/**
 * Created by pudding on 2017-6-29.
 */

//跳转到举报详情页面
function ireportInfo(sheet_id){
    var id=$(sheet_id).next().val();
    window.location.href="/sheet/shopApp/findSheetInfo?id="+id+"";
}
