create table usuario(
    idusuario serial primary key,
    nombre varchar(100),
    apellido varchar(100),
    username varchar(100),
    pass varchar(100),
    status boolean
);

create table rol(
    id_rol serial primary key,
    rol varchar(10)
);

create table usuario_rol(
    id_usuario_rol serial primary key,
    idusuario int,
    id_rol int
);



alter table usuario_rol add constraint fk_usuario_usuario_rol foreign key (idusuario) references usuario(idusuario);
alter table usuario_rol add constraint fk_rol_usuario_rol foreign key (id_rol) references rol(id_rol);
alter table usuario alter column status type varchar(20);

insert into rol values (default, 'ADMIN'), (default, 'USER');

select * from rol;
select * from usuario;
select * from usuario_rol;


insert into usuario values (default, 'gerardo', 'mira', 'gerar', '$2a$12$3LfHgmf16ZMIe3eMi5SpwuCTE5CHM8ZbMcqc5GDCC8RIHbBtg9pSO
', 'VERIFIED')

insert into usuario values (default, 'admin', 'admin', 'admin', '$2a$12$FAIQgfySc2hBQLHZgxkpvO/veX2yEeamJ1/Kh0LdYF7HfPL0c8evu', 'VERIFIED')

insert into usuario_rol values (default, 1, 2)


select username, pass, '1' as enabled from usuario where username='admin' and status = 'VERIFIED'
	