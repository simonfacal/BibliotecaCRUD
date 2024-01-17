package com.biblioteca.ejemplo.bibliotecaCrud.repository;


import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorLibroEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.projection.AutorProjection;
import com.biblioteca.ejemplo.bibliotecaCrud.projection.LibroProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IAutorLibroRepository extends JpaRepository<AutorLibroEntity,Long> {
    List<AutorLibroEntity> findByAutorIdAndLibroId(Long autorId,Long libroId);


    @Query(value = "SELECT l.titulo,l.isbn,l.anio_de_publicacion FROM libros l join autor_libro_relation al on al.libro_id=l.id WHERE al.autor_id=:autorId",nativeQuery = true)
    List<LibroProjection>getLibrosByAutorId(Long autorId);

    @Query(value = "SELECT a.nombre,a.apellido,a.fecha_nacimiento FROM autores a join autor_libro_relation al on al.autor_id=a.id WHERE al.libro_id=:libroId",nativeQuery = true)
    List<AutorProjection>getAutoresByLibroId(Long libroId);

}
