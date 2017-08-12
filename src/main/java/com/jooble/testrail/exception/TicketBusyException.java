package com.jooble.testrail.exception;

import com.jooble.testrail.entity.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "ticket is busy")
public class TicketBusyException extends RuntimeException {

    public TicketBusyException(Ticket ticket) {
        super("ticket - " + ticket.getId() + " is busy");
    }
}
