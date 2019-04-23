package com.example.BookManager.service;
import com.example.BookManager.dao.CookieDAO;
import com.example.BookManager.model.constants.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    @Autowired
    private CookieDAO cookieDAO;

    public void addCookie(Cookie c){
        cookieDAO.addCookie(c);
    }

    public Cookie getCookie(int uid){
        return cookieDAO.selectByUserId(uid);
    }
    public Cookie getCookie(String c){
        return cookieDAO.selectByCookie(c);
    }
    public void deleteCookie(String c){
        cookieDAO.deleteCookie(c);
    }
    public void deleteCookie(int tid){
        cookieDAO.deleteCookieById(tid);
    }

}
