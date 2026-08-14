package edu.isp63.prog2.gamer.controller;


import edu.isp63.prog2.gamer.dto.JugadorCreateDTO;
import edu.isp63.prog2.gamer.dto.JugadorResponseDTO;
import edu.isp63.prog2.gamer.entity.Jugador;
import edu.isp63.prog2.gamer.service.JugadorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jugadores")
public class JugadorController {

    // siempre el controller inyecta service
    private final JugadorService jugadorService;
    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping()
    List<JugadorResponseDTO> listaJugadores(){
        return jugadorService.listarTodosJugadores();
    }

    // Post para crear , @RequestBody para recibir un objeto, y
    // @Valid para ejecutar las validaciones del objeto recibido
    @PostMapping
    JugadorResponseDTO crearJugador(@Valid @RequestBody JugadorCreateDTO jugador){
        return jugadorService.crearJugador(jugador);
    }

}
