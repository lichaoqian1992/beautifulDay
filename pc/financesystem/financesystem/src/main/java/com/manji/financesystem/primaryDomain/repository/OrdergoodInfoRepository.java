package com.manji.financesystem.primaryDomain.repository;

import com.manji.financesystem.primaryDomain.entiity.OrdergoodInfoDo;
import com.manji.financesystem.utils.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudding on 2017-3-2.
 */
@Repository
public class OrdergoodInfoRepository {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询全部发货信息
     * @return
     */
    public List<OrdergoodInfoDo> findAllOrdergoodInfoDo(final int page){
        final List<OrdergoodInfoDo> li=new ArrayList<OrdergoodInfoDo>();
        String sql="select top 20 * from dt_order_goodinfo a  order by a.id;";
        jdbcTemplate.query(sql, new PreparedStatementSetter(){
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, page);
            }
        },new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrdergoodInfoDo order=new OrdergoodInfoDo();
                order.setId(rs.getInt("id"));
                order.setOrder_id(rs.getInt("order_id"));
                order.setExpress_id(rs.getInt("express_id"));
                order.setExpress_no(rs.getString("express_no"));
                order.setExpress_status(rs.getString("express_status"));
                order.setExpress_time(rs.getDate("express_time"));
                order.setAccept_name(rs.getString("accept_name"));
                order.setPost_code(rs.getString("post_code"));
                order.setTelephone(rs.getString("telephone"));
                order.setMobile(rs.getString("mobile"));
                order.setEmail(rs.getString("email"));
                order.setArea(rs.getString("area"));
                order.setAddress(rs.getString("address"));
                order.setIs_receipt(rs.getString("is_receipt"));
                order.setIs_deliver(rs.getString("is_deliver"));
                li.add(order);
                return li;
            }
        });
        return li;
    }
}
