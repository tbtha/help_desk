package com.optimumtech.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import com.optimumtech.ticket.models.entities.Ticket;
import com.optimumtech.ticket.repositories.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketServiceTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void probarEliminarTicket() {
        // Crear y guardar un ticket base
        Ticket ticket = new Ticket();
        ticket.setEmail("juan.pablo@gmail.com");
        ticket.setTitle("Ticket de prueba");
        ticket.setDescription("Descripción de prueba");
        ticket.setFechaRegistro(new Date());
        ticket.setEstado("CREADO");
        // Agrega aquí otros campos obligatorios de tu entidad Ticket si los hay
        ticket = ticketRepository.save(ticket);

        // Eliminar el ticket
        ticketRepository.deleteById(ticket.getId());

        // Verificar que ya no existe
        boolean existe = ticketRepository.findById(ticket.getId()).isPresent();
        assertEquals(false, existe);
    }
}