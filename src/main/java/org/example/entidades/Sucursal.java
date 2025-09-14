package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "empresa")
@SuperBuilder
public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private Boolean es_Casa_Matriz;

    private Domicilio domicilio;
    private Empresa empresa;

}
