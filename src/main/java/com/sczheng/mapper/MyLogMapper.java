package com.sczheng.mapper;


import com.sczheng.pojo.MyLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyLogMapper {


    @Select("select * from mylog")
    List<MyLog> list();

    @Insert("insert into mylog(action_type, user_id,product_id,timestamp ) values(#{actionType},#{userId},#{productId},#{timestamp})"   )
    void insert(MyLog mylog);

    @Select("select * from mylog where user_id = #{userId}")
    List<MyLog> getByUserId(Integer userid);

    @Select("select * from mylog where action_type = #{actionType}")
    List<MyLog> listWithAT(Short actionType);
}
