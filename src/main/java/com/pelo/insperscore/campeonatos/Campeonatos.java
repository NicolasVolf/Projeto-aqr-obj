package com.pelo.insperscore.campeonatos;

import com.pelo.insperscore.partidas.Partidas;
import com.pelo.insperscore.times.Times;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Campeonatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @ManyToMany(mappedBy = "campeonatos")
    private List<Times> time = new ArrayList<>();

    @OneToMany(mappedBy = "campeonato")
    private List<Partidas> partidas = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Times> getTime() {
        return time;
    }

    public void setTime(List<Times> time) {
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;
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
