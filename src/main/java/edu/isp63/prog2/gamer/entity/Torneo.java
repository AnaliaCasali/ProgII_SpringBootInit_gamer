package edu.isp63.prog2.gamer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Entity
@Table(name="Torneos")
@Data // getters setters tostring equals hashcode
@NoArgsConstructor // constructor sin los arg
@AllArgsConstructor // constructor con todos los argumentos
public class Torneo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "nombre_torneo", nullable = false ,length = 100)
    @NotBlank
    @NonNull
    private String nombreTorneo;

    @Column(name = "nombre_juego", nullable = false ,length = 50)
    @NotBlank
    @NonNull
    private String nombreJuego;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    private int cupo;

    @Column(length = 30, nullable = false)
    @NotBlank
    @NonNull
    private String plataforma;
}
