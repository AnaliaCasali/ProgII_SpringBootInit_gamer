package edu.isp63.prog2.gamer.controller;


import edu.isp63.prog2.gamer.dto.JugadorCreateDTO;
import edu.isp63.prog2.gamer.dto.JugadorResponseDTO;
import edu.isp63.prog2.gamer.entity.Jugador;
import edu.isp63.prog2.gamer.service.JugadorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/buscarporid")
    ResponseEntity<JugadorResponseDTO> buscarJugadorPorId(@RequestParam Integer id)
    {
            JugadorResponseDTO jugadorResponseDTO= jugadorService.buscarJugadorPorId(id);
            if(jugadorResponseDTO!=null) {
                return  ResponseEntity.ok(jugadorResponseDTO);
            }
            else  {
                return ResponseEntity.notFound().build();
            }
    }


        @GetMapping("/{id}")
        public ResponseEntity<JugadorResponseDTO> obtenerPorIdv2(@PathVariable Integer id) {

        return jugadorService.buscarJugadorPorIdv2(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

     }

}
