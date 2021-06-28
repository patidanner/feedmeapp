# FeedMe!
FeedMe é uma aplicação SpringBoot que lê um determinado tipo de arquivo de texto separado por espaços em branco, e os armazena em um banco de dados PostgreSQL.
Faça upload do arquivo via navegador, através de sua singela UI (é um projeto com foco em back-end, nada chique), ou use o arquivo de exemplo já disponível.
FeedMe está containerizado e pronto para ser executado via Docker!

Também disponível pgAdmin 4 para acessar o postgres.

Informações de compilação: java version "11.0.11" 2021-04-20 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.11+9-LTS-194)

## Pré-requisitos
* Docker

## Como executar
Na raíz do projeto, execute `docker-compose up`

## Como navegar pelo FeedMe!
### Home page: http://localhost:8080/

Faça upload do arquivo pela home page e submit. Um arquivo de 7MB (50 mil linhas) leva cerca de 35 segundos para ser processado. É utilizada apenas uma transação para a persistência em banco de dados, que foi configurada para ser feita em batches de 30 inserções.

O arquivo de texto deve seguir o seguinte template de colunas:
```
CPF                PRIVATE     INCOMPLETO  DATA DA ÚLTIMA COMPRA TICKET MÉDIO          TICKET DA ÚLTIMA COMPRA LOJA MAIS FREQUÊNTE LOJA DA ÚLTIMA COMPRA
000.000.00.00      1           1           00/00/00              00,00                 00,00                   00000/00            00000/00
```

### pgAdmin 4: http://localhost:5050/

Configure um novo servidor no pgAdmin 4 da seguinte maneira:
- Host/name address: db
- Port: 5432
- Password: admin

e visualize o banco de dados feedme.

O banco de dados é recriado toda ver que a aplicação inicializa. Cada submit de arquivo é do tipo update.
