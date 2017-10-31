/**
 * Created by pudding on 2017-7-2.
 */
function findsugginfo(sheet_id){
    var id=$(sheet_id).next().val();
    window.location.href="/sheet/shopApp/findopinion?id="+id+"";
}