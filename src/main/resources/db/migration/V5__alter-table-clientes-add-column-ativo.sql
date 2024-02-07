alter table clientes add column ativo tinyint;
update clientes set ativo = 1;