package com.linkedin.learning.otrareunionmas.dominio;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="actas")
@Getter
public class Acta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String contenido;
    @OneToOne
    @JoinColumn(name = "reunion_id")
    private Reunion reunion;

    @Override
    public String toString() {
        return "Acta{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                '}';
    }

    public Acta() {}

    public Acta(String contenido, Reunion reunion) {
        this.contenido = contenido;
        this.reunion = reunion;
    }
}
