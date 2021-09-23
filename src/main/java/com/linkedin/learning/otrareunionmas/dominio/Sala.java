package com.linkedin.learning.otrareunionmas.dominio;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "salas")
@Getter
public class Sala {

    @Id
    @Column(length = 20)
    private String id;
    private String descripcion;
    private int capacidad;
    @OneToMany(mappedBy = "sala")
    private List<Reunion> reuniones;

    @Override
    public String toString() {
        return "Sala{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }

    public Sala(String id, String descripcion, int capacidad){
        this.id = id;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
    }
}
