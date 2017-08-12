package com.jooble.testrail.service.impl;

import com.jooble.testrail.dao.TicketDao;
import com.jooble.testrail.entity.Ticket;
import com.jooble.testrail.entity.TicketStatus;
import com.jooble.testrail.entity.User;
import com.jooble.testrail.exception.TicketBusyException;
import com.jooble.testrail.service.BankService;
import com.jooble.testrail.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketDao ticketDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BankService bankService;

    @Transactional(readOnly = true)
    @Override
    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Ticket findById(Long id) {
        return ticketDao.findOne(id);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketDao.save(ticket);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Ticket> findByDate(LocalDateTime localDateTime) {
        return ticketDao.findByDepartureTimeAndStatus(localDateTime, TicketStatus.Free);
    }

    @Override
    public boolean buyTicket(Ticket ticket, User userInfo) {
        entityManager.lock(ticket, LockModeType.WRITE);
        if (ticket.getStatus() != TicketStatus.Free) throw new TicketBusyException(ticket);

        if (bankService.payment(userInfo, ticket.getValue())) {
            ticket.setStatus(TicketStatus.Busy);
            entityManager.lock(ticket, LockModeType.NONE);
            return true;
        } else {
            entityManager.lock(ticket, LockModeType.NONE);
            return false;
        }
    }
}
