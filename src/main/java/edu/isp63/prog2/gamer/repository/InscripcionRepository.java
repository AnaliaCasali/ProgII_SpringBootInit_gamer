package edu.isp63.prog2.gamer.repository;

import edu.isp63.prog2.gamer.dto.InscripcionJugadorResponseDTO;
import edu.isp63.prog2.gamer.dto.InscripcionResponseDTO;
import edu.isp63.prog2.gamer.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    List<Inscripcion> findByJugadorId(Integer jugadorId);
    List<Inscripcion> findByTorneoId(Integer torneoId);
    boolean existsByJugadorIdAndTorneoId(Integer jugadorId, Integer torneoId);
    long countByTorneoId(Integer torneoId);

    // 1. JOIN (filtrar por atributo de Jugador)
    @Query("SELECT i FROM Inscripcion i JOIN Jugador j WHERE j.rango=:rango")
    List<Inscripcion> findByRango(@Param("rango") Integer rango);

    // 2. JOIN FETCH (cargar Inscripción junto con Jugador )
    @Query("SELECT new edu.isp63.prog2.gamer.dto.InscripcionJugadorResponseDTO(" +
            "i.id, j.nickname, i.fechaInscripcion) " +
            "FROM Inscripcion i JOIN i.jugador j " +
            "WHERE i.fechaInscripcion BETWEEN :fechaDesde AND :fechaHasta")
    List<InscripcionJugadorResponseDTO> findByFechaInscripcionBetweenJPQL(
            @Param("fechaDesde") LocalDate fechaDesde,
            @Param("fechaHasta") LocalDate fechaHasta);


    // 2. JOIN FETCH (cargar Inscripción junto con Jugador y Torneo )
    @Query("SELECT new edu.isp63.prog2.gamer.dto.InscripcionResponseDTO(" +
            "i.id, j.nickname, t.nombreTorneo, i.fechaInscripcion) " +
            "FROM Inscripcion i " +
            "JOIN i.jugador j " +
            "JOIN i.torneo t " +
            "WHERE i.fechaInscripcion BETWEEN :fechaDesde AND :fechaHasta " +
            "AND t.nombreTorneo = :nombreTorneo")
    List<InscripcionResponseDTO> findByFechaInscripcionBetweenAndTorneo(
            @Param("fechaDesde") LocalDate fechaDesde,
            @Param("fechaHasta") LocalDate fechaHasta,
            @Param("nombreTorneo") String nombreTorneo);
}
