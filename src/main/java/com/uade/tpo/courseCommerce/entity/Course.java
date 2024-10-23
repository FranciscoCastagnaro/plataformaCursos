package com.uade.tpo.courseCommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data permite que nuestra clase disponga de getters y setters
// sin necesidad de definirlos

// Entity es un bean que representa una entidad
// en nuestra base de datos
// Clase @Entity -> Tabla
// Instancia de @Entity -> Fila de nuestra tabla
@Data
@Entity
@NoArgsConstructor
@Table(name = "courses")
public class Course {

    public Course(String description,
            String longDescription,
            String startDate,
            Category category,
            int maxSlots,
            String teacher) {
        this.description = description;
        this.longDescription = longDescription;
        this.startDate = startDate;
        this.category = category;
        this.maxSlots = maxSlots;
        this.availableSlots = maxSlots;
        this.teacher = teacher;
        this.discount = 0;
    }

    @Id // Este atributo es mi primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Autoincremental
    private Long id;

    @Column
    private String description;

    @Column
    private String longDescription;

    @Column
    private String startDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column
    private int maxSlots;

    @Column
    private String teacher;

    @Column
    private int availableSlots;

    @Column
    private int discount;

}
