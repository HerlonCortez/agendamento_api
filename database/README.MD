# anti-fraude-db - DB
O `init.sql` possui todo o código necessário para subir o banco de dados, para facilitar, pode ser gerado uma build do docker com base no Dockerfile contida nesse repositório, assim sendo mais fácil de rodar a instância na máquina desejada, principalmente para fins de testes.
Abaixo estão os comandos necessários para rodar:

- É necessário ter o Docker instalado na máquina desejada
- `docker build . -t db_agendamento`
- `docker run -p 5432:5432 -e POSTGRES_PASSWORD="dev123456" --name=db_agendamento db_agendamento`

> Written with [StackEdit](https://stackedit.io/).