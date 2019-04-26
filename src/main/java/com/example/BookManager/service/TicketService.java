package com.example.BookManager.service;
import com.example.BookManager.dao.TicketDAO;
import com.example.BookManager.model.constants.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    public void addTicket(Ticket t){
        ticketDAO.addTicket(t);
    }

    public Ticket getTicket(int uid){
        return ticketDAO.selectByUserId(uid);
    }
    public Ticket getTicket(String t){
        return ticketDAO.selectByTicket(t);
    }
    public void deleteTicket(String t){
        ticketDAO.deleteTicket(t);;
    }
    public void deleteTicket(int tid){
        ticketDAO.deleteTicketById(tid);
    }

}
