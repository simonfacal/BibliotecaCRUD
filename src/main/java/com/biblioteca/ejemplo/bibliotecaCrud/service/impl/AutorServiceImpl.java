package com.biblioteca.ejemplo.bibliotecaCrud.service.impl;

import com.biblioteca.ejemplo.bibliotecaCrud.dto.AutorDTO;
import com.biblioteca.ejemplo.bibliotecaCrud.mapper.AutorMapper;
import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorLibroEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.model.LibroEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.projection.LibroProjection;
import com.biblioteca.ejemplo.bibliotecaCrud.repository.IAutorLibroRepository;
import com.biblioteca.ejemplo.bibliotecaCrud.repository.IAutorRepository;
import com.biblioteca.ejemplo.bibliotecaCrud.repository.ILibroRepository;
import com.biblioteca.ejemplo.bibliotecaCrud.service.IAutorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AutorServiceImpl implements IAutorService {
   @Autowired
   private IAutorRepository autorRepository;
   @Autowired
   private ILibroRepository libroRepository;
   @Autowired
   private IAutorLibroRepository autorLibroRepository;
   @Autowired
   private AutorMapper autorMapper;

    @Override
    public AutorDTO save(AutorDTO autorDTO) {
        AutorEntity autorEntity=autorMapper.autorDTO2Entity(null,autorDTO);
        AutorEntity entitySaved=autorRepository.save(autorEntity);
        AutorDTO result=autorMapper.autorEntity2DTO(entitySaved);
        return result;
    }

    @Override
    public AutorDTO getById(Long id) {
        AutorEntity entity=autorRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El autor con el id %d no existe ",id)));
        AutorDTO result=autorMapper.autorEntity2DTO(entity);
        return result;
    }

    @Override
    public List<AutorDTO> getAll() {
        return autorRepository.findAll().stream().map(autorMapper::autorEntity2DTO).toList();
    }

    @Override
    public AutorDTO update(Long id, AutorDTO autorDTO) {
        AutorEntity autor=autorRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El autor con el id %d no existe ",id)));
        AutorEntity autorSaved=autorMapper.autorDTO2Entity(id,autorDTO);
        autorRepository.save(autorSaved);
        AutorDTO result=autorMapper.autorEntity2DTO(autorSaved);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        if(!autorRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El autor con el id %d no existe ",id));
        }
        autorRepository.deleteById(id);
    }

    @Override
    public List<AutorLibroEntity> addLibro(Long autorId, Long libroId) {
        AutorEntity autor =autorRepository.findById(autorId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El libro con el id %d no existe ",autorId)));
        LibroEntity libro=libroRepository.findById(libroId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El libro con el id %d no existe ",libroId)));

        if(!autorLibroRepository.findByAutorIdAndLibroId(autorId,libroId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El autor con el id %d ya tiene el libro con el id %d ",autorId,libroId));
        }

        AutorLibroEntity autorLibroEntity= new AutorLibroEntity();
        autorLibroEntity.setAutor(autor);
        autorLibroEntity.setLibro(libro);
        autorLibroRepository.save(autorLibroEntity); //hago Save a mano
        autor.getLibrosList().add(autorLibroEntity);
        autorRepository.save(autor);

        return autor.getLibrosList();
    }

    @Override
    public Map<AutorDTO, List<LibroProjection>> getAutorLibrosByAutorId(Long autorId) {

        AutorDTO autorDTO=getById(autorId);
        List<LibroProjection> libros=new ArrayList<>(autorLibroRepository.getLibrosByAutorId(autorId));
        Map<AutorDTO, List<LibroProjection>> map=new HashMap<>();
        map.put(autorDTO,libros);

        return map;
    }






}
