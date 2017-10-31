/**
 * Created by pudding on 2017-7-3.
 */
function finddispute(sheet_id){
    var id=$(sheet_id).next().val();
    window.location.href="/sheet/shopApp/finddispute?id="+id+"";
}
