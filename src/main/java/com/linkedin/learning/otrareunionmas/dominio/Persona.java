package com.linkedin.learning.otrareunionmas.dominio;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personas")
@Getter
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String numeroEmpleado;
    private String nombre;
    private String apellidos;

    @ManyToMany
    private Set<Reunion> reuniones;

    @Override
    public String toString() {

        return "Persona{" +
                "id=" + id +
                ", numeroEmpleado='" + numeroEmpleado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }

    public Persona() {
        reuniones = new HashSet();
    }

    public Persona(String numeroEmpleado, String nombre, String apellidos) {
        this();
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
}
