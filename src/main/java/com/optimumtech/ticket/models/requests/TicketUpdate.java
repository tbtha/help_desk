package com.optimumtech.ticket.models.requests;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TicketUpdate {
    
    @NotBlank
    private int id;

    @NotBlank
    private String estado;    
}
