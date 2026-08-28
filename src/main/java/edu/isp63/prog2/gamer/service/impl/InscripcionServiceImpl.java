package edu.isp63.prog2.gamer.service.impl;

import edu.isp63.prog2.gamer.dto.InscripcionJugadorResponseDTO;
import edu.isp63.prog2.gamer.dto.InscripcionResponseDTO;
import edu.isp63.prog2.gamer.entity.Inscripcion;
import edu.isp63.prog2.gamer.repository.InscripcionRepository;
import edu.isp63.prog2.gamer.service.InscripcionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class InscripcionServiceImpl implements InscripcionService {
    // inyección de dependencia
    private final InscripcionRepository inscripcionRepository;

    public InscripcionServiceImpl(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public List<InscripcionJugadorResponseDTO> findByFechaInscripcionBetweenJPQL
            (LocalDate fechaDesde, LocalDate fechaHasta) {
        return inscripcionRepository.findByFechaInscripcionBetweenJPQL(fechaDesde, fechaHasta);
    }

    @Override
    public List<InscripcionResponseDTO> findByRango(Integer rango) {
        return inscripcionRepository
                .findByRango(rango)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    private InscripcionResponseDTO toResponseDTO(Inscripcion inscripcion) {
        // convierte una entidad en record para generar el DTO
        return new InscripcionResponseDTO(
                inscripcion.getId(),
                inscripcion.getJugador().getNickname(),
                inscripcion.getTorneo().getNombreTorneo(),
                inscripcion.getFechaInscripcion());
    }
}
