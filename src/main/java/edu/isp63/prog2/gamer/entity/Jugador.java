package edu.isp63.prog2.gamer.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Jugadores")
@Data   // getters setters tostring equals
@NoArgsConstructor // sin argumento
@AllArgsConstructor // con argumentos
public class Jugador {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nickname",nullable=false,length=200)
    private String nickname;

    @Email
    private String email;
    private String password;
    private String rango="Principiante";
}
