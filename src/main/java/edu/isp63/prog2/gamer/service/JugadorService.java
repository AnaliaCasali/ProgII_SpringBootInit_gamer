package edu.isp63.prog2.gamer.service;

import edu.isp63.prog2.gamer.dto.JugadorCreateDTO;
import edu.isp63.prog2.gamer.dto.JugadorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface JugadorService {
        List<JugadorResponseDTO> listarTodosJugadores();
        JugadorResponseDTO  crearJugador(JugadorCreateDTO jugador);
        JugadorResponseDTO buscarJugadorPorId(Integer id);
        Optional<JugadorResponseDTO> buscarJugadorPorIdv2(Integer id);
        Optional<JugadorResponseDTO> actualizar(Integer id, JugadorCreateDTO jugador);
        boolean eliminarJugador(Integer id);
        Page<JugadorResponseDTO> listarTodos(Pageable pageable);
        Page<JugadorResponseDTO> listarPorRango(String rango, Pageable pageable);

        // para JPQL
        List<JugadorResponseDTO>findByNicknameJPQL(String nickname);
        List<JugadorResponseDTO>findByEmailJPQL(String email);

        Optional<String> findEmailByIdJPQL(Integer id);
}
