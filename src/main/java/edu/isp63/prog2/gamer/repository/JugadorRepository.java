package edu.isp63.prog2.gamer.repository;

import edu.isp63.prog2.gamer.entity.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador,Integer> {
    Jugador findByNickname(String nickname);
    boolean existsByEmail(String email);
}
