package com.biblioteca.ejemplo.bibliotecaCrud.repository;

import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAutorRepository extends JpaRepository<AutorEntity,Long> {
}
