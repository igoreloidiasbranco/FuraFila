create table reservas(

    id bigint not null auto_increment,
    restaurante_id bigint not null,
    cliente_id bigint not null,
    qtde int not null,
    data datetime not null,

    primary key(id),
    constraint fk_reservas_restaurante_id foreign key(restaurante_id) references restaurantes(id),
    constraint fk_reservas_cliente_id foreign key(cliente_id) references clientes(id)
)