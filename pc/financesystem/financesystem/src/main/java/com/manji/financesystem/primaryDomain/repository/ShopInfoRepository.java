package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.enums.OrderStatusEnums;
import com.manji.financesystem.enums.PayStatusEnums;
import com.manji.financesystem.enums.SettleMentStatusEnums;
import com.manji.financesystem.enums.UserRoleTypeEnums;
import com.manji.financesystem.primaryDomain.entiity.ShopInfoDO;
import com.manji.financesystem.primaryDomain.entiity.extra.OrderAndShopInfoDO;
import com.manji.financesystem.requestParam.ShopInfoParam;
import com.manji.financesystem.requestParam.WithDrawalsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/24.
 */
@Repository
public class ShopInfoRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询商家详细信息
     * @param param
     * @return
     */
    public List<ShopInfoDO> getShopInfo(ShopInfoParam param){
        final List<ShopInfoDO> list = new ArrayList<ShopInfoDO>();
        StringBuffer sql = new StringBuffer("select * from(select a.* ,b.card_type,b.card_number,b.card_pics,b.legal_person,b.legal_person_idcard,b.legal_person_mobile,ROW_NUMBER() over(order by a.ID)as 'userID' from dt_user_role_shopinfo a inner join dt_user_companyinfo b on a.user_id = b.user_id");
        StringBuffer sql2 = new StringBuffer(")as c where 1=1");
        if(param != null){
            if(!StringUtils.isEmpty(param.getShopName())){
                sql.append(" and a.name like '%"+param.getShopName()+"%'");
            }
            if(!StringUtils.isEmpty(param.getPageNum())){
                sql2.append(" and c.userID between ((20*"+(param.getPageNum()-1)+")+1)"+" and (20*"+param.getPageNum()+")");
            }
            sql.append(sql2);
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                        @Override
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            ShopInfoDO shopInfoDO = new ShopInfoDO();
                            shopInfoDO.setUserId(rs.getString("user_id"));
                            shopInfoDO.setUserName(rs.getString("name"));
                            shopInfoDO.setContent(rs.getString("content"));
                            shopInfoDO.setPics(rs.getString("pics"));
                            shopInfoDO.setTag(rs.getString("tag"));
                            shopInfoDO.setPcLogo(rs.getString("pc_logo"));
                            shopInfoDO.setMobile(rs.getString("mobile"));
                            shopInfoDO.setAddress(rs.getString("address"));
                            shopInfoDO.setArea(rs.getString("area"));
                            shopInfoDO.setTelephone(rs.getString("telephone"));
                            shopInfoDO.setCardType(rs.getString("card_type"));
                            shopInfoDO.setCardNum(rs.getString("card_number"));
                            shopInfoDO.setCardPic(rs.getString("card_pics"));
                            shopInfoDO.setLegalPerson(rs.getString("legal_person"));
                            shopInfoDO.setLegalTel(rs.getString("legal_person_mobile"));
                            shopInfoDO.setLegalIdCard(rs.getString("legal_person_idcard"));
                            list.add(shopInfoDO);
                            return list;
                        }
                    }
            );
        }
        return list;
    }

    /**
     * 查询总数据
     * @param param
     * @return
     */
    public ShopInfoDO getCount(ShopInfoParam param){
        final ShopInfoDO shopInfoDO = new ShopInfoDO();
        if(param != null){
            StringBuffer sql = new StringBuffer("select count(*) count from dt_user_role_shopinfo a inner join dt_user_companyinfo b on a.user_id = b.user_id");
            if(!StringUtils.isEmpty(param.getShopName())){
                sql.append(" and a.name like '%"+param.getShopName()+"%'");
            }
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                        @Override
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            shopInfoDO.setCountNum(rs.getInt("count"));
                            return shopInfoDO;
                        }
                    }
            );
        }
        return shopInfoDO;
    }

    /**
     * 查询商家订单信息和商家基本信息
     * @param param
     * @return
     */
    public List<OrderAndShopInfoDO> getShopOrderInfo(ShopInfoParam param){
        final List<OrderAndShopInfoDO> list = new ArrayList<OrderAndShopInfoDO>();
        if(param != null){
            StringBuffer sql1 = new StringBuffer("select * from(select a.name,a.area,a.mobile,a.percentage,b.* ,ROW_NUMBER() over(order by b.id)as 'userID' from dt_user_role_shopinfo a inner join dt_orders b on a.user_id=b.shop_user_id where 1=1");
            StringBuffer sql2 = new StringBuffer(")as c where 1=1");
            if(!StringUtils.isEmpty(param.getShopName())){
                sql1.append(" and a.name like '%"+param.getShopName()+"%'");
            }
            if(!StringUtils.isEmpty(param.getOrderStatus())){
                sql1.append(" and b.status="+param.getOrderStatus());
            }
            if(!StringUtils.isEmpty(param.getPageNum())){
                sql2.append(" and c.userID between ((20*"+(param.getPageNum()-1)+")+1)"+" and (20*"+param.getPageNum()+")");
            }
            sql1.append(sql2);
            jdbcTemplate.query(sql1.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    OrderAndShopInfoDO odo = new OrderAndShopInfoDO();
                    odo.setUserId(rs.getString("shop_user_id"));
                    odo.setUserName(rs.getString("name"));
                    odo.setMobile(rs.getString("mobile"));
                    odo.setArea(rs.getString("area"));
                    odo.setPercentage(rs.getDouble("percentage"));
                    odo.setOrderNo(rs.getString("order_no"));
                    odo.setOrderType(rs.getString("order_type"));
                    odo.setOrderTitle(rs.getString("order_title"));
                    odo.setOrderAmount(rs.getDouble("order_amount"));
                    odo.setPayableAmount(rs.getDouble("payable_amount"));
                    odo.setRealAmount(rs.getDouble("real_amount"));
                    odo.setStatus(OrderStatusEnums.getMsgByCode(rs.getString("status")));
                    odo.setPaymentStatus(PayStatusEnums.getMsgByCode(rs.getString("payment_status")));
                    odo.setSettlementStatus(SettleMentStatusEnums.getMsgByCode(rs.getString("settlement_status")));
                    odo.setSettlementTime(rs.getString("settlement_time"));
                    list.add(odo);
                    return list;
                }
            });
        }
        return list;
    }

    /**
     * 查询总数据
     * @param param
     * @return
     */
    public OrderAndShopInfoDO getOrderCount(ShopInfoParam param){
        final OrderAndShopInfoDO odo = new OrderAndShopInfoDO();
        if(param != null){
            StringBuffer sql = new StringBuffer("select count(*) count from dt_user_role_shopinfo a inner join dt_orders b on a.user_id=b.shop_user_id where 1=1");
            if(!StringUtils.isEmpty(param.getShopName())){
                sql.append(" and a.name like '%"+param.getShopName()+"%'");
            }
            if(!StringUtils.isEmpty(param.getOrderStatus())){
                sql.append(" and b.status="+param.getOrderStatus());
            }
            jdbcTemplate.query(sql.toString(), new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    odo.setCountNum(rs.getInt("count"));
                    return odo;
                }
            });
        }
        return odo;
    }

    /**
     * 导出EXcle
     * @param param
     * @return
     */
    public List<Map<String,Object>> getOrderDetails3(ShopInfoParam param){
        StringBuffer sql1 = new StringBuffer("select * from(select a.name,a.area,a.mobile,a.percentage,b.* ,ROW_NUMBER() over(order by b.id)as 'userID' from dt_user_role_shopinfo a inner join dt_orders b on a.user_id=b.shop_user_id where 1=1");
        StringBuffer sql2 = new StringBuffer(")as c where 1=1");
        if(param != null){
            if(!StringUtils.isEmpty(param.getShopName())){
                sql1.append(" and a.name like '%"+param.getShopName()+"%'");
            }
            if(!StringUtils.isEmpty(param.getOrderStatus())){
                sql1.append(" and b.status="+param.getOrderStatus());
            }
        }
        sql1.append(sql2);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql1.toString());
        return maps;
    }

    public int getShopCount(){
        final int[] countNum = new int[1];
        jdbcTemplate.query("select count(*) shopNum from dt_user_accountinfo where role_type='shop'", new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                countNum[0] = rs.getInt("shopNum");
                return countNum[0];
            }
        });

        return countNum[0];
    }
}
