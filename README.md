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
Na raíz do projeto, execute `docker-compose up --build`

## Como navegar pelo FeedMe!
### Home page: http://localhost:8080/

Faça upload do arquivo pela home page e submit. É utilizada apenas uma transação para a persistência em banco de dados, que foi configurada para ser feita em batches de 30 inserções. Estatísticas estão disponíveis em console log. 

O arquivo de texto deve seguir o seguinte template de colunas:
```
CPF                PRIVATE     INCOMPLETO  DATA DA ÚLTIMA COMPRA TICKET MÉDIO          TICKET DA ÚLTIMA COMPRA LOJA MAIS FREQUÊNTE LOJA DA ÚLTIMA COMPRA
000.000.00.00      1           1           00/00/00              00,00                 00,00                   00000/00            00000/00
```

### pgAdmin 4: http://localhost:5050/
Para logar no pgAdmin, utilize as credenciais:
- Username: admin@admin.com
- Password: admin

Configure um novo servidor no pgAdmin 4 da seguinte maneira:
- Host/name address: db
- Port: 5432
- Password: admin

e visualize o banco de dados feedme.

O banco de dados é recriado toda ver que a aplicação inicializa. Cada submit de arquivo é do tipo update.
O banco de dados consiste em uma tabela de staging para armazenar os dados do arquivo de texto

![image](https://user-images.githubusercontent.com/84414033/123719305-9cd6a880-d857-11eb-84bd-073a3f258e97.png)

