package edu.isp63.prog2.gamer.dto;
import jakarta.validation.constraints.NotNull;

public record InscripcionCreateDTO(
        @NotNull(message = "El id del jugador es obligadorio")
        Integer jugdorId,
        @NotNull(message = "El id del torneo es obligadorio")
        Integer TorneoId
)
{ }
