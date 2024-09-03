package com.uade.tpo.courseCommerce.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data permite que nuestra clase disponga de getters y setters
// sin necesidad de definirlos
@Data

// Entity es un bean que representa una entidad
// en nuestra base de datos
// Clase @Entity -> Tabla
// Instancia de @Entity -> Fila de nuestra tabla
@Entity
@NoArgsConstructor
@Table(name = "courses")
public class Course {
    
    public Course (String description,
                  String startDate, 
                  Category category, 
                  int maxSlots,
                  Teacher teacher) 
    {

        this.description = description;
        this.startDate = startDate;
        this.category = category;
        this.maxSlots = maxSlots;
        this.availableSlots = maxSlots; 
        this.teacher = teacher;

    }

    @Id // Este atributo es mi primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Autoincremental
    private Long id;

    @Column
    private String description;

    @Column
    private String startDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column
    private int maxSlots;

    @Column
    private int availableSlots;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses")
    private List<User> students;

}
