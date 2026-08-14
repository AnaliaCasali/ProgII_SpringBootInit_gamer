package edu.isp63.prog2.gamer.service;

import edu.isp63.prog2.gamer.dto.TorneoCreateDTO;
import edu.isp63.prog2.gamer.dto.TorneoResponseDTO;
import edu.isp63.prog2.gamer.entity.Torneo;

import java.util.List;

public interface TorneoService {
    List<TorneoResponseDTO> listarTodosTorneos(Integer torneoId);
    TorneoResponseDTO crearTorneo(TorneoCreateDTO torneo);
    //List<TorneoResponseDTO> buscarPorNombre(String nombreTorneo);
    List<TorneoResponseDTO> buscarPorPlataforma(String plataforma);
}
