package com.biblioteca.ejemplo.bibliotecaCrud.service;

import com.biblioteca.ejemplo.bibliotecaCrud.dto.AutorDTO;
import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorLibroEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.projection.LibroProjection;

import java.util.List;
import java.util.Map;

public interface IAutorService {
    AutorDTO save( AutorDTO  autorDTO);
    AutorDTO getById(Long id);

    List< AutorDTO> getAll();

    AutorDTO update(Long id, AutorDTO  autorDTO);

    void deleteById(Long id);

    List<AutorLibroEntity> addLibro(Long autorId, Long libroId);


    Map<AutorDTO,List<LibroProjection>> getAutorLibrosByAutorId(Long autorId);
}
