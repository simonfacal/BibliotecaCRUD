package com.biblioteca.ejemplo.bibliotecaCrud.controller;


import com.biblioteca.ejemplo.bibliotecaCrud.dto.AutorDTO;
import com.biblioteca.ejemplo.bibliotecaCrud.model.AutorLibroEntity;
import com.biblioteca.ejemplo.bibliotecaCrud.projection.LibroProjection;
import com.biblioteca.ejemplo.bibliotecaCrud.service.IAutorService;
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
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private IAutorService autorService;

    @PostMapping
    public ResponseEntity<AutorDTO> save(@Valid @RequestBody AutorDTO autorDTO){
        return new ResponseEntity<AutorDTO>(autorService.save(autorDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> getById(@PathVariable Long id){
        return new ResponseEntity<AutorDTO>(autorService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> getAll(){
        return new ResponseEntity<List<AutorDTO>>(autorService.getAll(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> update(@PathVariable Long id,@Valid @RequestBody AutorDTO autorDTO){
        return new ResponseEntity<AutorDTO>(autorService.update(id,autorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        autorService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{autorId}/{libroId}")
    public ResponseEntity<List<AutorLibroEntity>> addLibro(@PathVariable Long autorId,@PathVariable Long libroId){
        return new ResponseEntity<List<AutorLibroEntity>>(autorService.addLibro(autorId,libroId), HttpStatus.OK);
    }


    @GetMapping("/completos/{autorId}")
    public ResponseEntity<Map<AutorDTO, List<LibroProjection>>> getAutorLibrosByAutorId(@PathVariable Long autorId) {
        return new ResponseEntity<Map<AutorDTO, List<LibroProjection>>>(autorService.getAutorLibrosByAutorId(autorId), HttpStatus.OK);

    }


}
