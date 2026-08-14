package edu.isp63.prog2.gamer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


//  los reconrd son inmutables y el compilador genera automaticamente
// geters, constructores, equals y hashcode
// los atributos  se ponen como argumentos

public record JugadorCreateDTO(
     @NotBlank( message = "El nickname no puede estar vacío")
     String nickname,

     @Email ( message = "Debe ingresar un  email válido")
     String email,

     @NotBlank( message = "La contraseña no puede estar vacío")
     @Size( min = 5, max = 10, message = "La contraseña debe tener entre" +
             " 5 y 10  caracteres " )
     String password
) {}
