package com.jooble.testrail.controller;


import com.jooble.testrail.entity.Ticket;
import com.jooble.testrail.entity.User;
import com.jooble.testrail.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;


    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public List<Ticket> getTickets(@RequestParam(name = "day") int day,
                                   @RequestParam(name = "month") int month,
                                   @RequestParam(name = "year") int year) {
        return ticketService.findByDate(LocalDateTime.of(year, month, day, 0, 0));
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> buyTicket(@PathVariable(name = "id") Long id,
                                            User userInfo) {
        Ticket ticket = ticketService.findById(id);

        if (ticketService.buyTicket(ticket, userInfo)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
