package com.pelo.insperscore.config;

import com.pelo.insperscore.campeonatos.Campeonatos;
import com.pelo.insperscore.campeonatos.CampeonatosRepository;
import com.pelo.insperscore.partidas.Partidas;
import com.pelo.insperscore.partidas.PartidasRepository;
import com.pelo.insperscore.times.Times;
import com.pelo.insperscore.times.TimesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(TimesRepository timesRepo,
                                   CampeonatosRepository campeonatosRepo,
                                   PartidasRepository partidasRepo) {
        return args -> {
            if (!partidasRepo.findAll().isEmpty()) return;

            // Times da Série A 2025
            String[] nomesTimes = {
                    "Flamengo", "Palmeiras", "Atlético-MG", "Grêmio", "São Paulo",
                    "Corinthians", "Fluminense", "Internacional", "Vasco", "Botafogo",
                    "Cruzeiro", "Athletico-PR", "Fortaleza", "Cuiabá", "Bahia",
                    "Vitória", "Juventude", "Bragantino", "Coritiba", "Goiás"
            };

            List<Times> times = new ArrayList<>();
            for (String nome : nomesTimes) {
                Times t = new Times();
                t.setNome(nome);
                t.setTitulos(new ArrayList<>());
                times.add(timesRepo.save(t));
            }

            // Campeonato
            Campeonatos brasileirao = new Campeonatos();
            brasileirao.setNome("Brasileirão Série A");
            brasileirao.setTime(new ArrayList<>(times));
            campeonatosRepo.save(brasileirao);

            List<Partidas> partidas = new ArrayList<>();
            partidas.add(criarPartida(times.get(0), times.get(1), brasileirao, LocalDate.of(2025, 11, 5)));  // Flamengo x Palmeiras
            partidas.add(criarPartida(times.get(2), times.get(3), brasileirao, LocalDate.of(2025, 11, 5)));  // Atlético-MG x Grêmio
            partidas.add(criarPartida(times.get(4), times.get(5), brasileirao, LocalDate.of(2025, 11, 6)));  // São Paulo x Corinthians
            partidas.add(criarPartida(times.get(6), times.get(7), brasileirao, LocalDate.of(2025, 11, 6)));  // Fluminense x Internacional
            partidas.add(criarPartida(times.get(8), times.get(9), brasileirao, LocalDate.of(2025, 11, 7)));  // Vasco x Botafogo
            partidas.add(criarPartida(times.get(10), times.get(11), brasileirao, LocalDate.of(2025, 11, 7))); // Cruzeiro x Athletico-PR
            partidas.add(criarPartida(times.get(12), times.get(13), brasileirao, LocalDate.of(2025, 11, 8))); // Fortaleza x Cuiabá
            partidas.add(criarPartida(times.get(14), times.get(15), brasileirao, LocalDate.of(2025, 11, 8))); // Bahia x Vitória
            partidas.add(criarPartida(times.get(16), times.get(17), brasileirao, LocalDate.of(2025, 11, 9))); // Juventude x Bragantino
            partidas.add(criarPartida(times.get(18), times.get(19), brasileirao, LocalDate.of(2025, 11, 9))); // Coritiba x Goiás

            partidasRepo.saveAll(partidas);
        };
    }

    private Partidas criarPartida(Times mandante, Times visitante, Campeonatos camp, LocalDate data) {
        Partidas p = new Partidas();
        p.setTimeMandante(mandante);
        p.setTimeVisitante(visitante);
        p.setCampeonato(camp);
        p.setData(data);
        p.setResultado("");
        return p;
    }
}
