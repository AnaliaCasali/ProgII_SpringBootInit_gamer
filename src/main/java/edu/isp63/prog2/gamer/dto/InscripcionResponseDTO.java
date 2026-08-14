package edu.isp63.prog2.gamer.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InscripcionResponseDTO(
        Integer id,
        String nicknameJugador,
        String nombreTorneo,
        LocalDate fechaInscripcion
) {
}
