
---

# Aplicação de Estatísticas de Transações

Esta aplicação foi desenvolvida para processar transações financeiras e calcular estatísticas em tempo real, como o valor máximo, mínimo, soma e média das transações ocorridas no último minuto.

---

## Funcionalidades

1. **Recebimento de Transações**:
   - A aplicação recebe transações via API, contendo um valor (`BigDecimal`) e uma data/hora (`OffsetDateTime`).

2. **Filtragem das Transações**:
   - As transações são filtradas para incluir apenas aquelas que ocorreram no último minuto.

3. **Cálculo de Estatísticas**:
   - A aplicação calcula as seguintes estatísticas das transações filtradas:
     - **Valor Máximo**: O maior valor entre as transações.
     - **Valor Mínimo**: O menor valor entre as transações.
     - **Soma**: A soma de todos os valores das transações.
     - **Média**: A média dos valores das transações.
     - **Contagem**: O número total de transações.

4. **API REST**:
   - A aplicação expõe endpoints REST para:
     - Adicionar uma nova transação.
     - Recuperar as estatísticas das transações do último minuto.

---

## Como Funciona

### 1. **Fluxo de Dados**:
   - As transações são recebidas via API e armazenadas em uma lista.
   - Antes de calcular as estatísticas, a aplicação filtra as transações para incluir apenas aquelas que ocorreram no último minuto.

### 2. **Cálculo das Estatísticas**:
   - A função `calcularEstatisticas` processa a lista de transações filtradas e calcula:
     - **Valor Máximo**: Usando `.max(Comparator.naturalOrder())`.
     - **Valor Mínimo**: Usando `.min(Comparator.naturalOrder())`.
     - **Soma**: Usando `.reduce(BigDecimal.ZERO, BigDecimal::add)`.
     - **Média**: Dividindo a soma pelo número de transações.
     - **Contagem**: Usando `.size()`.

### 3. **Eficiência**:
   - A aplicação usa **Streams** do Java para processar as transações de forma eficiente.
   - A filtragem e o cálculo das estatísticas são feitos em tempo linear (\(O(n)\)), onde \(n\) é o número de transações.

---

## Estimativa de Custo da Função `calcularEstatisticas`

A função `calcularEstatisticas` tem uma complexidade de tempo de **\(O(n)\)**, onde \(n\) é o número de transações no último minuto. Isso significa que o tempo de execução aumenta linearmente com o número de transações.

### Detalhes da Estimativa:

1. **Filtragem**:
   - A filtragem das transações do último minuto é feita com uma complexidade de \(O(n)\), pois cada transação é verificada uma vez.

2. **Cálculo das Estatísticas**:
   - O cálculo do valor máximo, mínimo e soma é feito em uma única passagem pelas transações filtradas, com complexidade \(O(n)\).
   - O cálculo da média é \(O(1)\), pois envolve apenas uma divisão.

3. **Custo Total**:
   - O custo total da função é **\(O(n)\)**, onde \(n\) é o número de transações no último minuto.

### Exemplo Prático:

- Se houver **100 transações** no último minuto:
  - A função levará aproximadamente **100 operações** para filtrar e calcular as estatísticas.
- Se houver **1.000 transações**:
  - A função levará aproximadamente **1.000 operações**.

### Considerações:
- A função é eficiente para um número moderado de transações (até algumas dezenas de milhares por minuto).
- Para um número muito grande de transações (milhões por minuto), pode ser necessário otimizar o código ou usar técnicas de processamento paralelo.

---

## Como Executar a Aplicação

### Pré-requisitos:
- Java 11 ou superior.
- Maven (para gerenciamento de dependências).

### Passos:
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/aplicacao-transacoes.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd aplicacao-transacoes
   ```
3. Compile o projeto:
   ```bash
   mvn clean install
   ```
4. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

---

## Endpoints da API

### 1. **Adicionar uma Transação**:
- **Método**: `POST`
- **URL**: `/transacao`
- **Corpo da Requisição**:
  ```json
  {
    "valor": 100.50,
    "dataHora": "2023-10-01T12:34:56-03:00"
  }
  ```
- **Resposta**:
  - Status `201 Created` em caso de sucesso.
  - Status `400 Bad Request` se a transação for inválida (ex: valor negativo ou data futura).
### 2. **Deletar Transações**;
- **Método**: `DELETE`
- **URL**: `/transacao`
- **Resposta**:
  - Status `200 OK` em casso de sucesso
### 3. **Recuperar Estatísticas**:
- **Método**: `GET`
- **URL**: `/estatisticas`
- **Resposta**:
  ```json
  {
    "count": 3,
    "sum": 351.50,
    "avg": 117.17,
    "max": 200.75,
    "min": 50.25
  }
  ```

---

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento de APIs REST.
- **Maven**: Gerenciador de dependências e build.
- **Streams API**: Para processamento eficiente das transações.

---


## Contato

Se tiver dúvidas ou sugestões, entre em contato:

- **Nome**: [Marley]
- **Email**: [marleypm16@gmail.com]
- **GitHub**: [marleypm16](https://github.com/seu-usuario)

---

