package com.jooble.testrail.service;

import com.jooble.testrail.entity.Ticket;
import com.jooble.testrail.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {
    List<Ticket> findAll();

    Ticket findById(Long id);

    Ticket save(Ticket ticket);

    List<Ticket> findByDate(LocalDateTime localDateTime);

    boolean buyTicket(Ticket ticket, User userInfo);
}
