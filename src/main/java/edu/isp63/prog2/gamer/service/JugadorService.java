package edu.isp63.prog2.gamer.service;

import edu.isp63.prog2.gamer.dto.JugadorCreateDTO;
import edu.isp63.prog2.gamer.dto.JugadorResponseDTO;

import java.util.List;
import java.util.Optional;

public interface JugadorService {
        List<JugadorResponseDTO> listarTodosJugadores();
        JugadorResponseDTO  crearJugador(JugadorCreateDTO jugador);
        JugadorResponseDTO buscarJugadorPorId(Integer id);
        Optional<JugadorResponseDTO> buscarJugadorPorIdv2(Integer id);
}
