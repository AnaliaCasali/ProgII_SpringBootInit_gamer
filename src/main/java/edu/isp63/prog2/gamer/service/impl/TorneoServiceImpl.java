package edu.isp63.prog2.gamer.service.impl;

import edu.isp63.prog2.gamer.dto.TorneoCreateDTO;
import edu.isp63.prog2.gamer.dto.TorneoResponseDTO;
import edu.isp63.prog2.gamer.entity.Torneo;
import edu.isp63.prog2.gamer.repository.TorneoRepository;
import edu.isp63.prog2.gamer.service.TorneoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TorneoServiceImpl implements TorneoService {
    // inyección de dependencia de torneo Repository x constructor
    private final TorneoRepository torneoRepository;
    public TorneoServiceImpl(TorneoRepository torneoRepository) {
        this.torneoRepository = torneoRepository;
    }

    @Override
    public List<TorneoResponseDTO> listarTodosTorneos(Integer torneoId) {

        return  torneoRepository.findAll()
                        .stream()
                        .map(this::toResponseDTO)
                        .toList();
    }

    @Override
    public TorneoResponseDTO crearTorneo(TorneoCreateDTO torneo) {
        Torneo torneoEntity = new Torneo();
        torneoEntity.setNombreTorneo(torneo.nombreTorneo());
        torneoEntity.setPlataforma(torneo.plataforma());
        torneoEntity.setCupo(torneo.cupo());
        torneoEntity.setPrecio(torneo.precio());
        torneoEntity.setNombreJuego(torneo.nombreJuego());
        // creo llamando al save
        Torneo torneoGuardado= torneoRepository.save(torneoEntity);
        // convierto Torneo a TorneoResponse
        TorneoResponseDTO torneoResponse= toResponseDTO(torneoGuardado);
        // devuelvo el response
        return torneoResponse;
    }

/*    @Override
    public List<TorneoResponseDTO> buscarPorNombre(String nombreTorneo) {
        return torneoRepository.findByNombreIgnoreCase(nombreTorneo)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
*/
    @Override
    public List<TorneoResponseDTO> buscarPorPlataforma(String plataforma) {
        return torneoRepository.findByPlataforma(plataforma)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private  TorneoResponseDTO toResponseDTO(Torneo torneo) {
        return new TorneoResponseDTO(
                torneo.getId(),
                torneo.getNombreTorneo(),
                torneo.getNombreJuego(),
                torneo.getPrecio(),
                torneo.getCupo(),
                torneo.getPlataforma());
    }
}
