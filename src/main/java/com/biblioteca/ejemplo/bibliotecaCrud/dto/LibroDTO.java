package com.biblioteca.ejemplo.bibliotecaCrud.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroDTO {


    @NotBlank
    private String titulo;
    @Size(min=13,max=13)
    @NotBlank
    private String isbn;
    @NotBlank
    private String anioDePublicacion;

    @Override
    public String toString() {
        return "LibroDTO{" +
                "titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anioDePublicacion='" + anioDePublicacion + '\'' +
                '}';
    }
}
