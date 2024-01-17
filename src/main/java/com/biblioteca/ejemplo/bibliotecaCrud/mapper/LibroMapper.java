package com.biblioteca.ejemplo.bibliotecaCrud.mapper;

import com.biblioteca.ejemplo.bibliotecaCrud.dto.LibroDTO;
import com.biblioteca.ejemplo.bibliotecaCrud.model.LibroEntity;
import org.springframework.stereotype.Component;


@Component
public class LibroMapper {


    public LibroEntity libroDTO2Entity(Long id,LibroDTO dto){
     return LibroEntity.builder().
             id(id).
             titulo(dto.getTitulo()).
             isbn(dto.getIsbn()).
             anioDePublicacion(dto.getAnioDePublicacion()).
             build();

    }

    public LibroDTO libroEntity2DTO(LibroEntity entity){

        return LibroDTO.builder().
                titulo(entity.getTitulo()).
                isbn(entity.getIsbn()).
                anioDePublicacion(entity.getAnioDePublicacion()).
                build();
    }

}
