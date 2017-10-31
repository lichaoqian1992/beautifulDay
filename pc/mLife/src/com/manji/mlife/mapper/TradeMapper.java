package com.manji.mlife.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manji.mlife.Vo.QueryVo;
import com.manji.mlife.model.Trade;

public interface TradeMapper {
	
    int deleteByPrimaryKey(String outerid);

    int insert(Trade record);

    int insertSelective(Trade record);

    Trade selectByPrimaryKey(String outerid);
    
    Trade selectByPrimaryKeyTradeNo(String tradeNo);

    int updateByPrimaryKeySelective(Trade record);

    int updateByPrimaryKey(Trade record);
    
    List<Trade> queryChargeBills(@Param("vo")QueryVo vo,  @Param("begin")int begin,  @Param("limit")int limit);
    
    List<Trade> queryTrafficBills(@Param("vo")QueryVo vo , @Param("begin")int begin , @Param("limit")int limit);
    //充值订单总记录数
    int findCountCid(@Param("vo")QueryVo vo);
    //充值订单详情
	List<Trade> queryChargeBillsShowDetail(@Param("vo")QueryVo vo);
	//票务订单总订单数
	int findCountCid2(@Param("vo")QueryVo vo);//@Param("vo")表示往后台传的参数
	 List<Trade> selectByPrimaryKey2(String outerid);

}