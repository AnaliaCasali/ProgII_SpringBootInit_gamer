package edu.isp63.prog2.gamer.service.impl;

import edu.isp63.prog2.gamer.dto.JugadorCreateDTO;
import edu.isp63.prog2.gamer.dto.JugadorResponseDTO;
import edu.isp63.prog2.gamer.entity.Jugador;
import edu.isp63.prog2.gamer.repository.JugadorRepository;
import edu.isp63.prog2.gamer.service.JugadorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JugadorServiceImpl implements JugadorService {
    ///  SI O SI  LOS SERVICE NECESITAN INYECTAR REPOSITORY
    // Inyección de Dependencias por contructor
    // paso1 creo la variable  que sera final

    private final JugadorRepository jugadorRepository ;
    // paso 2 agrego el contructor para inicializar la variable
    public JugadorServiceImpl(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
     }
    @Override
    public List<JugadorResponseDTO> listarTodosJugadores() {
        List<JugadorResponseDTO> lista=
                jugadorRepository
                        .findAll()
                        .stream()
                        .map(this::toResponseDTO)
                        .toList();
        return lista;
    }
    private JugadorResponseDTO toResponseDTO(Jugador jugador) {
        return new JugadorResponseDTO(
                                        jugador.getId(),
                                        jugador.getNickname(),
                                        jugador.getEmail(),
                                        jugador.getRango());
    }
    @Override
    public JugadorResponseDTO crearJugador(JugadorCreateDTO jugador) {
        log.warn("JugadorCreateDTO que intento guardar" +  jugador.toString());

        Jugador jugadorEntity= new Jugador();
        jugadorEntity.setEmail(jugador.email());
        jugadorEntity.setNickname(jugador.nickname());
        jugadorEntity.setPassword(jugador.password());

        log.warn("Jugador que intento guardar" +  jugadorEntity.toString());

        if( jugadorRepository.existsByEmail(jugadorEntity.getEmail())) {
            System.out.println("El email ya esta registrado");
            return null;
        }

        Jugador jugadorGuardado= jugadorRepository.save(jugadorEntity);

        return new JugadorResponseDTO(jugadorGuardado.getId(),
                                        jugadorGuardado.getNickname(),
                                        jugadorGuardado.getEmail(),
                                        jugadorGuardado.getRango());
    }

    @Override
    public JugadorResponseDTO buscarJugadorPorId(Integer id) {
            // llamo al repository para encontrar el jugador con ese id
           Optional<Jugador> jugador= jugadorRepository.findById(id);
           JugadorResponseDTO responseDTO = null;
           // si el jugador no es null
           if(jugador.isPresent()) {
               // lo convierto a DTO
               responseDTO=toResponseDTO(jugador.get());
           }
           // devuelvo el dto
           return responseDTO;
    }

    @Override
    public Optional<JugadorResponseDTO> buscarJugadorPorIdv2(Integer id) {
        // busco por id con repository
        Optional<Jugador> jugador= jugadorRepository.findById(id);
        // genero un optional de response a partir de un jugador que puede que sea nulo
        Optional<JugadorResponseDTO> responseDTO=
                Optional.ofNullable(
                        // convierte a DTO el jugador que vino del repository
                        toResponseDTO(jugador.get())
                );
        return responseDTO;
    }


}
