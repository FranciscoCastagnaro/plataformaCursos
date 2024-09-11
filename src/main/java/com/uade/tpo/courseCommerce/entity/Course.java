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
@NoArgsConstructor    //Genera un constructor sin parámetros
@Table(name = "courses")  //Especifica el nombre de la tabla en la base de datos a la que se mapeará esta entidad, que en este caso es courses

//este constructor se utiliza para crear instancias de Course con valores especificos
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
    
    //campos de la clase Course
    @Id // Este atributo es mi primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Autoincremental
    private Long id;

    @Column
    private String description;

    @Column
    private String startDate;
    
    //muchos cursos pueden estar asociados a una misma categoria o un mismo profesor
    @ManyToOne
    //unir en la bd a la clave foranea category
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column
    private int maxSlots;

    @Column
    private int availableSlots;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
    
    //un curso puede tener muchos estudiantes y un estudiante puedo estae escrito a muchos cursos
    @ManyToMany(mappedBy = "courses")
    private List<User> students;

}
