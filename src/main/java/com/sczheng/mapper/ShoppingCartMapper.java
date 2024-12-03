package com.sczheng.mapper;




import com.sczheng.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    @Select("select * from shoppingCart")
    List<ShoppingCart> list();

    int delete(List<Integer> ids);


    void update(ShoppingCart shoppingCart);

    @Insert("insert into shoppingcart(user_id,product_id, num, totalprice) values(#{userId} ,#{productId}, #{num}, #{totalPrice}) ")
    void insert(ShoppingCart shoppingCart);

    @Select("select * from shoppingCart where user_id = #{userId}")
    List<ShoppingCart> getCartById(Integer userId);
}
