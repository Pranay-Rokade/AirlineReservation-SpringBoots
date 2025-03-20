package com.pranay.assignment1.service;

import com.pranay.assignment1.dto.Ticket;
import com.pranay.assignment1.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public ResponseEntity<?> createTicket(Ticket ticket){
        return ticketRepository.createTicket(ticket);
    }

    public ResponseEntity<?> getTicketById(@PathVariable String id){
        System.out.println("service "+id);
        return ticketRepository.getTicketById(id);
    }

    public ResponseEntity<?> deleteTicket(@PathVariable String id){
        return ticketRepository.deleteTicket(id);
    }
}
