package edu.isp63.prog2.gamer.repository;

import edu.isp63.prog2.gamer.entity.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador,Integer> {
      // busco un jugador por nombre de Usuario
    //  si termina con variable nickname ignorando case "S" o "s"
    //
     Optional<Jugador> getByNicknameEndsWithIgnoreCase(String nickname);

     // busca todos los jugadores que coincida con el rango del string exacto
     List<Jugador> findByRango (String rango);

    // busca todos los jugadores que coincida con el rango del string
    // en alguna parte
    // ordeno por nickname de manera descendente
    List<Jugador> findByRangoContainingOrderByNicknameDesc (String rango);

    // verifica que existe el email y devuelve VoF
     boolean existsByEmail(String email);

     // uno criterios con AND
    List<Jugador> findByEmailContainingAndNickname(String arroba, String nickname);

    // lista los 3 primeros jugadores que encuentre con ese rango y los ordena por email
    List<Jugador> findTop3ByRangoOrderByEmailAsc(String Rango) ;

    // criterios numericos
    // prefijo atributo criterio
    // lista de jugadores cuyo id sea menor que menor
    List<Jugador> findByIdLessThan(Integer menor);
    // lista de jugadores en el rango
    List<Jugador> findByIdBetween(Integer menor, Integer mayor);

 }
