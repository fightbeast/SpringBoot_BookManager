package com.example.BookManager.dao;
import com.example.BookManager.model.constants.User;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface UserDAO {
    String table_name = " user ";
    String insert_field = " name, email, password ";
    String select_field = " id, " + insert_field;

    @Insert({"insert into", table_name, "(", insert_field,
        ") values (#{name},#{email},#{password})"})
    int addUser(User user);

    @Select({"select", select_field, "from", table_name, "where id=#{id}"})
    User selectById(int id);

    @Select({"select", select_field, "from", table_name, "where name=#{name}"})
    User selectByName(String name);

    @Select({"select", select_field, "from", table_name, "where email=#{email}"})
    User selectByEmail(String email);

    @Select({"select", select_field, "from", table_name, "where password=#{password}"})
    User selectByPassword(String password);

    @Update({"update", table_name, "set password=#{password} where id=#{id}"})
    void updatePassword(User user);
}
