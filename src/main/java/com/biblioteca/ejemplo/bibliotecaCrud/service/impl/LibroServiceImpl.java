package com.biblioteca.ejemplo.bibliotecaCrud.service.impl;


import com.biblioteca.ejemplo.bibliotecaCrud.dto.LibroDTO;
import com.biblioteca.ejemplo.bibliotecaCrud.mapper.LibroMapper;
import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorLibroEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.model.LibroEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.projection.AutorProjection;
import com.biblioteca.ejemplo.bibliotecaCrud.repository.IAutorLibroRepository;
import com.biblioteca.ejemplo.bibliotecaCrud.repository.IAutorRepository;
import com.biblioteca.ejemplo.bibliotecaCrud.repository.ILibroRepository;
import com.biblioteca.ejemplo.bibliotecaCrud.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibroServiceImpl implements ILibroService {
   @Autowired
   private ILibroRepository libroRepository;

   @Autowired
   private IAutorRepository autorRepository;

   @Autowired
   private IAutorLibroRepository autorLibroRepository;
   @Autowired
   private LibroMapper libroMapper;
    @Override
    public LibroDTO save(LibroDTO libroDTO) {
        LibroEntity entity=libroMapper.libroDTO2Entity(null,libroDTO); //pasas null el ID porque lo genera hibernate
        LibroEntity entitySaved=libroRepository.save(entity);
        LibroDTO result=libroMapper.libroEntity2DTO(entitySaved);

        return result;
    }

    @Override
    public LibroDTO getById(Long id) {
        LibroEntity entity=libroRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El libro con el id %d no existe ",id)));
        LibroDTO result=libroMapper.libroEntity2DTO(entity);

        return result;
    }

    @Override
    public List<LibroDTO> getAll() {
        return libroRepository.findAll().stream().map(libroMapper::libroEntity2DTO).toList();
    }

    @Override
    public LibroDTO update(Long id, LibroDTO libroDTO) {
        LibroEntity libro=libroRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El libro con el id %d no existe ",id)));
        LibroEntity libroSaved=libroMapper.libroDTO2Entity(id,libroDTO);
        libroRepository.save(libroSaved);
        LibroDTO result=libroMapper.libroEntity2DTO(libroSaved);

        return result;
    }

    @Override
    public void deleteById(Long id) {
        if(!libroRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El libro con el id %d no existe ",id));
        }
        libroRepository.deleteById(id);
    }


    public List<AutorLibroEntity> addAutor(Long autorId,Long libroId){
        AutorEntity autor =autorRepository.findById(autorId).orElseThrow();
        LibroEntity libro=libroRepository.findById(libroId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El libro con el id %d no existe ",libroId)));

        if(!autorLibroRepository.findByAutorIdAndLibroId(autorId,libroId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El libro con el id %d ya tiene el autor con el id %d ",libroId,autorId));
        }

        AutorLibroEntity autorLibroEntity= new AutorLibroEntity();
        autorLibroEntity.setAutor(autor);
        autorLibroEntity.setLibro(libro);
        libro.getAutoresList().add(autorLibroEntity);
        autorLibroRepository.save(autorLibroEntity);
        libroRepository.save(libro);
        return libro.getAutoresList();

    }

    @Override
    public Map<LibroDTO, List<AutorProjection>> getLibrosAutorByLibroId(Long libroId) {
        LibroDTO libroDTO=getById(libroId);
        List<AutorProjection> autores=new ArrayList<>(autorLibroRepository.getAutoresByLibroId(libroId));
        Map<LibroDTO,List<AutorProjection>> map=new HashMap<>();
        map.put(libroDTO,autores);
        return map;
    }



}
