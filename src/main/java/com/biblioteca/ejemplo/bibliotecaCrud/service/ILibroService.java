package com.biblioteca.ejemplo.bibliotecaCrud.service;


import com.biblioteca.ejemplo.bibliotecaCrud.dto.LibroDTO;
import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorLibroEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.projection.AutorProjection;

import java.util.List;
import java.util.Map;

public interface ILibroService {

    LibroDTO save(LibroDTO libroDTO);
    LibroDTO getById(Long id);

    List<LibroDTO> getAll();

    LibroDTO update(Long id,LibroDTO libroDTO);

    void deleteById(Long id);

    List<AutorLibroEntity> addAutor(Long autorId, Long libroId);

    Map<LibroDTO,List<AutorProjection>> getLibrosAutorByLibroId(Long libroId);

}
