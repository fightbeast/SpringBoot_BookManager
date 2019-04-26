package com.example.BookManager.biz;

import com.example.BookManager.model.constants.Ticket;
import com.example.BookManager.model.constants.User;
import com.example.BookManager.model.exceptions.LoginRegisterException;
import com.example.BookManager.service.UserService;
import com.example.BookManager.service.TicketService;
import com.example.BookManager.utils.ConcurrentUtils;
import com.example.BookManager.utils.MD5;
import com.example.BookManager.utils.TicketUtils;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginBiz {
    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    //登陆逻辑
    public String login(String email, String password) throws Exception{
        User user = userService.getUser(email);

        if(user == null)
            throw new LoginRegisterException("邮箱不存在");
        if(!StringUtils.equals(MD5.next(password),user.getPassword()))
            throw new LoginRegisterException("密码不正确");

        Ticket ticket = ticketService.getTicket(user.getId());

        if(ticket == null){
            ticket = TicketUtils.next(user.getId());
            ticketService.addTicket(ticket);
            return ticket.getTicket();
        }
        if(ticket.getExpiredAt().before(new Date())){
           ticketService.deleteTicket(ticket.getId());
        }
        ticket = TicketUtils.next(user.getId());
        ticketService.addTicket(ticket);

        ConcurrentUtils.setHost(user);
        return ticket.getTicket();
    }

    public void logout(String t){
        ticketService.deleteTicket(t);
    }

    public String register(User user) throws Exception{
        if(userService.getUser(user.getEmail()) != null){
            throw new LoginRegisterException("用户已存在！");
        }

        String plain = user.getPassword();
        String md5 = MD5.next(plain);
        user.setPassword(md5);

        userService.addUser(user);

        Ticket ticket = TicketUtils.next(user.getId());
        ticketService.addTicket(ticket);

        ConcurrentUtils.setHost(user);
        return ticket.getTicket();

    }









}
