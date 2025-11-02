package com.pelo.insperscore.estadios;

import com.pelo.insperscore.partidas.Partidas;
import com.pelo.insperscore.times.Times;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estadios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @OneToOne
    @JoinColumn(name = "time_id")
    private Times time;

    @OneToMany
    @JoinColumn(name = "partidas_id")
    private List<Partidas> partidas = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Times getTime() {
        return time;
    }

    public void setTime(Times time) {
        this.time = time;
    }

    public List<Partidas> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partidas> partidas) {
        this.partidas = partidas;
    }

    public Integer getId() {
        return id;
    }

}
