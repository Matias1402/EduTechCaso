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
    private Integer idDelCurso;

    @Column(unique = true, nullable = false)
    private String ISBN;

    @Column(nullable = false)
    private String Nombre_Curso;


}
