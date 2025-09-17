drop database if exists DB_ahorcado;
Create database DB_ahorcado;
use DB_ahorcado;

create table palabras (
    id int auto_increment primary key,
    palabra varchar(50) not null,
    pista1 varchar(255) not null,
    pista2 varchar(255) not null,
    pista3 varchar(255) not null
);
create table usuarios (
    id int auto_increment primary key,
    username varchar(50) not null unique,
    contraseña varchar(100) not null
);

select * from usuarios;
select * from palabras;