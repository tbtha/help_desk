package com.optimumtech.ticket.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.optimumtech.ticket.models.requests.TicketCreate;
import com.optimumtech.ticket.models.requests.TicketUpdate;
import com.optimumtech.ticket.services.TicketService;
import com.optimumtech.ticket.models.entities.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimumtech.ticket.assemblers.TicketModelAssembler;
import com.optimumtech.ticket.models.entities.Ticket;
import com.optimumtech.ticket.models.requests.TicketCreate;
import com.optimumtech.ticket.services.TicketService;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/ticket")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketModelAssembler assembler;

    @GetMapping("/")
    public CollectionModel<EntityModel<Ticket>> obtenerTodos() {
      List<EntityModel<Ticket>> ticket = ticketService.obtenerTodos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(ticket,
            linkTo(methodOn(TicketController.class).obtenerTodos()).withSelfRel());

    }

    // @GetMapping("/{id}")
    // public User obtenerUno(@PathVariable int id) {
    //     return userService.obtenerPorId(id);
    // }

    @PostMapping("/")
    public EntityModel<Ticket> registrar(@Valid @RequestBody TicketCreate body) {
        Ticket ticket = ticketService.registrar(body);
        return assembler.toModel(ticket);
    }

    @PutMapping()

    public EntityModel<Ticket> actualizar(@Valid @RequestBody TicketUpdate body) {
        Ticket ticket = ticketService.actualizar(body);
        return assembler.toModel(ticket);
    }

}
