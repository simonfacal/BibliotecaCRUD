package com.biblioteca.ejemplo.bibliotecaCrud.repository;

import com.biblioteca.ejemplo.bibliotecaCrud.model.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibroRepository extends JpaRepository<LibroEntity,Long> {
}
