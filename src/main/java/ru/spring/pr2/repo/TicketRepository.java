package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
