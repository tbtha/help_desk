package com.optimumtech.ticket.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.optimumtech.ticket.controllers.TicketController;
import com.optimumtech.ticket.models.entities.Ticket;
@Component
public class TicketModelAssembler implements RepresentationModelAssembler<Ticket, EntityModel<Ticket>> {

    @Override
    public EntityModel<Ticket> toModel(Ticket ticket) {
        return EntityModel.of(ticket,
            linkTo(methodOn(TicketController.class).obtenerUno(ticket.getId())).withSelfRel(),
            linkTo(methodOn(TicketController.class).obtenerTodos()).withRel("ticket")
        );
    }
}
