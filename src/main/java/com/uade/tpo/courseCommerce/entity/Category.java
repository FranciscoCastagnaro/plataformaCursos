package com.uade.tpo.courseCommerce.entity;

// Imports
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// Data permite que nuestra clase disponga de getters y setters
// sin necesidad de definirlos
@Data

// Entity es un bean que representa una entidad
// en nuestra base de datos
// Clase @Entity -> Tabla
// Instancia de @Entity -> Fila de nuestra tabla
@Entity

@Table(name = "categories")
public class Category {
    
    // Constructor obligatorio para JPA
    public Category () {}

    @Id // Este atributo es mi primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Autoincremental
    private Long id;

    @Column
    private String description;

}
//hola