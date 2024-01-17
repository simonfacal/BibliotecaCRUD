package com.biblioteca.ejemplo.bibliotecaCrud.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface AutorProjection {
    String getNombre();
    String getApellido();
    @Value("#{target.fecha_nacimiento}")
    LocalDate getFechaNacimiento();


}
