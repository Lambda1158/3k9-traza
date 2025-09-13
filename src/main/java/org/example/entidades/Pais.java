package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Pais {
    private Long id;
    private String nombre;
    @Builder.Default
    private Set<Provincia> provincias = new HashSet<>();

    @Override
    public String toString()
    {
        return "Pais{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                "}";
    }

}
