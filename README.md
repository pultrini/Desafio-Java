# Desafio Tecnico visto do Youtube do Fiasco
## ⚙️ 1. Setup e Configuração Inicial

- [x]  Gerar o projeto no Spring Initializr (Java 17+, Maven).
- [x]  Adicionar as dependências: Spring Web, Validation e Lombok.
- [x]  Configurar o arquivo application.yml adicionando a variável de tempo (ex: estatistica.segundos: 60).
- [x]  Organizar as pastas do projeto (pacotes: controller, service, dto e exception).

## 📦 2. Modelagem de Dados (DTOs)

- [x]  Criar o DTO de Entrada (TransacaoRequest): Deve conter o valor e a dataHora.
- [x]  Pesquisar e definir o tipo correto para dataHora que aceite o padrão ISO 8601 com fuso horário UTC (o "Z" no final).
- [x]  Criar o DTO de Saída (EstatisticaResponse): Deve conter count, sum, avg, min e max.

## 🧠 3. Regra de Negócio: Transações (Service)

- [x]  Criar a classe TransacaoService.
- [x]  Criar uma estrutura de dados em memória (Lista) para armazenar as transações.
- [x]  Pesquisa importante: Como garantir que essa lista seja Thread-Safe (segura para requisições simultâneas) no Java?
- [x]  Criar o método de adicionar transação na lista.
- [x]  Implementar a validação: A transação não pode acontecer no futuro.
- [x]  Implementar a validação: O valor da transação não pode ser menor que 0 (negativo).
- [x]  Criar o método para limpar/deletar todas as transações da lista.

## 📊 4. Regra de Negócio: Estatísticas (Service)

- [x]  Criar a classe EstatisticaService.
- [x]  Fazer o Spring ler a variável de segundos definida no application.yml e injetar nesta classe.
- [x]  Criar o método que calcula as estatísticas.
- [x]  Pesquisa importante: Como usar a API de Streams do Java para filtrar as transações que aconteceram apenas dentro da janela de tempo (últimos 60 segundos)?
- [x]  Pesquisa importante: Como calcular soma, média, mínimo, máximo e quantidade de uma só vez usando DoubleSummaryStatistics.
- [x]  Garantir que, se a lista filtrada estiver vazia, os valores retornados sejam todos 0.0.

## 🌐 5. Exposição da API (Controllers)

- [x]  Criar a classe TransacaoController mapeada para a rota /transacao.
- [x]  Criar o endpoint POST que recebe o JSON, chama o Service e retorna o status 201 Created.
- [x]  Criar o endpoint DELETE que chama o método de limpar do Service e retorna o status 200 OK.
- [x]  Criar a classe EstatisticaController mapeada para a rota /estatistica.
- [x]  Criar o endpoint GET que chama o serviço de estatísticas e retorna o JSON com os cálculos e status 200 OK.

## 🚨 6. Tratamento de Erros e Validações

- [x]  Pesquisa importante: Como usar @ExceptionHandler ou @ControllerAdvice no Spring Boot.
- [x]  Configurar para retornar 422 Unprocessable Entity quando a data for no futuro ou valor for menor que zero.
- [ ]  Configurar para retornar 400 Bad Request caso o Spring não consiga entender o JSON enviado (exemplo: JSON mal formatado).

## 🧪 7. Testes e Validação Final

- [ ]  Rodar a aplicação (./mvnw spring-boot:run).
- [ ]  Fazer um POST válido e verificar se retorna 201.
- [ ]  Fazer um POST com data no futuro e verificar se retorna 422.
- [ ]  Fazer um GET /estatistica logo em seguida e verificar se os cálculos batem.
- [ ]  Esperar 60 segundos, fazer um GET /estatistica novamente e verificar se os valores voltaram todos para zero.
- [ ]  Fazer um DELETE e verificar se a lista foi limpa.
- [ ]  Rodar o comando de build (./mvnw clean install) e garantir que o .jar é gerado com sucesso na pasta target.