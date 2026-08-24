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
        Optional<Jugador> jugador = jugadorRepository.findById(id);
        //si quiero convertir el contenido del Optional,
        // sin "abrirlo" con .get() uso MAP, si lo encontro convierte pero
        // si jugador está vacío, .map() no hace nada
        // y devuelve Optional.empty()
        return jugador.map(this::toResponseDTO);
    }

    @Override
    public Optional<JugadorResponseDTO> actualizar(Integer id, JugadorCreateDTO jugador) {

        return jugadorRepository
                .findById(id) // lo busco con el repository
                .map(jugador1 -> { // convierto createDto a jugador
                    jugador1.setNickname(jugador.nickname());
                    jugador1.setEmail(jugador.email());
                    jugador1.setPassword(jugador.password());
                    return jugadorRepository.save(jugador1); // guardo la actualizacion con el repository
                })
                .map(this::toResponseDTO);
            // llamo al metodo para volver a convertir a la inversa Jugador a ResponseDTO

    }

    @Override
    public boolean eliminarJugador(Integer id) {
        if( jugadorRepository.existsById(id) ) {
             Jugador jugador= jugadorRepository.findById(id).get();
            jugadorRepository.delete(jugador);
            return true;
        }
        else
            return false;

    }
}
