package com.example.BookManager.interceptor;
import com.example.BookManager.model.constants.Ticket;
import com.example.BookManager.model.constants.User;
import com.example.BookManager.service.TicketService;
import com.example.BookManager.service.UserService;
import com.example.BookManager.utils.ConcurrentUtils;
import com.example.BookManager.utils.CookieUtils;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception{
            String t = CookieUtils.getCookie("t",request);
            if(!StringUtils.isEmpty(t)){
                Ticket ticket = ticketService.getTicket(t);
                if(ticket != null && ticket.getExpiredAt().after(new Date())){
                    User host = userService.getUser(ticket.getUserId());
                    ConcurrentUtils.setHost(host);
                }
            }
            return true;
        }
}
