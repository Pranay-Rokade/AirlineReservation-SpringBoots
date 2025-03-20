package com.pranay.assignment1.repository;

import com.pranay.assignment1.dto.Flight;
import com.pranay.assignment1.dto.Ticket;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TicketRepository {
    Map<String, Ticket> ticketsTable;

    @Autowired
    FlightRepository flightRepository ;

    @PostConstruct
    public void init(){
        ticketsTable = new HashMap<>();
        Ticket ticket = new Ticket("1", "Pranay Rokade","pranay@gmail.com", "A010","a1");
        ticketsTable.put("1",ticket);
    }


    public ResponseEntity<?> createTicket(Ticket ticket){
        String TicketId = ticket.getId();
        String FlightId = ticket.getFlightId();
        List<Flight> flights = flightRepository.getAllFlights("asc");
        for (Flight flight : flights) {
            if (flight.getId().equals(FlightId)) {
                System.out.println("Flight found: " + flight);
                this.ticketsTable.put(TicketId, ticket);
                return ResponseEntity.ok(ticket.toBuilder().id(TicketId).build()); // 200 OK
//                return ticket.toBuilder().id(TicketId).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight with ID " + FlightId + " not found.");

    }

    public ResponseEntity<?> getTicketById(String id) {
        System.out.println("repo"+id);
        System.out.println(this);
        Ticket toReturn = this.ticketsTable.getOrDefault(id, null);
        if (toReturn == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ID " + id + " doesn't exist.");
        }
        return ResponseEntity.ok(toReturn);
    }

    public ResponseEntity<?> deleteTicket(String id){
        Ticket prev = ticketsTable.remove(id);
        if (prev == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ID " + id + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted Ticket with ID "+id);
    }
}
