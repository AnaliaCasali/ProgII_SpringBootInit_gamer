package edu.isp63.prog2.gamer.repository;

import edu.isp63.prog2.gamer.entity.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TorneoRepository  extends JpaRepository<Torneo, Integer> {
    public List<Torneo> findByPlataforma(String plataforma);
    //List<Torneo> findByNombreIgnoreCase (String nombre);

}



