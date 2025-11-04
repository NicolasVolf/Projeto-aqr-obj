# 📊 Guia de População do Banco de Dados - Insperscore

## 📋 Pré-requisitos

1. ✅ Aplicação Spring Boot deve ter sido executada pelo menos uma vez para criar as tabelas
2. ✅ Acesso ao banco de dados MySQL configurado
3. ✅ Cliente MySQL instalado (escolha um):
   - MySQL Workbench (GUI - Recomendado para iniciantes)
   - DBeaver (GUI - Multiplataforma)
   - Cliente de linha de comando MySQL

## 🚀 Como Executar o Script

### Opção 1: Usando MySQL Workbench (Recomendado)

1. Abra o MySQL Workbench
2. Crie uma nova conexão com os seguintes dados:
   - **Host:** `mysql-3c2b5884-projeto-2.l.aivencloud.com`
   - **Port:** `26737`
   - **Username:** `avnadmin`
   - **Password:** (sua senha do banco)
   - **Schema:** `defaultdb`
   - **SSL:** Required

3. Conecte-se ao banco de dados

4. Abra o arquivo `database-population.sql`:
   - Menu: File → Open SQL Script
   - Navegue até: `src/main/resources/database-population.sql`

5. Execute o script:
   - Clique no ícone de raio ⚡ (Execute)
   - Ou pressione `Ctrl + Shift + Enter` (Windows/Linux) / `Cmd + Shift + Enter` (Mac)

6. Verifique os resultados no painel de saída

### Opção 2: Usando DBeaver

1. Abra o DBeaver
2. Crie uma nova conexão MySQL:
   - Botão direito em "Database Navigator" → New Database Connection
   - Selecione MySQL
   - Preencha os dados de conexão (mesmos da Opção 1)
   - Teste a conexão e finalize

3. Abra o SQL Editor:
   - Botão direito na conexão → SQL Editor → Open SQL Script

4. Carregue e execute o script:
   - Abra o arquivo `database-population.sql`
   - Pressione `Ctrl + Alt + X` (Windows/Linux) / `Cmd + Alt + X` (Mac)
   - Ou clique em "Execute SQL Script"

### Opção 3: Linha de Comando (Para usuários avançados)

```bash
# 1. Navegue até a pasta do projeto
cd /Users/leona/Downloads/Projeto-aqr-obj

# 2. Execute o script SQL
mysql -h mysql-3c2b5884-projeto-2.l.aivencloud.com \
      -P 26737 \
      -u avnadmin \
      -p \
      --ssl-mode=REQUIRED \
      defaultdb < src/main/resources/database-population.sql

# 3. Digite a senha quando solicitado
```

### Opção 4: Usando Maven/Spring Boot

Você também pode criar um arquivo `data.sql` que o Spring Boot executa automaticamente:

```bash
# 1. Copie o conteúdo do script
cp src/main/resources/database-population.sql src/main/resources/data.sql

# 2. Adicione esta propriedade ao application.properties:
# spring.jpa.defer-datasource-initialization=true
# spring.sql.init.mode=always

# 3. Execute a aplicação normalmente
./mvnw spring-boot:run
```

**⚠️ ATENÇÃO:** Esta opção executará o script TODA VEZ que a aplicação iniciar!

## 📊 Dados que Serão Inseridos

### Times (6 registros)
- Flamengo, Palmeiras, Corinthians, São Paulo, Santos, Grêmio
- Cada um com seus títulos históricos

### Campeonatos (5 registros)
- Campeonato Brasileiro 2024
- Copa Libertadores 2024
- Copa do Brasil 2024
- Campeonato Paulista 2024
- Campeonato Carioca 2024

### Estádios (6 registros)
- Maracanã, Allianz Parque, Neo Química Arena, Morumbi, Vila Belmiro, Arena do Grêmio
- Cada um vinculado ao seu time

### Jogadores (18 registros)
- Jogadores famosos de cada time
- Com posições, números, idades e nacionalidades

### Partidas (17 registros)
- Partidas distribuídas entre os diferentes campeonatos
- Com datas, resultados, estádios e times

## 🔍 Validação dos Dados

Após executar o script, você pode validar se tudo foi inserido corretamente com estas consultas:

```sql
-- Ver contagem de registros em cada tabela
SELECT 'Times' AS Tabela, COUNT(*) AS Total FROM times
UNION ALL
SELECT 'Campeonatos', COUNT(*) FROM campeonatos
UNION ALL
SELECT 'Estadios', COUNT(*) FROM estadios
UNION ALL
SELECT 'Jogadores', COUNT(*) FROM jogadores
UNION ALL
SELECT 'Partidas', COUNT(*) FROM partidas;

-- Resultado esperado:
-- Times: 6
-- Campeonatos: 5
-- Estadios: 6
-- Jogadores: 18
-- Partidas: 17
```

## 🔄 Limpar e Reinserir Dados

Se precisar limpar os dados e executar o script novamente:

```sql
-- O script já inclui esta seção de limpeza no início
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
```

Depois execute o script completo novamente.

## ⚠️ Possíveis Problemas e Soluções

### Erro: "Table doesn't exist"
**Solução:** Execute a aplicação Spring Boot primeiro para criar as tabelas

### Erro: "Duplicate entry for key PRIMARY"
**Solução:** Execute a seção de limpeza antes de inserir novamente

### Erro: "Cannot add or update a child row: a foreign key constraint fails"
**Solução:** Certifique-se de executar o script na ordem correta (não execute partes isoladas)

### Erro de Conexão SSL
**Solução:** Certifique-se de que está usando `--ssl-mode=REQUIRED` na linha de comando

## 🧪 Testando as APIs após População

Após popular o banco, você pode testar as APIs com estas chamadas:

```bash
# Listar todos os times
curl http://localhost:8080/times

# Listar todos os campeonatos
curl http://localhost:8080/campeonatos

# Listar todas as partidas
curl http://localhost:8080/partidas

# Buscar um time específico
curl http://localhost:8080/times/1

# Buscar jogadores de um time
curl http://localhost:8080/jogadores?timeId=1
```

Ou acesse o Swagger UI em: `http://localhost:8080/swagger-ui.html`

## 📝 Observações Importantes

1. O script usa IDs específicos (1, 2, 3...) para facilitar os relacionamentos
2. As tabelas de junção (Many-to-Many) são populadas por último
3. Os dados são fictícios mas baseados em times reais do futebol brasileiro
4. As datas das partidas estão em 2024
5. O script inclui consultas de validação no final

## 💡 Dica

Para facilitar o desenvolvimento, você pode manter este script e executá-lo sempre que precisar resetar o banco de dados para um estado conhecido e consistente.

---

**Arquivo do Script:** `src/main/resources/database-population.sql`

**Data de Criação:** Novembro 2024

