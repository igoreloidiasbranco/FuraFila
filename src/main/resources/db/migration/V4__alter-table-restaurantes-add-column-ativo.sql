alter table restaurantes add column ativo tinyint;
update restaurantes set ativo = 1;