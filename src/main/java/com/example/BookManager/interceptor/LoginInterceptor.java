package com.example.BookManager.interceptor;
import com.example.BookManager.model.constants.Ticket;
import com.example.BookManager.service.TicketService;
import com.example.BookManager.utils.CookieUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TicketService ticketService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception{
            String t = CookieUtils.getCookie("t",request);
            if(StringUtils.isEmpty(t)){
                response.sendRedirect("/users/login");
                return false;
            };

            Ticket ticket = ticketService.getTicket(t);
            if(ticket == null){
                response.sendRedirect("/users/login");
                return false;
            }

            if(ticket.getExpiredAt().before(new Date())){
                response.sendRedirect("/users/login");
                return false;
            }
            return true;
        }
}
