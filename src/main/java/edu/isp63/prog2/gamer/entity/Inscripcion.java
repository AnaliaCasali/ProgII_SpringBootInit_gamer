package edu.isp63.prog2.gamer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="inscripciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="jugador_id", nullable=false,
              foreignKey =
              @ForeignKey(
              foreignKeyDefinition =
                 "FOREIGNKEY=jugador_id REFERENCES jugadores(id)" +
                         " ON DELETE CASCADE " ))
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name="torneo_id", nullable=false,
            foreignKey =
            @ForeignKey(
                foreignKeyDefinition =
                    "FOREIGNKEY=torneo_id REFERENCES torneos(id)" +
                                    " ON DELETE CASCADE "))
    private Torneo torneo;

    @Column(name="fecha_inscripcion")
    private LocalDate fechaInscripcion;

}
