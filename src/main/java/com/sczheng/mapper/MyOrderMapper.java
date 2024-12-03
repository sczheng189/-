package com.sczheng.mapper;




import com.sczheng.pojo.MyOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyOrderMapper {

    @Select("select * from myorder")
    List<MyOrder> list();
    

//应该不需要
    void update(MyOrder myOrder);

    @Insert("insert into myorder(user_id, product_id, total_amount, order_date) value" +
            "(#{userId},#{productId},#{totalAmount}, #{orderDate}) ")
    void insert(MyOrder myOrder);


    @Select("select * from myorder where user_id = #{userId}")
    List<MyOrder> selectByUserId(Integer userId);
}
