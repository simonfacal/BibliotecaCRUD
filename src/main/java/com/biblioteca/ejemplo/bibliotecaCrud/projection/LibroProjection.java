package com.biblioteca.ejemplo.bibliotecaCrud.projection;


import org.springframework.beans.factory.annotation.Value;

public interface LibroProjection {
    String getTitulo();
    String getIsbn();
    @Value("#{target.anio_de_publicacion}")
    String getAnioDePublicacion();

}
