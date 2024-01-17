package com.biblioteca.ejemplo.bibliotecaCrud.controller;


import com.biblioteca.ejemplo.bibliotecaCrud.dto.LibroDTO;
import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorLibroEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.projection.AutorProjection;
import com.biblioteca.ejemplo.bibliotecaCrud.service.ILibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private ILibroService libroService;
    @PostMapping
    public ResponseEntity<LibroDTO> save(@Valid @RequestBody LibroDTO libroDTO){
    return new ResponseEntity<LibroDTO>(libroService.save(libroDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> getById(@PathVariable Long id){
        return new ResponseEntity<LibroDTO>(libroService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LibroDTO>> getAll(){
        return new ResponseEntity<List<LibroDTO>>(libroService.getAll(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> update(@PathVariable Long id,@Valid @RequestBody LibroDTO libroDTO){
        return new ResponseEntity<LibroDTO>(libroService.update(id,libroDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        libroService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{libroId}/{autorId}")
    public ResponseEntity<List<AutorLibroEntity>> addAutor(@PathVariable Long libroId,@PathVariable Long autorId){
        return new ResponseEntity<List<AutorLibroEntity>>(libroService.addAutor(autorId,libroId), HttpStatus.OK);
    }

    @GetMapping("/completos/{libroId}")
    public ResponseEntity<Map<LibroDTO, List<AutorProjection>>> getAutorLibrosByLibroId(@PathVariable Long libroId){
        return new ResponseEntity<Map<LibroDTO, List<AutorProjection>>>(libroService.getLibrosAutorByLibroId(libroId), HttpStatus.OK);
    }



}
