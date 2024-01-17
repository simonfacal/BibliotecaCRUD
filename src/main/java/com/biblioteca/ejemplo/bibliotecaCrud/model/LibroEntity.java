package com.biblioteca.ejemplo.bibliotecaCrud.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="libros")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LibroEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String isbn;

    @Column(name="anio_de_publicacion")
    private String anioDePublicacion;
    @OneToMany(cascade= CascadeType.REMOVE, orphanRemoval=true,mappedBy="libro")
    private List<AutorLibroEntity> autoresList;


    public LibroEntity(Long id, String titulo, String isbn, String anioDePublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.anioDePublicacion = anioDePublicacion;
        this.autoresList=new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anioDePublicacion=" + anioDePublicacion +
                ", autor=" + autoresList +
                '}';
    }
}
