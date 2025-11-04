package com.pelo.insperscore.partidas;

import com.pelo.insperscore.campeonatos.Campeonatos;
import com.pelo.insperscore.estadios.Estadios;
import com.pelo.insperscore.times.Times;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Partidas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate data;
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "campeonato_id")
    private Campeonatos campeonato;

    @ManyToOne
    @JoinColumn(name = "estadio_id")
    private Estadios estadio;

    @ManyToOne
    @JoinColumn(name = "mandante_id")
    private Times timeMandante;

    @ManyToOne
    @JoinColumn(name = "visitante_id")
    private Times timeVisitante;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Campeonatos getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonatos campeonato) {
        this.campeonato = campeonato;
    }

    public Times getTimeMandante() {
        return timeMandante;
    }

    public void setTimeMandante(Times timeMandante) {
        this.timeMandante = timeMandante;
    }

    public Times getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(Times timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public Estadios getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadios estadio) {
        this.estadio = estadio;
    }

    public Integer getId() {
        return id;
    }

}
