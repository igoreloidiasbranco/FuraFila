create table clientes(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar (14) not null,
    telefone varchar(20) not null,
    email varchar(100) not null unique,
    datanascimento varchar (10),
    logradouro varchar (100) not null,
    numero varchar (20),
    complemento varchar (100),
    bairro varchar (100) not null,
    cep varchar (9) not null,
    cidade varchar (100) not null,
    uf char (2),

    primary key (id)

);