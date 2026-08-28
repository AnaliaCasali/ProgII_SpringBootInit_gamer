package edu.isp63.prog2.gamer.dto;

import java.time.LocalDate;

public record InscripcionJugadorResponseDTO(
        Integer idInscripcion,
        String nickname,
        LocalDate fechaInscripcion
)
{ }
