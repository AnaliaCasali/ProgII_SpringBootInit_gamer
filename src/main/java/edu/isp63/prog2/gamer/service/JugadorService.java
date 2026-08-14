package edu.isp63.prog2.gamer.service;

import edu.isp63.prog2.gamer.dto.JugadorCreateDTO;
import edu.isp63.prog2.gamer.dto.JugadorResponseDTO;

import java.util.List;

public interface JugadorService {
        List<JugadorResponseDTO> listarTodosJugadores();
        JugadorResponseDTO  crearJugador(JugadorCreateDTO jugador);
}
