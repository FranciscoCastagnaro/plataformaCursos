package com.uade.tpo.plataformaCursos.entity;

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
@Table(name = "cursos")
public class Curso {
    
    // Constructor obligatorio para JPA
    public Curso (String descripcion,
                  String fechaInicio, 
                  Long categoria, 
                  int maxVacantes,
                  Profesor profesor) 
    {

        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.categoria = categoria;
        this.maxVacantes = maxVacantes;
        this.vacantesDisponibles = maxVacantes; 
        this.profesor = profesor;

    }

    @Id // Este atributo es mi primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Autoincremental
    private Long id;

    @Column
    private String descripcion;

    @Column
    private String fechaInicio;

    @Column
    private Long categoria;

    @Column
    private int maxVacantes;

    @Column
    private int vacantesDisponibles;

    @Column
    private Profesor profesor;

}
