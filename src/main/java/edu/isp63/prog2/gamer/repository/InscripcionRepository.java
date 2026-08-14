package edu.isp63.prog2.gamer.repository;

import edu.isp63.prog2.gamer.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    List<Inscripcion> findByJugadorId(Integer jugadorId);
    List<Inscripcion> findByTorneoId(Integer torneoId);
    boolean existsByJugadorIdAndTorneoId(Integer jugadorId, Integer torneoId);
    long countByTorneoId(Integer torneoId);
}
