-- ============================================
-- SCRIPT DE POPULAÇÃO DO BANCO DE DADOS
-- Sistema: Insperscore
-- Database: MySQL 8
-- ============================================
--
-- INSTRUÇÕES DE USO:
--
-- 1. Certifique-se de que a aplicação Spring Boot já foi executada pelo menos uma vez
--    para que o Hibernate crie as tabelas automaticamente (spring.jpa.hibernate.ddl-auto=update)
--
-- 2. Conecte-se ao banco de dados MySQL usando um cliente de sua escolha:
--    - MySQL Workbench
--    - DBeaver
--    - Cliente de linha de comando: mysql -h mysql-3c2b5884-projeto-2.l.aivencloud.com -P 26737 -u avnadmin -p defaultdb
--
-- 3. Execute este script SQL completo no banco de dados 'defaultdb'
--
-- 4. IMPORTANTE: As tabelas devem ser populadas nesta ordem específica devido às dependências:
--    1º Times (não tem dependências)
--    2º Campeonatos (não tem dependências)
--    3º Estadios (depende de Times)
--    4º Jogadores (depende de Times)
--    5º Partidas (depende de Campeonatos, Estadios e Times)
--    6º Tabelas de relacionamento Many-to-Many
--
-- ============================================

-- Limpar dados existentes (em ordem reversa das dependências)
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM jogadores_id;
DELETE FROM time_id;
DELETE FROM campeonatos_id;
DELETE FROM times_titulos;
DELETE FROM partidas;
DELETE FROM jogadores;
DELETE FROM estadios;
DELETE FROM campeonatos;
DELETE FROM times;

SET FOREIGN_KEY_CHECKS = 1;

-- ============================================
-- 1. POPULAR TIMES
-- ============================================
INSERT INTO times (id, nome) VALUES
(1, 'São Paulo'),
(2, 'Flamengo'),
(3, 'Corinthians'),
(4, 'Palmeiras'),
(5, 'Santos'),
(6, 'Grêmio');

-- Títulos dos Times (ElementCollection)
INSERT INTO times_titulos (times_id, titulos) VALUES
(1, 'Campeonato Brasileiro 2019'),
(1, 'Campeonato Brasileiro 2020'),
(1, 'Copa Libertadores 2019'),
(1, 'Copa do Brasil 2022'),
(2, 'Campeonato Brasileiro 2018'),
(1, 'Copa Libertadores 2020'),
(1, 'Copa Libertadores 2021'),
(1, 'Copa do Brasil 2020'),
(3, 'Campeonato Brasileiro 2015'),
(3, 'Copa Libertadores 2012'),
(3, 'Mundial de Clubes 2012'),
(1, 'Campeonato Brasileiro 2008'),
(1, 'Copa Libertadores 2005'),
(1, 'Mundial de Clubes 2005'),
(5, 'Copa Libertadores 2011'),
(5, 'Campeonato Brasileiro 2004'),
(6, 'Copa Libertadores 2017'),
(6, 'Campeonato Brasileiro 1996');

-- ============================================
-- 2. POPULAR CAMPEONATOS
-- ============================================
INSERT INTO campeonatos (id, nome) VALUES
(1, 'Campeonato Brasileiro Série A 2024'),
(2, 'Copa Libertadores 2024'),
(3, 'Copa do Brasil 2024'),
(4, 'Campeonato Paulista 2024'),
(5, 'Campeonato Carioca 2024');

-- ============================================
-- 3. POPULAR ESTADIOS
-- ============================================
INSERT INTO estadios (id, nome, time_id) VALUES
(1, 'Maracanã', 2),
(2, 'Allianz Parque', 4),
(3, 'Neo Química Arena', 3),
(4, 'Morumbi', 1),
(5, 'Vila Belmiro', 5),
(6, 'Arena do Grêmio', 6);

-- ============================================
-- 4. POPULAR JOGADORES
-- ============================================
INSERT INTO jogadores (id, nome, posicao, numero, idade, nacionalidade, time_id) VALUES
-- Flamengo
(1, 'Gabriel Barbosa', 'Atacante', 9, 27, 'Brasileiro', 2),
(2, 'Éverton Ribeiro', 'Meio-campista', 7, 35, 'Brasileiro', 2),
(3, 'Arrascaeta', 'Meio-campista', 14, 30, 'Uruguaio', 2),
(4, 'Pedro', 'Atacante', 21, 26, 'Brasileiro', 1),
-- Palmeiras
(5, 'Rony', 'Atacante', 10, 29, 'Brasileiro', 4),
(6, 'Raphael Veiga', 'Meio-campista', 23, 29, 'Brasileiro', 4),
(7, 'Dudu', 'Atacante', 7, 32, 'Brasileiro', 4),
(8, 'Weverton', 'Goleiro', 21, 36, 'Brasileiro', 4),
-- Corinthians
(9, 'Yuri Alberto', 'Atacante', 9, 23, 'Brasileiro', 3),
(10, 'Renato Augusto', 'Meio-campista', 8, 36, 'Brasileiro', 3),
(11, 'Cássio', 'Goleiro', 12, 37, 'Brasileiro', 3),
-- São Paulo
(12, 'Calleri', 'Atacante', 9, 30, 'Argentino', 1),
(13, 'Lucas Moura', 'Atacante', 7, 32, 'Brasileiro', 1),
(14, 'Rafael', 'Goleiro', 23, 34, 'Brasileiro', 1),
-- Santos
(15, 'Marcos Leonardo', 'Atacante', 9, 21, 'Brasileiro', 5),
(16, 'Lucas Braga', 'Meio-campista', 7, 26, 'Brasileiro', 5),
-- Grêmio
(17, 'Suárez', 'Atacante', 9, 37, 'Uruguaio', 6),
(18, 'Reinaldo', 'Lateral', 6, 36, 'Brasileiro', 6);

-- ============================================
-- 5. POPULAR PARTIDAS
-- ============================================
INSERT INTO partidas (id, data, resultado, campeonato_id, estadio_id, mandante_id, visitante_id) VALUES
-- Campeonato Brasileiro
(1, '2024-03-15', '2-1', 1, 1, 1, 2),
(2, '2024-03-22', '1-1', 1, 2, 2, 3),
(3, '2024-04-05', '3-0', 1, 3, 3, 4),
(4, '2024-04-12', '2-2', 1, 4, 4, 1),
(5, '2024-04-20', '1-0', 1, 5, 5, 6),
(6, '2024-05-03', '0-1', 1, 6, 6, 2),
-- Copa Libertadores
(7, '2024-05-10', '3-2', 2, 1, 1, 6),
(8, '2024-05-17', '2-0', 2, 2, 2, 5),
(9, '2024-06-01', '1-1', 2, 3, 3, 1),
-- Copa do Brasil
(10, '2024-06-15', '2-1', 3, 4, 4, 3),
(11, '2024-06-22', '0-0', 3, 1, 1, 5),
(12, '2024-07-05', '3-1', 3, 2, 2, 4),
-- Campeonato Paulista
(13, '2024-01-20', '1-0', 4, 3, 3, 4),
(14, '2024-01-27', '2-1', 4, 4, 4, 5),
(15, '2024-02-03', '1-1', 4, 5, 5, 3),
-- Campeonato Carioca
(16, '2024-01-15', '3-0', 5, 1, 1, 1),
(17, '2024-02-10', '2-2', 5, 1, 1, 1);

-- ============================================
-- 6. POPULAR RELACIONAMENTOS MANY-TO-MANY
-- ============================================

-- Relacionamento Times <-> Campeonatos (time_id)
INSERT INTO time_id (campeonatos_id, time_id) VALUES
-- Brasileirão
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
-- Libertadores
(2, 1), (2, 2), (2, 3), (2, 6),
-- Copa do Brasil
(3, 1), (3, 2), (3, 3), (3, 4), (3, 5),
-- Paulista
(4, 3), (4, 4), (4, 5),
-- Carioca
(5, 1);

-- Relacionamento Times <-> Campeonatos (campeonatos_id - lado inverso)
INSERT INTO campeonatos_id (times_id, campeonatos_id) VALUES
-- Flamengo
(2, 1), (2, 2), (2, 3), (2, 5),
-- Palmeiras
(4, 1), (4, 2), (4, 3),
-- Corinthians
(3, 1), (3, 2), (3, 3), (3, 4),
-- São Paulo
(1, 1), (1, 3), (1, 4), (1, 2),
-- Santos
(5, 1), (5, 3), (5, 4),
-- Grêmio
(6, 1), (6, 2);

-- Relacionamento Partidas <-> Jogadores (jogadores_id)
INSERT INTO jogadores_id (partidas_id, jogadores_id) VALUES
-- Partida 1: Flamengo 2x1 Palmeiras
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8),
-- Partida 2: Palmeiras 1x1 Corinthians
(2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10), (2, 11),
-- Partida 3: Corinthians 3x0 São Paulo
(3, 9), (3, 10), (3, 11), (3, 12), (3, 13), (3, 14),
-- Partida 4: São Paulo 2x2 Flamengo
(4, 12), (4, 13), (4, 14), (4, 1), (4, 2), (4, 3), (4, 4),
-- Partida 5: Santos 1x0 Grêmio
(5, 15), (5, 16), (5, 17), (5, 18),
-- Partida 6: Grêmio 0x1 Palmeiras
(6, 17), (6, 18), (6, 5), (6, 6), (6, 7), (6, 8),
-- Partida 7: Flamengo 3x2 Grêmio (Libertadores)
(7, 1), (7, 2), (7, 3), (7, 4), (7, 17), (7, 18),
-- Partida 8: Palmeiras 2x0 Santos (Libertadores)
(8, 5), (8, 6), (8, 7), (8, 8), (8, 15), (8, 16),
-- Partida 9: Corinthians 1x1 Flamengo (Libertadores)
(9, 9), (9, 10), (9, 11), (9, 1), (9, 2), (9, 3), (9, 4),
-- Partida 10: São Paulo 2x1 Corinthians (Copa do Brasil)
(10, 12), (10, 13), (10, 14), (10, 9), (10, 10), (10, 11),
-- Partida 11: Flamengo 0x0 Santos (Copa do Brasil)
(11, 1), (11, 2), (11, 3), (11, 4), (11, 15), (11, 16),
-- Partida 12: Palmeiras 3x1 São Paulo (Copa do Brasil)
(12, 5), (12, 6), (12, 7), (12, 8), (12, 12), (12, 13), (12, 14);

-- ============================================
-- VERIFICAÇÃO DOS DADOS INSERIDOS
-- ============================================

-- Contar registros em cada tabela
SELECT 'Times' AS Tabela, COUNT(*) AS Total FROM times
UNION ALL
SELECT 'Campeonatos', COUNT(*) FROM campeonatos
UNION ALL
SELECT 'Estadios', COUNT(*) FROM estadios
UNION ALL
SELECT 'Jogadores', COUNT(*) FROM jogadores
UNION ALL
SELECT 'Partidas', COUNT(*) FROM partidas
UNION ALL
SELECT 'Times-Títulos', COUNT(*) FROM times_titulos
UNION ALL
SELECT 'Times-Campeonatos (time_id)', COUNT(*) FROM time_id
UNION ALL
SELECT 'Times-Campeonatos (campeonatos_id)', COUNT(*) FROM campeonatos_id
UNION ALL
SELECT 'Partidas-Jogadores', COUNT(*) FROM jogadores_id;

-- ============================================
-- CONSULTAS DE VALIDAÇÃO
-- ============================================

-- Ver times com seus títulos
SELECT t.nome AS Time, GROUP_CONCAT(tt.titulos SEPARATOR ', ') AS Titulos
FROM times t
LEFT JOIN times_titulos tt ON t.id = tt.times_id
GROUP BY t.id, t.nome;

-- Ver partidas com times mandante e visitante
SELECT
    p.id,
    p.data,
    p.resultado,
    tm.nome AS Mandante,
    tv.nome AS Visitante,
    c.nome AS Campeonato,
    e.nome AS Estadio
FROM partidas p
JOIN times tm ON p.mandante_id = tm.id
JOIN times tv ON p.visitante_id = tv.id
JOIN campeonatos c ON p.campeonato_id = c.id
JOIN estadios e ON p.estadio_id = e.id
ORDER BY p.data;

-- Ver jogadores por time
SELECT t.nome AS Time, j.nome AS Jogador, j.posicao, j.numero
FROM jogadores j
JOIN times t ON j.time_id = t.id
ORDER BY t.nome, j.numero;

-- ============================================
-- FIM DO SCRIPT
-- ============================================

