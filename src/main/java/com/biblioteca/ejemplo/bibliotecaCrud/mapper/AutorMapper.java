package com.biblioteca.ejemplo.bibliotecaCrud.mapper;

import com.biblioteca.ejemplo.bibliotecaCrud.dto.AutorDTO;


import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorEntity;
import org.springframework.stereotype.Component;


@Component
public class AutorMapper {


    public AutorEntity autorDTO2Entity(Long id,AutorDTO dto){

        return AutorEntity.builder().
                id(id).
                nombre(dto.getNombre()).
                apellido(dto.getApellido()).
                fechaNacimiento(dto.getFechaNacimiento()).
                build();


    }

    public AutorDTO autorEntity2DTO(AutorEntity entity){

        return AutorDTO.builder().
                nombre(entity.getNombre()).
                apellido(entity.getApellido()).
                fechaNacimiento(entity.getFechaNacimiento()).
                build();


    }

}
