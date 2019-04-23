package com.example.BookManager.dao;
import com.example.BookManager.model.constants.Cookie;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface CookieDAO {
    String table_name = " cookie ";
    String insert_field = " user_id, cookie, expired_at ";
    String select_field = " id, " + insert_field;

    @Insert({"insert into", table_name, "(", insert_field,
        ") values (#{user_id},#{cookie},#{expired_at})"})
    int addCookie(Cookie cookie);

    @Select({"select", select_field, "from", table_name, "where user_id=#{uid}"})
    Cookie selectByUserId(int uid);

    @Select({"select", select_field, "from", table_name, "where cookie=#{c}"})
    Cookie selectByCookie(String c);

    @Delete({"delete from", table_name, " where id=#{tid}"})
    void deleteCookieById(int tid);

    @Delete({"delete from", table_name, " where cookie=#{c}"})
    void deleteCookie(String c);

}
