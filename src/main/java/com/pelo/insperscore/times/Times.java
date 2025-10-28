package com.pelo.insperscore.times;

import com.pelo.insperscore.campeonatos.Campeonatos;
import com.pelo.insperscore.jogadores.Jogadores;
import com.pelo.insperscore.partidas.Partidas;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Times {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @ElementCollection
    private List<String> titulos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "campeonatos_id")
    private List<Campeonatos> campeonatos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "mandantes_id")
    private List<Partidas> partidasMandante = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "visitante_id")
    private List<Partidas> partidasVisitante = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "jogadores_id")
    private List<Jogadores> jogadores = new ArrayList<>();

    public List<Partidas> getPartidasMandante() {
        return partidasMandante;
    }

    public void setPartidasMandante(List<Partidas> partidasMandante) {
        this.partidasMandante = partidasMandante;
    }

    public List<Partidas> getPartidasVisitante() {
        return partidasVisitante;
    }

    public void setPartidasVisitante(List<Partidas> partidasVisitante) {
        this.partidasVisitante = partidasVisitante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<String> titulos) {
        this.titulos = titulos;
    }

    public List<Campeonatos> getCampeonatos() {
        return campeonatos;
    }

    public void setCampeonatos(List<Campeonatos> campeonatos) {
        this.campeonatos = campeonatos;
    }


    public List<Jogadores> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogadores> jogadores) {
        this.jogadores = jogadores;
    }
}
