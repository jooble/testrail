package com.jooble.testrail.dao;

import com.jooble.testrail.entity.Ticket;
import com.jooble.testrail.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketDao extends JpaRepository<Ticket, Long> {

    List<Ticket> findByDepartureTimeAndStatus(LocalDateTime localDateTime, TicketStatus status);
}
