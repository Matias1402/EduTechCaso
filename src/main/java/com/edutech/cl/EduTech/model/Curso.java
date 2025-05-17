package com.edutech.cl.EduTech.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDecurso;

    @Column(unique = true, nullable = false)
    private String Isbn;

    @Column(nullable = false)
    private String Nombre_Curso;


}
