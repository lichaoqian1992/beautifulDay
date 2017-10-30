package com.manji.finance.withdrawals.Withdrawals;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.withdrawals.Account.UserAccountInfoRepository;
import com.manji.finance.withdrawals.Enums.OrderTypeEnums;
import com.manji.finance.withdrawals.Enums.UserRoleTypeEnums;
import com.manji.finance.withdrawals.Enums.UserconfirmwhetherEnums;
import com.manji.finance.withdrawals.Param.WithDrawalsParam;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsRepository;
import com.manji.finance.withdrawals.WithdrawalsDaily.WithdrawalsDailyRepository;


import java.util.ArrayList;
import java.util.List;


public class WithdrawalsService {

    //提现数据类
    WithdrawalsRepository withdrawalsRepository=new WithdrawalsRepository();


    //账户数据类
    UserAccountInfoRepository userAccountInfoRepository=new UserAccountInfoRepository();

    //审核数据类
    WithdrawalsDailyRepository withdrawalsDailyRepository=new WithdrawalsDailyRepository();

    /**
     * 查询异常提现(条件参数)
     * @return
     */
    public Page<Record> findabnormalWithdrawals(WithDrawalsParam param){
        param.setExceptions(1);
        Page<Record> Recordlist=withdrawalsRepository.findAllWithdrawals(param);
        //处理好数据用于前端展示
        for (int i=0;i<Recordlist.getList().size();i++){
            Recordlist.getList().get(i).set("index",i+1);
            //查询对应账户信息
            Record record=userAccountInfoRepository.findAccountInfoAll(Recordlist.getList().get(i).get("user_id").toString(),Recordlist.getList().get(i).getStr("user_role_type"));
            //账户是否被冻结标识
            if (record!=null){
                String state=record.get("state").toString();
                Recordlist.getList().get(i).set("state",state);
            }else{
                Recordlist.getList().get(i).set("state",1);
            }
            //查询对应User信息
            Record users=userAccountInfoRepository. findUsersAll(Recordlist.getList().get(i).get("user_id").toString());
            if (users!=null){
                Recordlist.getList().get(i).set("username",users.getStr("user_name"));
            }else{
                Recordlist.getList().get(i).set("username","");
            }
            //如果是商家查询店铺名称
            if (Recordlist.getList().get(i).getStr("user_role_type").equals("Shop")){
                Record shopinfo=findShopInfo(Recordlist.getList().get(i).get("user_id").toString());
                if (shopinfo==null){
                    Recordlist.getList().get(i).set("shop_name","");
                }else{
                    Recordlist.getList().get(i).set("shop_name",shopinfo.getStr("name"));
                }

            }else{
                Recordlist.getList().get(i).set("shop_name","");
            }

            //账户类型处理
            String user_role_type= UserRoleTypeEnums.getMsgByCode(Recordlist.getList().get(i).getStr("user_role_type"));
            Recordlist.getList().get(i).set("user_role_type",user_role_type);
        }
        return  Recordlist;
    }


    /**
     * 查询待审核页面(条件参数)
     * @return
     */
    public Page<Record> findexamineWithdrawals(WithDrawalsParam param){
        param.setExceptions(0);
        param.setStatus("0");
        param.setDetailed(0);
        Page<Record> Recordlist=withdrawalsRepository.findAllWithdrawalssh(param);
        //处理好数据用于前端展示
        for (int i=0;i<Recordlist.getList().size();i++){
            Recordlist.getList().get(i).set("index",i+1);
            //查询对应User信息
            Record users=userAccountInfoRepository. findUsersAll(Recordlist.getList().get(i).get("user_id").toString());
            if (users!=null){
                Recordlist.getList().get(i).set("username",users.getStr("user_name"));
            }else{
                Recordlist.getList().get(i).set("username","");
            }
            //如果是商家查询店铺名称
            if (Recordlist.getList().get(i).getStr("user_role_type").equals("Shop")){
                Record shopinfo=findShopInfo(Recordlist.getList().get(i).get("user_id").toString());
                if (shopinfo==null){
                    Recordlist.getList().get(i).set("shop_name","");
                }else{
                    Recordlist.getList().get(i).set("shop_name",shopinfo.getStr("name"));
                }

            }else{
                Recordlist.getList().get(i).set("shop_name","");
            }
            //账户类型处理
            String user_role_type= UserRoleTypeEnums.getMsgByCode(Recordlist.getList().get(i).getStr("user_role_type"));
            Recordlist.getList().get(i).set("user_role_type",user_role_type);
        }
        return  Recordlist;
    }


    /**
     * 查询提现确认页面(条件参数)
     * @return
     */
    public Page<Record> findensureWithdrawals(WithDrawalsParam param){
        param.setStatus("0");
        param.setExceptions(0);
        param.setDetailed(1);
        Page<Record> Recordlist=withdrawalsRepository.findAllWithdrawalssh(param);
        //处理好数据用于前端展示
        for (int i=0;i<Recordlist.getList().size();i++){
            Recordlist.getList().get(i).set("index",i+1);
            //查询对应User信息
            Record users=userAccountInfoRepository. findUsersAll(Recordlist.getList().get(i).get("user_id").toString());
            if (users!=null){
                Recordlist.getList().get(i).set("username",users.getStr("user_name"));
            }else{
                Recordlist.getList().get(i).set("username","");
            }
            //如果是商家查询店铺名称
            if (Recordlist.getList().get(i).getStr("user_role_type").equals("Shop")){
                Record shopinfo=findShopInfo(Recordlist.getList().get(i).get("user_id").toString());
                if (shopinfo==null){
                    Recordlist.getList().get(i).set("shop_name","");
                }else{
                    Recordlist.getList().get(i).set("shop_name",shopinfo.getStr("name"));
                }

            }else{
                Recordlist.getList().get(i).set("shop_name","");
            }
            //账户类型处理
            String user_role_type= UserRoleTypeEnums.getMsgByCode(Recordlist.getList().get(i).getStr("user_role_type"));
            Recordlist.getList().get(i).set("user_role_type",user_role_type);
        }
        return  Recordlist;
    }


    /**
     * 查询提现完成(条件参数)
     * @return
     */
    public Page<Record> findrecordWithdrawals(WithDrawalsParam param){
        param.setExceptions(0);
        param.setDetailed(1);
        Page<Record> Recordlist=withdrawalsRepository.findAllWithdrawalss(param);
        //处理好数据用于前端展示
        for (int i=0;i<Recordlist.getList().size();i++){
            Recordlist.getList().get(i).set("index",i+1);
            //查询对应User信息
            Record users=userAccountInfoRepository. findUsersAll(Recordlist.getList().get(i).get("user_id").toString());
            if (users!=null){
                Recordlist.getList().get(i).set("username",users.getStr("user_name"));
            }else{
                Recordlist.getList().get(i).set("username","");
            }
            //如果是商家查询店铺名称
            if (Recordlist.getList().get(i).getStr("user_role_type").equals("Shop")){
                Record shopinfo=findShopInfo(Recordlist.getList().get(i).get("user_id").toString());
                if (shopinfo==null){
                    Recordlist.getList().get(i).set("shop_name","");
                }else{
                    Recordlist.getList().get(i).set("shop_name",shopinfo.getStr("name"));
                }

            }else{
                Recordlist.getList().get(i).set("shop_name","");
            }
            //账户类型处理
            String user_role_type= UserRoleTypeEnums.getMsgByCode(Recordlist.getList().get(i).getStr("user_role_type"));
            Recordlist.getList().get(i).set("user_role_type",user_role_type);
        }
        return  Recordlist;
    }








    //通过id查询
    public Record getTUserWithdrawals(String id){
        Record record=withdrawalsRepository.getTUserWithdrawals(id);
        //查询对应账户信息
        Record userAccountInfo=userAccountInfoRepository.findAccountInfoAll(record.get("user_id").toString(),record.get("user_role_type").toString());
        //账户是否被冻结标识
        if (userAccountInfo!=null){
            String state=userAccountInfo.get("state").toString();
            record.set("state",state);
        }else{
            String state="1";
            record.set("state",state);
        }
        //查询对应User信息
        Record users=userAccountInfoRepository. findUsersAll(record.get("user_id").toString());
        if (users!=null){
            record.set("username",users.getStr("user_name"));
        }else{
            record.set("username","");
        }
        //账户类型处理
        String user_role_type= UserRoleTypeEnums.getMsgByCode(record.getStr("user_role_type"));
        record.set("user_role_type",user_role_type);
        record.set("dzje",record.getBigDecimal("total_money").doubleValue()+record.getBigDecimal("commission").doubleValue());
        return record;
    }

    //通过UserId查询
    public List<Record> getTUserWithdrawalsByuserId(String userId){

        List<Record> recordList=withdrawalsRepository.getTUserWithdrawalsByuserId(userId);

        return recordList;
    }

    /**
     * 查询用户详细信息(用于提现详情展示)
     * @param userId
     * @return
     */
    public Record findBuyerInfoDO(String userId){
        return withdrawalsRepository.findBuyerInfoDO(userId);
    }


    /**
     * 查询商家详细信息(用于提现详情展示)
     * @param userId
     * @return
     */
    public Record findShopInfo(String userId){
        return withdrawalsRepository.findShopInfo(userId);
    }

    /**
     * 通过userId查询此商家技术服务费
     */
    public Record findbusiness(String userId){
        return withdrawalsRepository.findbusiness(userId);
    }

    /**
     * 通过userId查询此用户的公司信息
     */
    public Record findCompanyinfo(String userId){
        return withdrawalsRepository.findCompanyinfo(userId);
    }

    /**
     * 通过userid查询此账户正在处理提现金额
     */
    public Double findwithdrawalsCL(String userId){
        return withdrawalsRepository.findwithdrawalsCL(userId);
    }

    /**
     * 查询银行详细信息
     */
    public Record findBankinfo(String userId){
        return withdrawalsRepository.findBankinfo(userId);
    }

    /**
     * 查看资质证件
     */
    public  List<Record> findShopinfoOther(String userId){
        return withdrawalsRepository.findShopinfoOther(userId);
    }


    /**
     * 通过userid查询此账户冻结提现金额
     */
    public Double findwithdrawalsDJ(String userId){
        return withdrawalsRepository.findwithdrawalsDJ(userId);
    }

    /**
     * 通过userid查询此账户提现在途
     */
    public Double findwithdrawalsZT(String userId){
        return withdrawalsRepository.findwithdrawalsZT(userId);
    }


    /**
     * 通过id查询此账户余额
     */
    public Double getAccountinfoAmountSumByid(String id){
        return withdrawalsRepository.getAccountinfoAmountSumByid(id);
    }


    /**
     * 通过id查询此账户可提现金额
     */
    public Double getAllowAmountinfoAmountSumByid(String id){
        return withdrawalsRepository.getAllowAmountinfoAmountSumByid(id);
    }


    /**
     * 通过userId查询金额变动
     */
    public Page<Record> findUserAccountLogDOByUserId(String userId,String userType,int page,String startTime,String endTime,String wstatus){
        Page<Record> pageRecord=withdrawalsRepository.findUserAccountLogDOByUserId(userId,userType,page,startTime,endTime,wstatus);
        for (int i=0;i<pageRecord.getList().size();i++){
            Record record=pageRecord.getList().get(i);
            //处理存入对方账户id
            switch(record.getStr("type")){

                //转账
                case "Transaction":
                    Record trecord=withdrawalsRepository.getOutuser(record.get("user_id").toString(),record.getStr("order_no"));
                    if (trecord==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        pageRecord.getList().get(i).set("order_user_id",trecord.get("touser_id").toString());
                    }
                    break;

                //转出
                case "TransferOut":
                    Record toutrecord=withdrawalsRepository.getOutuser(record.get("user_id").toString(),record.getStr("order_no"));
                    if (toutrecord==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        pageRecord.getList().get(i).set("order_user_id",toutrecord.get("touser_id").toString());
                    }
                    break;

                //转入
                case "TransferIn":
                    Record tinrecord=withdrawalsRepository.getInuser(record.get("user_id").toString(),record.getStr("order_no"));
                    if (tinrecord==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        pageRecord.getList().get(i).set("order_user_id",tinrecord.get("user_id").toString());
                    }
                    break;

                //订单退款
                case "OrderBack":
                    //获取退回订单
                    //获取订单
                    Record OrderBack=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
                    if (OrderBack==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        //如果退款金额大于0说明此条记录是用户记录
                        if (record.getBigDecimal("val").doubleValue()>0){
                            pageRecord.getList().get(i).set("order_user_id",OrderBack.get("user_id"));
                            //如果退款金额大于0说明此条记录是商家记录
                        }else{
                            pageRecord.getList().get(i).set("order_user_id",OrderBack.get("shop_user_id"));
                        }
                    }
                    break;

                //购买商品
                case "BuyGoods":
                    //获取订单
                    Record orders=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
                    if (orders==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        //判断用户id
                        if (record.get("user_id").toString().equals(orders.get("user_id").toString())){
                            pageRecord.getList().get(i).set("order_user_id",orders.get("shop_user_id"));
                        }else{
                            pageRecord.getList().get(i).set("order_user_id",orders.get("user_id"));
                        }
                    }
                    break;

                    //订单预结算
                    case "OrderPreSettlement":
                        //获取订单
                        Record OrderPreSettlement=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
                        if (OrderPreSettlement==null){
                            pageRecord.getList().get(i).set("order_user_id","-1");
                        }else{
                            //判断用户id
                            if (record.get("user_id").toString().equals(OrderPreSettlement.get("user_id").toString())){
                                pageRecord.getList().get(i).set("order_user_id",OrderPreSettlement.get("shop_user_id"));
                            }else{
                                pageRecord.getList().get(i).set("order_user_id",OrderPreSettlement.get("user_id"));
                            }
                        }

                        break;

                    //订单结算
                    case "OrderSettlement":
                        //获取订单
                        Record orderSettlement=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
                        if (orderSettlement==null){
                            pageRecord.getList().get(i).set("order_user_id","-1");
                        }else{
                            //判断用户id
                            if (record.get("user_id").toString().equals(orderSettlement.get("user_id").toString())){
                                pageRecord.getList().get(i).set("order_user_id",orderSettlement.get("shop_user_id"));
                            }else{
                                pageRecord.getList().get(i).set("order_user_id",orderSettlement.get("user_id"));
                            }
                        }

                    break;


                default:
                    pageRecord.getList().get(i).set("order_user_id","-1");
                    break;
            }

            //处理订单类型
            String type= OrderTypeEnums.getMsgByCode(record.getStr("type"));
            pageRecord.getList().get(i).set("type",type);
        }
        return pageRecord;
    }




    /**
     * 通过userId查询金额变动
     */
    public Page<Record> findUserVoucherLogDOByUserId(String userId,int page,String startTime,String endTime){
        Page<Record> pageRecord=withdrawalsRepository.findUserVoucherLogDOByUserId(userId,page,startTime,endTime);
        for (int i=0;i<pageRecord.getList().size();i++){
            Record record=pageRecord.getList().get(i);
            //处理存入对方账户id
            switch(record.getStr("type")){

                //转账
                case "Transaction":
                    Record trecord=withdrawalsRepository.getOutuser(record.get("user_id").toString(),record.getStr("order_no"));
                    if (trecord==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        pageRecord.getList().get(i).set("order_user_id",trecord.get("touser_id").toString());
                    }
                    break;

                //转出
                case "TransferOut":
                    Record toutrecord=withdrawalsRepository.getOutuser(record.get("user_id").toString(),record.getStr("order_no"));
                    if (toutrecord==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        pageRecord.getList().get(i).set("order_user_id",toutrecord.get("touser_id").toString());
                    }
                    break;

                //转入
                case "TransferIn":
                    Record tinrecord=withdrawalsRepository.getInuser(record.get("user_id").toString(),record.getStr("order_no"));
                    if (tinrecord==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        pageRecord.getList().get(i).set("order_user_id",tinrecord.get("user_id").toString());
                    }
                    break;

                //订单退款
                case "OrderBack":
                    //获取退回订单
                    //获取订单
                    Record OrderBack=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
                    if (OrderBack==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        //如果退款金额大于0说明此条记录是用户记录
                        if (record.getBigDecimal("val").doubleValue()>0){
                            pageRecord.getList().get(i).set("order_user_id",OrderBack.get("user_id"));
                            //如果退款金额大于0说明此条记录是商家记录
                        }else{
                            pageRecord.getList().get(i).set("order_user_id",OrderBack.get("shop_user_id"));
                        }
                    }
                    break;

                //购买商品
                case "BuyGoods":
                    //获取订单
                    Record orders=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
                    if (orders==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        //判断用户id
                        if (record.get("user_id").toString().equals(orders.get("user_id").toString())){
                            pageRecord.getList().get(i).set("order_user_id",orders.get("shop_user_id"));
                        }else{
                            pageRecord.getList().get(i).set("order_user_id",orders.get("user_id"));
                        }
                    }
                    break;

                //订单预结算
                case "OrderPreSettlement":
                    //获取订单
                    Record OrderPreSettlement=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
                    if (OrderPreSettlement==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        //判断用户id
                        if (record.get("user_id").toString().equals(OrderPreSettlement.get("user_id").toString())){
                            pageRecord.getList().get(i).set("order_user_id",OrderPreSettlement.get("shop_user_id"));
                        }else{
                            pageRecord.getList().get(i).set("order_user_id",OrderPreSettlement.get("user_id"));
                        }
                    }

                    break;

                //订单结算
                case "OrderSettlement":
                    //获取订单
                    Record orderSettlement=withdrawalsRepository.getOrdersByorderno(record.getStr("order_no"));
                    if (orderSettlement==null){
                        pageRecord.getList().get(i).set("order_user_id","-1");
                    }else{
                        //判断用户id
                        if (record.get("user_id").toString().equals(orderSettlement.get("user_id").toString())){
                            pageRecord.getList().get(i).set("order_user_id",orderSettlement.get("shop_user_id"));
                        }else{
                            pageRecord.getList().get(i).set("order_user_id",orderSettlement.get("user_id"));
                        }
                    }

                    break;


                default:
                    pageRecord.getList().get(i).set("order_user_id","-1");
                    break;
            }

            //处理订单类型
            String type= OrderTypeEnums.getMsgByCode(record.getStr("type"));
            pageRecord.getList().get(i).set("type",type);
        }
        return pageRecord;
    }


    /**
     * 通过提现单号查询提现操作日志表
     * @param witdrawalsNo
     * @return
     */
    public List<Record> findWithdrawalslog(String  witdrawalsNo) {
        return withdrawalsRepository.findWithdrawalslog(witdrawalsNo);
    }

    /**
     * 批量修改异常审核状态
     * @param id
     * @return
     */
    public String  updateToexamine(String id){
        int i=withdrawalsRepository.updateToexamine(id);
        if (i>0){
            return "success";
        }else{
            return "err";
        }

    }



    /**
     * 修改提现状态
     * @param id
     * @param statuts
     */
    public void updateStats(String id,String statuts,String remark){
        withdrawalsRepository.updateStats(id,statuts,remark);
    }



    /**
     * 查询对应权限待审核总金额
     * @return
     */
    public List<Double> getSumMony() {
        WithDrawalsParam param = new WithDrawalsParam();
        List<Double> lid = new ArrayList<Double>();
        param.setStatus("0");
        param.setDetailed(0);
        param.setRole("8");
        lid.add(withdrawalsRepository.getSumMony(param));
        param.setRole("4");
        lid.add(withdrawalsRepository.getSumMony(param));
        param.setRole("2");
        lid.add(withdrawalsRepository.getSumMony(param));
        param.setRole("3");
        lid.add(withdrawalsRepository.getSumMony(param));
        return lid;
    }


    /**
     * 查询当日(对应权限)已审核订单条数
     * @return
     */
    public List<Long> getWithdrawalsDailyCount(){
        List<Long> list = new ArrayList<Long>();
        list.add(withdrawalsDailyRepository.getWithdrawalsDailyCount(8));
        list.add(withdrawalsDailyRepository.getWithdrawalsDailyCount(4));
        list.add(withdrawalsDailyRepository.getWithdrawalsDailyCount(2));
        list.add(withdrawalsDailyRepository.getWithdrawalsDailyCount(3));
        return list;
    }

    /**
     * 对应权限审核员通过
     */
    public void updatedetailed(String id){
        withdrawalsRepository.updatedetailed(id);
    }

    /**
     * 通过id查询(把此订单修改成异常订单)
     */
    public void updateExceptions(String id){
        withdrawalsRepository.updateExceptions(id);
    }

    /**
     * 正常订单已生成清单
     */
    public void updateStatsAnddetailed(String id,String statuts,String remark){withdrawalsRepository.updateStatsAnddetailed(id,statuts,remark);}

    /**
     * 修改出纳确认(完成,失败)状态
     */
    public void updateConfirmwhether(String id,String confirmwhether){
        withdrawalsRepository.updateConfirmwhether(id,confirmwhether);
    }

    /**
     * 查询所有提现(EXCL)
     * @return
     */
    public List<Record> getAllWithdrawals(WithDrawalsParam param) {
        param.setExceptions(0);
        param.setDetailed(1);
        List<Record> list=withdrawalsRepository.getAllWithdrawals(param);
        List<Record> ll=new ArrayList<Record>();
        for (int i=0;i<list.size();i++){
            //查询对应User信息
            Record users=userAccountInfoRepository. findUsersAll(list.get(i).get("user_id").toString());
            if (users!=null){
                list.get(i).set("username",users.getStr("user_name"));
            }else{
                list.get(i).set("username","");
            }
            //账户类型处理
            String user_role_type= UserRoleTypeEnums.getMsgByCode(list.get(i).getStr("user_role_type"));
            list.get(i).set("user_role_type",user_role_type);
            //如果是商家查询店铺名
            Record shopInfo = findShopInfo(list.get(i).get("user_id").toString());
            if (shopInfo==null){
                list.get(i).set("shopInfoname", users.getStr("user_name").toString());

                list.get(i).set("area","");
            }else{
                list.get(i).set("shopInfoname", shopInfo.getStr("name"));
                if (shopInfo.getStr("shopmoble")==null){
                    if (users.getStr("mobile")==null){
                        list.get(i).set("shopmoble","");
                    }else{
                        list.get(i).set("shopmoble",users.getStr("mobile").toString());
                    }

                }else{
                    list.get(i).set("shopmoble",shopInfo.getStr("shopmoble"));
                }

                if (shopInfo.getStr("TAG")==null){
                    list.get(i).set("TAG","");
                }else{
                    list.get(i).set("TAG",shopInfo.getStr("TAG"));
                }
                list.get(i).set("area",shopInfo.getStr("area"));
            }
            //提现状态
            String confirmwhether= UserconfirmwhetherEnums.getMsgByCode(list.get(i).get("confirmwhether").toString());
            //查询账户余额
            Record zhyy=withdrawalsRepository.findzhyy(list.get(i).get("user_id").toString(),list.get(i).getStr("withdrawals_no"));
            if (zhyy!=null){
                list.get(i).set("zhye",zhyy.getBigDecimal("new_value").doubleValue());
            }else{
                list.get(i).set("zhye","");
            }

            list.get(i).set("confirmwhether", confirmwhether);
            if (list.get(i).getStr("user_role_type").toString().equals("商家")){
                List<Record> ordersList=findOrdersByWtime(list.get(i).get("user_id").toString(),list.get(i).getStr("withdrawals_no"));

                if (ordersList.size()==0){
                    list.get(i).set("dzje",list.get(i).getBigDecimal("total_money").doubleValue()-list.get(i).getBigDecimal("commission").doubleValue());
                    ll.add(list.get(i));
                }else{
                    for (int j=0;j<ordersList.size();j++){
                        //查询订单详细信息
                        Record orderinfo=withdrawalsRepository.findOrderinfo(ordersList.get(j).getStr("order_no"));
                        if (j==0){
                            list.get(i).set("orders_no",ordersList.get(j).getStr("order_no"));
                            if (orderinfo==null){
                                list.get(i).set("complete_time","");
                                list.get(i).set("real_amount","");
                                list.get(i).set("user_percentage","");
                                list.get(i).set("sxf","");
                                list.get(i).set("balance_amount","");
                                list.get(i).set("ordervoucher","");

                            }else{
                                list.get(i).set("complete_time",orderinfo.getDate("complete_time"));
                                list.get(i).set("real_amount",(orderinfo.getBigDecimal("real_amount").doubleValue()+orderinfo.getBigDecimal("ordervoucher").doubleValue()));
                                if (orderinfo.getBigDecimal("user_percentage")==null){
                                    list.get(i).set("user_percentage","");
                                }else{
                                    list.get(i).set("user_percentage",orderinfo.getBigDecimal("user_percentage").doubleValue()*100);
                                }
                                if (orderinfo.getBigDecimal("sxf")==null){
                                    list.get(i).set("sxf","");
                                }else{
                                    list.get(i).set("sxf",orderinfo.getBigDecimal("sxf").doubleValue());
                                }

                                if (orderinfo.getBigDecimal("balance_amount")==null) {
                                    list.get(i).set("balance_amount","");
                                }else{
                                    list.get(i).set("balance_amount", orderinfo.getBigDecimal("balance_amount").doubleValue());
                                }

                                if (orderinfo.getBigDecimal("ordervoucher")==null) {
                                    list.get(i).set("ordervoucher","");
                                }else{
                                    list.get(i).set("ordervoucher", orderinfo.getBigDecimal("ordervoucher").doubleValue());
                                }

                                if (orderinfo.getBigDecimal("balance_amount")==null||orderinfo.getBigDecimal("ordervoucher")==null) {
                                    list.get(i).set("zhje","");
                                }else{
                                    list.get(i).set("zhje",orderinfo.getBigDecimal("balance_amount").doubleValue()+orderinfo.getBigDecimal("ordervoucher").doubleValue());
                                }


                            }
                            list.get(i).set("dzje",list.get(i).getBigDecimal("total_money").doubleValue()-list.get(i).getBigDecimal("commission").doubleValue());
                            ll.add(list.get(i));
                        }else{
                            Record order=new Record();
                            order.set("shopInfoname",list.get(i).getStr("shopInfoname"));
                            order.set("shopmoble",list.get(i).get("shopmoble").toString());
                            order.set("TAG",list.get(i).get("TAG").toString());
                            order.set("area",list.get(i).get("area").toString());
                            order.set("withdrawals_no",list.get(i).get("withdrawals_no").toString());
                            order.set("user_role_type",list.get(i).get("user_role_type").toString());
                            order.set("username",list.get(i).get("username").toString());
                            order.set("bank_name",list.get(i).get("bank_name").toString());
                            order.set("bank_card",list.get(i).get("bank_card").toString());
                            order.set("bank_address",list.get(i).get("bank_address").toString());
                            order.set("amount",list.get(i).get("amount").toString());
                            order.set("commission",list.get(i).get("commission").toString());
                            order.set("voucher",list.get(i).get("voucher").toString());
                            order.set("total_money",list.get(i).get("total_money").toString());
                            order.set("add_time",list.get(i).get("add_time").toString());
                            order.set("confirmwhether",list.get(i).get("confirmwhether").toString());
                            order.set("bank_user",list.get(i).get("bank_user").toString());
                            order.set("orders_no",ordersList.get(j).getStr("order_no"));
                            order.set("zhye",list.get(i).getDouble("zhye"));

                            if (orderinfo==null){
                                order.set("complete_time","");
                                order.set("real_amount","");
                                order.set("user_percentage","");
                                order.set("sxf","");
                                order.set("balance_amount","");
                                order.set("ordervoucher","");
                                order.set("zhje","");
                            }else{
                                order.set("complete_time",orderinfo.getDate("complete_time"));
                                order.set("real_amount",(orderinfo.getBigDecimal("real_amount").doubleValue()+orderinfo.getBigDecimal("ordervoucher").doubleValue()));
                                if (orderinfo.getBigDecimal("user_percentage")==null){
                                    order.set("user_percentage","");
                                }else{
                                    order.set("user_percentage",orderinfo.getBigDecimal("user_percentage").doubleValue()*100);
                                }
                                if (orderinfo.getBigDecimal("sxf")==null){
                                    order.set("sxf","");
                                }else{
                                    order.set("sxf",orderinfo.getBigDecimal("sxf").doubleValue());
                                }
                                if (orderinfo.getBigDecimal("balance_amount")==null) {
                                    order.set("balance_amount","");
                                }else{
                                    order.set("balance_amount", orderinfo.getBigDecimal("balance_amount").doubleValue());
                                }

                                if (orderinfo.getBigDecimal("ordervoucher")==null) {
                                    order.set("ordervoucher","");
                                }else{
                                    order.set("ordervoucher", orderinfo.getBigDecimal("ordervoucher").doubleValue());
                                }

                                if (orderinfo.getBigDecimal("balance_amount")==null||orderinfo.getBigDecimal("ordervoucher")==null) {
                                    order.set("zhje","");
                                }else{
                                    order.set("zhje",orderinfo.getBigDecimal("balance_amount").doubleValue()+orderinfo.getBigDecimal("ordervoucher").doubleValue());
                                }
                            }
                            order.set("dzje",list.get(i).getBigDecimal("total_money").doubleValue()-list.get(i).getBigDecimal("commission").doubleValue());
                            ll.add(order);
                        }
                    }
                }
            }else{
                list.get(i).set("orders_no","");
                list.get(i).set("complete_time","");
                list.get(i).set("real_amount","");
                list.get(i).set("user_percentage","");
                list.get(i).set("sxf","");
                list.get(i).set("balance_amount","");
                list.get(i).set("ordervoucher","");
                list.get(i).set("zhje","");
                list.get(i).set("dzje",list.get(i).getBigDecimal("total_money").doubleValue()-list.get(i).getBigDecimal("commission").doubleValue());
                ll.add(list.get(i));
            }
        }
        return ll;
    }


    /**
     * 账户
     */
    public Record userSelect(int userId) {
        Record userSelect=withdrawalsRepository.userSelect(userId);
        return userSelect;
    }
    /**
     * 账户数据
     */
    public List<Record> accountSelect(int userId) {
        List<Record> acSelect=withdrawalsRepository.accountSelect(userId);
        //账户类型处理
        for (int i=0;i<acSelect.size();i++){
            String user_role_type= UserRoleTypeEnums.getMsgByCode(acSelect.get(i).getStr("role_type"));
            acSelect.get(i).set("role_type",user_role_type);
        }
        return acSelect;
    }
    /**
     * 在线充值数据
     */
    public  Page<Record> recSelect(int pageNumber, int pageSize,int userId,String resNO) {
        Page<Record> recSelect=withdrawalsRepository.accountSelect(pageNumber,pageSize,userId,resNO);
        for (int i=0;i<recSelect.getList().size();i++){
            String user_role_type= UserRoleTypeEnums.getMsgByCode(recSelect.getList().get(i).getStr("ROLE_TYPE"));
            recSelect.getList().get(i).set("ROLE_TYPE",user_role_type);
        }
        return recSelect;
    }

    /**
     * 提现数据
     */
    public  Page<Record> widSelect(int pageNumber, int pageSize,int userId,String widNO) {
        Page<Record> widSelect=withdrawalsRepository.widSelect(pageNumber,pageSize,userId,widNO);
        for (int i=0;i<widSelect.getList().size();i++){
            String user_role_type= UserRoleTypeEnums.getMsgByCode(widSelect.getList().get(i).getStr("user_role_type"));
            widSelect.getList().get(i).set("user_role_type",user_role_type);
        }
        return widSelect;
    }

    public Record findconfig(String musum){
       return withdrawalsRepository.findconfig(musum);
    }

    /**
     * 通过本次提现单和上次提现单查询出中间全部已结算订单
     * @return
     */
    public List<Record> findOrdersByWtime(String user_id,String w_no){
        //查询本次提现单时间
        Record Brecord=withdrawalsRepository.findBW(user_id,w_no);
        if (Brecord==null){
            return withdrawalsRepository.findOrderByWtime(user_id,Brecord.get("add_time").toString(),null);
        }
        //查询上次提现单时间
        Record Srecord=withdrawalsRepository.findJW(user_id,w_no);
        if (Srecord==null){
            return withdrawalsRepository.findOrderByWtime(user_id,null,Brecord.get("add_time").toString());
        }
        return withdrawalsRepository.findOrderByWtime(user_id,Srecord.get("add_time").toString(),Brecord.get("add_time").toString());
    }

    /**
     *查询手续费状态
     */
    public Record findsxfStatic(String userid,String roletype){
        return withdrawalsRepository.findsxfStatic(userid,roletype);
    }

    /**
     * 用户主动撤销提现
     */
    public int revocationNotification(String withdrawals_no){
        return withdrawalsRepository.revocationNotification(withdrawals_no);
    }
}
