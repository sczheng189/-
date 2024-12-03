package com.sczheng.mapper;




import com.sczheng.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> list(String username);

    int delete(List<Integer> ids);

    @Select("select * from user where id = #{id}")
    User getById(Integer id);



//    @Update("update user set username=#{username},email=#{email},password=#{password},address=#{address},role=#{role},money=#{money} where id=#{id}")
    void update(User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(User user);

    @Insert("insert into user(username,email, password,address,role,money) values(#{username},#{email},#{password},#{address},#{role},#{money})")
    void register(User user);

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);
}
