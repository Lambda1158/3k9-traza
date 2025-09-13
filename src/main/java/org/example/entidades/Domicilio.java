package org.example.entidades;


import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "localidad")
@SuperBuilder
public class Domicilio {
    private Long id;
    private String Nombre;
    private Integer numbero;
    private Integer cp;

    private Localidad localidad;
}
