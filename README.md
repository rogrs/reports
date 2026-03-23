# Spring Boot + JasperReports para relatórios dinâmicos

Aplicação Spring Boot para geração de relatórios dinâmicos em PDF com JasperReports.

## O que esta API entrega

- geração de relatórios em PDF a partir de **template Jasper JRXML**;
- escolha da **fonte de dados** em tempo de execução:
  - coleção JSON enviada no corpo da requisição;
  - conexão JDBC informada pela aplicação cliente;
- autenticação de aplicações REST por **token Bearer**;
- endpoint para descoberta de templates padrão do projeto.

## Fluxo de autenticação

1. Solicite um token em `POST /api/auth/token`.
2. Use o valor retornado em `Authorization: Bearer <token>`.
3. Chame os endpoints protegidos em `/api/**`.

Credenciais padrão de demonstração:

- `clientId`: `report-client`
- `clientSecret`: `report-secret`

## Exemplo para obter token

```bash
curl -X POST http://localhost:8080/api/auth/token   -H 'Content-Type: application/json'   -d '{
    "clientId": "report-client",
    "clientSecret": "report-secret"
  }'
```

## Exemplo de relatório com coleção JSON

```bash
curl -X POST http://localhost:8080/api/reporting/render   -H 'Authorization: Bearer reports-api-token-123'   -H 'Content-Type: application/json'   --output relatorio.pdf   -d '{
    "reportName": "resumo-executivo",
    "template": {
      "sourceType": "CLASSPATH",
      "value": "reports/templates/executive-summary.jrxml"
    },
    "parameters": {
      "REPORT_TITLE": "Resumo executivo",
      "SUMMARY_TEXT": "Indicadores enviados pela API cliente"
    },
    "data": [
      {"label": "Pedidos processados", "value": "1520"},
      {"label": "Receita total", "value": "R$ 81.340,00"},
      {"label": "Tempo médio", "value": "02:14 min"}
    ]
  }'
```

## Exemplo de relatório com banco de dados externo

O template `reports/templates/dynamic-list.jrxml` usa o parâmetro Jasper `REPORT_SQL`. Assim a aplicação cliente pode escolher o banco, a consulta e o modelo.

```bash
curl -X POST http://localhost:8080/api/reporting/render   -H 'Authorization: Bearer reports-api-token-123'   -H 'Content-Type: application/json'   --output indicadores.pdf   -d '{
    "reportName": "indicadores-jdbc",
    "template": {
      "sourceType": "CLASSPATH",
      "value": "reports/templates/dynamic-list.jrxml"
    },
    "dataSource": {
      "jdbcUrl": "jdbc:h2:mem:testdb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
      "username": "sa",
      "password": "",
      "driverClassName": "org.h2.Driver"
    },
    "parameters": {
      "REPORT_TITLE": "Indicadores do banco",
      "REPORT_SQL": "select ''Faturamento'' as label, ''100000'' as value union all select ''Clientes'', ''450''"
    }
  }'
```

## Templates

Os templates padrão ficam em `src/main/resources/reports/templates`.
Você pode:

- reutilizar um template do classpath;
- enviar um JRXML inline no campo `template.value` com `sourceType=INLINE`.

## Executando localmente

```bash
./mvnw spring-boot:run
```

## Banco local

O perfil `dev` continua configurado com H2 para facilitar testes locais. Ajuste `application-dev.properties` ou envie uma conexão JDBC própria na requisição quando quiser consultar outra base.
