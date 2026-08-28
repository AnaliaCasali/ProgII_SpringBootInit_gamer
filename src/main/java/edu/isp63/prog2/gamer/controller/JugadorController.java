package edu.isp63.prog2.gamer.controller;


import edu.isp63.prog2.gamer.dto.JugadorCreateDTO;
import edu.isp63.prog2.gamer.dto.JugadorResponseDTO;
import edu.isp63.prog2.gamer.entity.Jugador;
import edu.isp63.prog2.gamer.service.JugadorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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


    @PostMapping("/crear")
    ResponseEntity<JugadorResponseDTO> crearJugadorV2(@Valid @RequestBody JugadorCreateDTO jugador,
    UriComponentsBuilder uriBuilder){

        // paso 1 agrego en la firma del metodo ResponseEntity y UriComponentsBuilder
        // paso 2 guardo en una variable el responseDTO que devuelve el crear
        JugadorResponseDTO jugadorCreado   = jugadorService.crearJugador(jugador);

        // paso 3 crear la url para identificar el objeto creado y devolverlo completo
        URI url= uriBuilder
                .path("/api/v1/jugadores/{id}")  // veo la ruta definida arriba
                .buildAndExpand(jugadorCreado.id()) // uso el dto anterior
                .toUri();

        //paso 4 devuelvo el responseEntity con la url y el jugador creado
        return ResponseEntity.created(url).body(jugadorCreado);
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

     @PutMapping("/{id}")
    public ResponseEntity<JugadorResponseDTO>actualizar
             (@PathVariable Integer id, @Valid @RequestBody JugadorCreateDTO jugador ){

       return  jugadorService.actualizar(id, jugador)
               .map(ResponseEntity::ok) // si lo encontro ok
               .orElseGet(()->ResponseEntity.notFound().build()); // sino notFound

     }
     @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable Integer id){
         // uso operador ternario
        // si pudo eliminar devuelve noContent  sino no encontrado
         return jugadorService.eliminarJugador(id)?
                                                ResponseEntity.noContent().build()
                                               : ResponseEntity.notFound().build();
     }

     @GetMapping("/listartodospage")
    public ResponseEntity<Page<JugadorResponseDTO>> listrJugadoresPaginado(
            @PageableDefault(size=10)  Pageable pageable)
     {
         return ResponseEntity.ok(jugadorService.listarTodos(pageable));
     }

     @GetMapping("/listarporrangopage/{rango}")
    public ResponseEntity<Page<JugadorResponseDTO>> listarPorRangoPaginado(
            @PathVariable String rango,
            @PageableDefault(size=10) Pageable pageable)
     {
         return ResponseEntity.ok(jugadorService.listarPorRango(rango, pageable));
     }

     @GetMapping("/listarporemail/{email}")
    public ResponseEntity<List<JugadorResponseDTO>> listarPorEmailJPQL
             (@PathVariable String email)
     {
         return ResponseEntity.ok(jugadorService.findByEmailJPQL(email));
     }

    @GetMapping("/listarpornickname/{nickname}")
    public ResponseEntity<List<JugadorResponseDTO>> listarPorNicknameJPQL
            (@PathVariable String nickname)
    {
        return ResponseEntity.ok(jugadorService.findByNicknameJPQL(nickname));
    }

    @GetMapping("/buscaremailporid")
    public ResponseEntity<String>  findEmailByIdJPQL(@RequestParam Integer id){
        if( jugadorService.findEmailByIdJPQL(id).isPresent()){
            return ResponseEntity.ok(jugadorService.findEmailByIdJPQL(id).get());
        }
        else
            return ResponseEntity.notFound().build();

    }


}




