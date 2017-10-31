package com.manji.financesystem.primaryDomain.repository;

/**
 * 每个行业的经营情况
 * Created by Administrator on 2017/1/19.
 */

import com.manji.financesystem.primaryDomain.entiity.EveryIndustryManagementSituationDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EveryIndustryManagementSituationRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询每个行业的经营情况
     * @return
     */
    public List<EveryIndustryManagementSituationDO> getStationOfIndustry(){
        //查询类别Id,然后循环类别ID查询出该商品的类型列表，然后根据类型列表中的第一个查询出该商品的父类
        final List<EveryIndustryManagementSituationDO> list = new ArrayList<EveryIndustryManagementSituationDO>();
        //1.查询出类别ID,category_id:类别ID,article_id:文章ID,goods_title:商品名,real_amount:实付金额
        jdbcTemplate.query("select sum(kk.real_amount) amountMoney,w.title,w.id from (select a.category_id,a.real_amount,a.goods_title,b.class_layer,b.class_list,b.title,replace(SUBSTRING(b.class_list,2,4),',','') maxparent  from (SELECT c.category_id, d.article_id, d.goods_title, d.real_amount FROM dt_article c INNER JOIN ( SELECT a.article_id,b.goods_title,b.real_amount FROM dt_article_goods a INNER JOIN ( SELECT b.goods_id,a.real_amount,b.goods_title FROM dt_orders a INNER JOIN dt_order_goods b ON a.id = b.order_id WHERE a.real_amount > 0) b ON a.id = b.goods_id) d ON c.id = d.article_id AND c.channel_id = 7) a inner join dt_article_category b on a.category_id=b.id where b.channel_id=7) kk inner join dt_article_category w on kk.maxparent=w.id group by w.id,w.title\n", new RowMapper() {
                    @Override
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        EveryIndustryManagementSituationDO everyIndustryManagementSituationDO = new EveryIndustryManagementSituationDO();
                        everyIndustryManagementSituationDO.setCategory_id(rs.getInt("id"));
                        everyIndustryManagementSituationDO.setTitle(rs.getString("title"));
                        everyIndustryManagementSituationDO.setAmountMoney(rs.getDouble("amountMoney"));

                        list.add(everyIndustryManagementSituationDO);
                        return list;
                    }
                });

        return list;
    }

}
