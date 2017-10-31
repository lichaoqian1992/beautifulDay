package com.manji.sheet.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.json.JFinalJson;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.manji.sheet.base.Message;
import com.manji.sheet.intercepter.AppIntercepter;
import com.manji.sheet.model.bean.Account;
import com.manji.sheet.model.reqParam.SheetInfoParam;
import com.manji.sheet.service.BuyersAppService;
import com.manji.sheet.utils.ImgCompressUtils;
import com.manji.sheet.utils.PicUtils;
import com.manji.sheet.utils.WorkerNumberUtil;
import com.manji.sheet.utils.zipPicsUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;


//APP接口调用
@Before(AppIntercepter.class)
public class BuyersAppController extends Controller {

    private static Logger logger = Logger.getLogger(BuyersAppController.class);
    BuyersAppService appser = new BuyersAppService();
    private WorkerNumberUtil WorkerUtil;


    public void index() {
        renderText("sb");
    }

    /**
     * 发起举报-商家说明
     */
    public void shop() {
        render("informant_business_instruction.html");
    }

    /**
     * 发起举报-商品说明
     */
    public void goods() {

        render("informant_goods_instruction.html");
    }

    /**
     * 补充资料
     */
    public void addInfo() {

        setAttr("info", getParaToInt("sheet_id"));
        //查询一下sheet_no
        setAttr("sheet_no", Db.findFirst("select * from dt_sheets where id=?", getParaToInt("sheet_id")).get("sheet_no").toString());

        render("add_data.html");
    }

    /**
     * 我的建议/举报
     */
    public void mySuggestAndOther() {
        //setAttr("info", getParaToInt("sheet_id"));
//		Account account = getSessionAttr("Account");
        render("informant_suggestandother.html");
    }

    /**
     * 我的纠纷
     */
    public void myTrade() {
        redirect("/buyersApp/myInfo?codeReport=06");
    }

    public void findTradeByOrderId() {

        String orderNo = getPara("orderNo");

    }

    /**
     * 填写已解决资料
     */
    public void resolved() {

        setAttr("sheet_id", getPara("sheet_id"));
        render("resolved.html");
    }

    /**
     * 商家信息查询
     */
    public void shopInfo() {

        SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");
        Record shopInfo = appser.getShopByName(sheetInfo.getShopId());
        //举报商家
        sheetInfo.setCodeReport("02");
        List<Record> codeReportList = WorkerUtil.getcodeReportNo(sheetInfo.getCodeReport());

        setAttr("sheetInfo", sheetInfo);
        setAttr("shopInfo", shopInfo);//店铺信息
        setAttr("codeReportList", codeReportList);//二级类型信息
        render("business_informant_center.html");
    }

    /**
     * 商家举报
     */
    @SuppressWarnings("static-access")
    public void shopReport() {

        List<UploadFile> file = getFiles();
        String pics = "";
        String message = "";
        for (int i = 0; i < file.size(); i++) {
            File f = null;
            //如果上传的图片大于2M,那么就压缩图片
            if (file.get(i).getFile().length() >= 1024 * 1024 * 2) {

                //方法一：先压缩图片，然后上传图片
                /*ImgCompressUtils imgCom;
				try {
					BufferedImage sourceImg =ImageIO.read(new FileInputStream(file.get(i).getFile()));  
					int width = sourceImg.getWidth();
					int height = sourceImg.getHeight();
					//System.out.println(width+" "+height);
					imgCom = new ImgCompressUtils(file.get(i).getFile());
					String path = imgCom.resizeFix(width/2, height/2);  
					f = new File(path);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
                //方法二：
                File originalFile = file.get(i).getFile();
                File resizedFile = new File("/manji/log/tomcat7/456.jpg");//tomcat7 正式,tomcat_test测试
                //File resizedFile = new File("/manji/log/tomcat_test/456.jpg");//tomcat_test测试
                //File resizedFile = new File("C:/Users/Administrator/Desktop/456.jpg");//tomcat_test测试，tomcat7 正式
                try {
                    new zipPicsUtils().resize(originalFile, resizedFile, 1, 0.7f);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                f = resizedFile;

            } else {
                f = file.get(i).getFile();
            }
            if (i == file.size() - 1) {
                pics += PicUtils.postPic(f);
            } else {
                pics += PicUtils.postPic(f) + ",";
            }
            f.delete();
        }

        logger.info("==================图片上传成功，图片地址：" + pics + "===============================");
        SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");

        sheetInfo.setPics(pics);
        String stu = appser.saveShopReport(sheetInfo);
        String code = sheetInfo.getReportType().split("_")[0];

        if (stu != null && stu.equals("SUCCESS")) {
            logger.info("==================数据保存成功，返回值：" + stu + "，code=" + code + "==============================");
			/*if(!code.equals("01") && !code.equals("02")){
				redirect("/buyersApp/myInfo?codeReport="+code);
			}else{
				redirect("/buyersApp/myInfo?codeReport="+code+"&message=ERROR");*/
            logger.info("===========举报商家/商品结束，返回值：" + stu + "，code=" + code + "==============================");
            setAttr("message", "SUCCESS");
            renderJson();
            //}
        }


    }


    /**
     * 商品举报查询
     */
    public void goodsReportInfo() {

        SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");

        Record commodityInfo = appser.getCommodityInfo(sheetInfo.getArticleId());
        //举报商品
        sheetInfo.setCodeReport("01");
        List<Record> codeReportList = WorkerUtil.getcodeReportNo(sheetInfo.getCodeReport());

        setAttr("sheetInfo", sheetInfo);
        setAttr("commodityInfo", commodityInfo);//店铺商品信息
        setAttr("codeReportList", codeReportList);//二级类型信息
        render("goods_informant_center .html");
    }

    /**
     * 建议查询
     */
    public void suggestReportInfo() {

        SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");
        List<Record> codeReportList = WorkerUtil.getcodeReportNo(sheetInfo.getCodeReport());
        setAttr("codeReportList", codeReportList);//二级类型信息
        render("informant_suggest.html");
    }

    /**
     * 商品举报
     */
    public void goodsReport() {

        SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");
    }

    /**
     * 举报满集员工信息
     */
    public void manjiReportInfo() {

        SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");

        List<Record> codeReportList = WorkerUtil.getcodeReportNo(sheetInfo.getCodeReport());

        setAttr("sheetInfo", sheetInfo);
        setAttr("codeReportList", codeReportList);//二级类型信息

        render("");
    }

    /**
     * 举报满集员工
     */
    public void manjiReport() {

        String code = getPara("codeReport");
        //查询举报类型
        List<Record> codeReportList = WorkerUtil.getcodeReportNo(code);
        setAttr("codeReportList", codeReportList);//二级类型信息
        render("staff_informant_center.html");
    }

    /**
     * 举报详情
     */
	/*public void reportInfo(){
		SheetInfoParam sheetInfo=getBean(SheetInfoParam.class, "");
		List<Record> list=appser.reportInfo(sheetInfo);
		setAttr("reportInfo",list);
		render("");
	}*/

    /**
     * 纠纷查询
     */
    public void disputeInfo() {

        String articleId = "";
        SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");
        Record orderInfo = appser.getOrderInfo(getPara("order_no"));
        String type = "";
        String biaoshi = "";
        if (orderInfo != null) {
            //查询退单状态
            Record goodInfo = appser.getOrderGoodsInfo(getPara("order_no"));
            if (goodInfo != null) {
                if (orderInfo.get("status").toString().equals("1")) {//生成订单
                    if (orderInfo.get("payment_status").toString().equals("2")) {//已支付
                        if (goodInfo.get("express_status").toString().equals("2")) {//已发货
                            type = "待收货";
                            biaoshi = "1";
                        }
                    }
                } else if (orderInfo.get("status").toString().equals("2")) {//确认订单
                    if (orderInfo.get("payment_status").toString().equals("2")) {//已支付
                        if (goodInfo.get("express_status").toString().equals("2")) {//已发货
                            type = "待收货";
                            biaoshi = "1";
                        }
                    }
                } else if (orderInfo.get("status").toString().equals("3")) {//完成订单
                    if (orderInfo.get("payment_status").toString().equals("2")) {//已支付
						/*List<Record> listComment = appser.findComment(orderInfo.get("id").toString());
						if(listComment != null){
							if(listComment.size() > 0){
								type= "已完成";
								biaoshi = "1";
							}else{
								type= "待评价";
								biaoshi = "1";
							}
						}*/
                        type = "交易完成";
                        biaoshi = "1";
                    }
                }
            }
            orderInfo.set("type", type);
            setAttr("goodInfo", goodInfo);
        }
        sheetInfo.setCodeReport("06");
        List<Record> codeReportList = WorkerUtil.getcodeReportNo(sheetInfo.getCodeReport());
        setAttr("sheetInfo", sheetInfo);
        setAttr("orderInfo", orderInfo);//店铺订单信息
        setAttr("codeReportList", codeReportList);//二级类型信息

        render("trade_dispute_center.html");
    }

    /**
     * 交易纠纷
     */
    public void dispute() {

        SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");
    }

    /**
     * 建议详情查询
     */
    public void suggestInfo() {

        List<Record> flow = new ArrayList<>();
        String sheet_no = getPara("sheet_no");
        String codeReport = getPara("codeReport");
        //1.流程进度信息
        flow = appser.findFlow(sheet_no);
        //2.商家的信息
        Record shopInfo = appser.findShopInfo(sheet_no);
        //2.商品的信息
        Record goodInfo = appser.findGoodInfo(sheet_no);
        //3.工单信息
        Record sheet = appser.findSheet(sheet_no);
        //4.工单资料信息
        List<Record> info = appser.findInfo(sheet_no);
        //5.工单详情表
        Record detail = appser.findDetails(sheet_no);
        //5.订单详情表
        Record order = appser.findOrder(sheet_no);
        String type = "";
        String biaoshi = "";
        if (order != null) {
            //查询退单状态
            Record orderGoodInfo = appser.getOrderGoodsInfo(order.getStr("order_no").toString());
            if (orderGoodInfo != null) {
                if (order.get("status").toString().equals("1")) {//生成订单
                    if (order.get("payment_status").toString().equals("2")) {//已支付
                        if (orderGoodInfo.get("express_status").toString().equals("2")) {//已发货
                            type = "待收货";
                            biaoshi = "1";
                        }
                    }
                } else if (order.get("status").toString().equals("2")) {//确认订单
                    if (order.get("payment_status").toString().equals("2")) {//已支付
                        if (orderGoodInfo.get("express_status").toString().equals("2")) {//已发货
                            type = "待收货";
                            biaoshi = "1";
                        }
                    }
                } else if (order.get("status").toString().equals("3")) {//完成订单
                    if (order.get("payment_status").toString().equals("2")) {//已支付
						/*List<Record> listComment = appser.findComment(order.get("id").toString());
						if(listComment != null){
							if(listComment.size() > 0){
								type= "已完成";
								biaoshi = "1";
							}else{
								type= "待评价";
								biaoshi = "1";
							}
						}*/
                        type = "交易完成";
                        biaoshi = "1";
                    }
                }
                order.set("type", type);
            }

        }
        if (flow == null) {
            flow.get(0).set("descr", "暂无").set("opr_time", "暂无");

        }
        setAttr("flow", flow);
        setAttr("sheet", sheet);
        setAttr("shopInfo", shopInfo);
        setAttr("goodInfo", goodInfo);
        setAttr("info", info);
        setAttr("detail", detail);

        setAttr("order", order);
        String[] code = sheet.get("code").toString().split("_");
        if (code[0].equals("01")) {
            //举报商品
            render("informant_goods.html");
        } else if (code[0].equals("02")) {
            //举报商家
            render("informant_business.html");
        } else if (code[0].equals("03")) {
            //举报员工
            render("informant_staff.html");
        } else if (code[0].equals("05")) {
            //建议
            render("opinion_suggestion_info.html");
        } else if (code[0].equals("06")) {
            //交易纠纷
            render("trade_dispute.html");
        }
    }

    /**
     * 查询我的建议或者我的举报
     */
    public void myInfo() {

        Account account = getSessionAttr("Account");
        String codeReport = getPara("codeReport");
        String message = getPara("message");
        String sponsoId = account.getUser_id();

        logger.info("=========开始调用myInfo方法，codeReport=" + codeReport + "sponsoId=" + sponsoId + "============");

        List<Record> suggest = appser.reportInfo(codeReport, sponsoId);
        if (suggest != null && suggest.size() > 0) {
            for (int i = 0; i < suggest.size(); i++) {
                if (codeReport.equals("06") || codeReport.equals("07")) {
                    //查询该用户所有可发起纠纷的订单
                    //查新订单中的商品的信息
                    List<Record> goods = appser.findGoodsById(suggest.get(i).get("id").toString());
                    String pics = "";
                    String name = "";
                    String number = "";
                    for (int j = 0; j < goods.size(); j++) {
                        pics += goods.get(j).get("img_url") + ",";
                        name += goods.get(j).get("goods_title") + ",";
                        number += goods.get(j).get("quantity") + ",";
                    }
                    suggest.get(i).set("pics", pics);
                    suggest.get(i).set("name", name);
                    suggest.get(i).set("number", number);
                    suggest.get(i).set("order_no", goods.get(0).get("order_no"));
                } else if (!codeReport.equals("05")) {
                    Record shopOrGoodsInfo = appser.findShopOrGoodsInfo(suggest.get(i).get("id").toString(), suggest.get(i).get("type_code").toString());
                    if (shopOrGoodsInfo != null) {
                        suggest.get(i).set("pics", shopOrGoodsInfo.get("img_url"));
                    } else {
                        suggest.get(i).set("pics", "暂无");
                    }
                }
            }
        }
        //查询该用户所有的订单，状态包括：待收货、待评价、已完成
        //1.先查询该用户所有的已发货的订单，然后判断状态
        if (codeReport.equals("06") || codeReport.equals("07")) {

            List<Record> orderList = appser.findOrderList(sponsoId);
            for (int m = 0; m < orderList.size(); m++) {
                Record order = orderList.get(m);
                //先要判断订单是否已经发起过交易纠纷
                String type = "";
                String biaoshi = "0";
                if (order.get("status").toString().equals("1")) {//生成订单
                    if (order.get("payment_status").toString().equals("2")) {//已支付
                        if (order.get("express_status").toString().equals("2")) {//已发货
                            type = "待收货";
                            biaoshi = "1";
                        }
                    }
                } else if (order.get("status").toString().equals("2")) {//确认订单
                    if (order.get("payment_status").toString().equals("2")) {//已支付
                        if (order.get("express_status").toString().equals("2")) {//已发货
                            type = "待收货";
                            biaoshi = "1";
                        }
                    }
                } else if (order.get("status").toString().equals("3")) {//完成订单
                    if (order.get("payment_status").toString().equals("2")) {//已支付
						/*List<Record> listComment = appser.findComment(order.get("id").toString());
						if(listComment != null){
							if(listComment.size() > 0){
								type= "已完成";
								biaoshi = "1";
							}else{
								type= "待评价";
								biaoshi = "1";
							}
						}*/
                        type = "交易完成";
                        biaoshi = "1";
                    }
                }
                order.set("type", type);
                order.set("biaoshi", biaoshi);
                //查新订单中的商品的信息
                List<Record> goods = appser.findGoodByOrderId(order.get("id").toString());
                String pics = "";
                String name = "";
                String number = "";
                for (int j = 0; j < goods.size(); j++) {
                    pics += goods.get(j).get("img_url") + ",";
                    name += goods.get(j).get("goods_title") + ",";
                    number += goods.get(j).get("quantity") + ",";
                }
                order.set("pics", pics);
                order.set("name", name);
                order.set("number", number);
            }

            setAttr("order", orderList);
        }

        setAttr("list", suggest);

        logger.info("=========调用myInfo方法结束，codeReport=" + codeReport + "数据长度" + suggest.size() + "条============");

        if (codeReport.equals("05")) {
            render("my_suggest.html");
        } else if (codeReport.indexOf("01") != -1 || codeReport.indexOf("02") != -1 || codeReport.indexOf("03") != -1) {
            if (message != null && message.equals("ERROR")) {
                setAttr("message", message);
            }
            render("my_instruction.html");
        } else if (codeReport.equals("06")) {
            render("trade_dispute_list.html");
        }
    }

    /**
     * 根据单号修改单据状态
     */
    public void updateStatus() {

        int sheet_id = getParaToInt("sheet_id");
        int status = getParaToInt("status");

        String descr = getPara("descr", "");
        //System.out.println(descr+"====================");
        int a = appser.updateStatus(sheet_id, status, descr);
        if (a > 0) {
            //拿到举报的类型和发起人
            Record info = appser.findSheet(appser.findSheetById(sheet_id + "").get("sheet_no").toString());
            String code = info.getStr("type_code").split("_")[0];
            setAttr("code", code);
            setAttr("message", "SUCCESS");
            //redirect("/buyersApp/myInfo?codeReport="+code);
        } else {
            setAttr("message", "ERROR");
        }
        renderJson();
    }

    /**
     * 添加资料
     */
    @SuppressWarnings("static-access")
    public void add() {

        List<UploadFile> file = getFiles();
        String pics = "";
        File f = null;
        for (int i = 0; i < file.size(); i++) {
            //如果上传的图片大于2M,那么就压缩图片
            if (file.get(i).getFile().length() >= 1024 * 1024 * 2) {

                //方法一：先压缩图片，然后上传图片
				/*ImgCompressUtils imgCom;
				try {
					BufferedImage sourceImg =ImageIO.read(new FileInputStream(file.get(i).getFile()));  
					int width = sourceImg.getWidth();
					int height = sourceImg.getHeight();
					//System.out.println(width+" "+height);
					imgCom = new ImgCompressUtils(file.get(i).getFile());
					String path = imgCom.resizeFix(width/2, height/2);  
					f = new File(path);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
                //方法二：
                File originalFile = file.get(i).getFile();
                File resizedFile = new File("/manji/log/tomcat7/456.jpg");//tomcat_test测试，tomcat7 正式
                //File resizedFile = new File("C:/Users/Administrator/Desktop/456.jpg");//tomcat_test测试，tomcat7 正式
                try {
                    new zipPicsUtils().resize(originalFile, resizedFile, 1, 0.7f);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                f = resizedFile;
            } else {
                f = file.get(i).getFile();
            }
            if (i == file.size() - 1) {
                pics += PicUtils.postPic(f);
            } else {
                pics += PicUtils.postPic(f) + ",";
            }
            f.delete();
        }
        //System.out.println(pics);
        SheetInfoParam sheetInfo = new SheetInfoParam();
        long id = Long.parseLong(getPara("id"));
        String descr = getPara("descr");
        //添加资料

        Account account = getSessionAttr("Account");
        String user_name = Db.findFirst("select * from dt_users where id=?", account.getUser_id()).getStr("user_name");
        ;
        sheetInfo.setReportUser(user_name);
        sheetInfo.setReportUserId(account.getUser_id());
        sheetInfo.setReportUserType("Buyer");
        sheetInfo.setId(id);
        sheetInfo.setDescr(descr);
        sheetInfo.setPics(pics);
        String s = appser.add(sheetInfo);

        setAttr("message", s);
        renderJson();
        //redirect("/buyersApp/suggestInfo?sheet_no="+appser.findSheetById(sheetInfo.getId()+"").get("sheet_no").toString());
    }


    /**
     * 用户取消详情查询
     */
    public void cancleInfo() {
        render("");
    }

    /**
     * 用户取消
     */
    public void cancle() {
        SheetInfoParam sheetInfo = getBean(SheetInfoParam.class, "");
    }

    /**
     * 查询已经申请过的交易申请
     */
    public void checkRepeat() {

        String message = "";
        String orderId = getPara("orderId");
        String code = getPara("code");
        Record sheet = appser.checkRepeat(orderId, code);
        if (sheet != null) {
            if (sheet.get("sheet_no") != null && !"".equals(sheet.get("sheet_no"))) {
                message = "SUCCESS";
            } else {
                message = "ERROR";
            }
        } else {
            message = "ERROR";
        }
        setAttr("message", message);
        renderJson();
    }

    /**
     * 判断订单是否可以发起交易纠纷
     */
    public void check() {

        String message = "";
        String order_no = getPara("order_no");
        //查询该订单交易纠纷的次数
        List<Record> orderList = Db.find("select * from dt_sheets where id in(select sheet_id from dt_sheet_business where order_id=(select id from dt_orders where order_no=?)) and type_code like '06%';", order_no);
        //查询交易纠纷的类型有几种
        List<Record> codeList = Db.find("select * from dt_sheet_type where code like '06_%'");

        //作出判断
        if (orderList != null && codeList != null) {
            if (orderList.size() == codeList.size()) {
                message = "ERROR";
            } else {
                message = "SUCCESS";
            }
        }

        setAttr("message", message);
        renderJson();

    }

    /**
     * 补充资料
     */
    public void addData() {

        Account account = getSessionAttr("Account");
        Message msg = new Message();
		/*Account account = new Account();
		account.setNick_name("YCXY");
		account.setUser_id("2149985");
		account.setUser_role_type("Shop");*/
        String order_id = getPara("order_id");
        String descr = getPara("descr");
        String pics = getPara("pics");

        if (order_id == null || descr == null || pics == null || account == null) {
            msg.setStatus("0");
            msg.setMessage("ERROR");
            msg.setResult("参数不全,应传参数：sessionId、order_id、descr、pics");

            logger.info("参数不全。日志信息：" + JFinalJson.getJson().toJson(msg).toString());
        } else {
            boolean flag = appser.addData(account, order_id, descr, pics);

            if (flag) {
                msg.setStatus("1");
                msg.setMessage("SUCCESS");
                msg.setResult("添加资料成功");
            } else {
                msg.setStatus("0");
                msg.setMessage("ERROR");
                msg.setResult("添加资料失败");
            }
            logger.info("接口名称：addData,接口参数：user_id=" + account.getUser_id() + ",user_name=" + account.getNick_name() + "role_type=" + account.getUser_role_type() + "order_id=" + order_id + "descr=" + descr + "pics=" + pics + "日志信息：" + JFinalJson.getJson().toJson(msg).toString());
        }

        renderText(JFinalJson.getJson().toJson(msg).toString());
    }


}
