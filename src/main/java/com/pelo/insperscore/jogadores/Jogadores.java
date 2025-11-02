package com.pelo.insperscore.jogadores;

import com.pelo.insperscore.times.Times;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jogadores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String posicao;
    private Integer numero;
    private List<String> titulos;
    private Integer idade;
    private String nacionalidade;
    @ManyToOne
    @JoinColumn(name = "time_id")
    private Times time;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public List<String> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<String> titulos) {
        this.titulos = titulos;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Times getTime() {
        return time;
    }

    public void setTime(Times time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }
}
