package com.jooble.testrail;

import com.jooble.testrail.entity.Ticket;
import com.jooble.testrail.entity.TicketStatus;
import com.jooble.testrail.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class InitBean {

    public InitBean(@Autowired TicketService ticketService) {
        Ticket ticket = new Ticket();
        ticket.setDepartureTime(LocalDateTime.of(2017, 5, 3, 0, 0));
        ticket.setFrom("Санкт-Петербург");
        ticket.setTo("Москва");
        ticket.setArrival(LocalDateTime.of(2017, 5, 4, 0, 0));
        ticket.setValue(new BigDecimal(992));
        ticket.setStatus(TicketStatus.Free);

        Ticket ticket1 = new Ticket();
        ticket1.setDepartureTime(LocalDateTime.of(2017, 5, 3, 0, 0));
        ticket1.setFrom("Санкт-Петербург");
        ticket1.setTo("Уфа");
        ticket1.setArrival(LocalDateTime.of(2017, 5, 5, 0, 0));
        ticket1.setValue(new BigDecimal(343));
        ticket1.setStatus(TicketStatus.Free);
        ticket1.setDepartureTime(LocalDateTime.of(2017, 6, 4, 0, 0));

        ticketService.save(ticket);
        ticketService.save(ticket1);
    }
}
