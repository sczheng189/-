package com.sczheng.mapper;




import com.sczheng.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from product")
    List<Product> listAll();

    @Select("select * from product where name like concat('%',#{name},'%') ")
    List<Product> list(String name);

    int delete(List<Integer> ids);

    @Select("select * from product where id = #{id}")
    Product getById(Integer id);

    void update(Product product);


    @Insert("insert into product(name, description, price, image_url, stock_quantity, create_time, update_time) values(#{name},#{description},#{price},#{imageUrl},#{stockQuantity},#{createTime},#{updateTime})")
    void insert(Product product);

    @Update("update product set stock_quantity = stock_quantity - #{decrementValue} where id = #{id}")
    void updateStock(@Param("id") Integer id, @Param("decrementValue") Integer decrementValue);
}
