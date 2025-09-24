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

Delimiter $$
create procedure sp_AgregarUsuario(
    in p_username varchar(50),
    in p_contrasena varchar(100)
)
begin
    insert into usuarios (username, contraseña)
    Values (p_username, p_contrasena);
End $$
Delimiter ;

call sp_AgregarUsuario('Alan', '58319705');
call sp_AgregarUsuario('Brenda', 'abc123');
call sp_AgregarUsuario('Carlos', 'qwerty');
call sp_AgregarUsuario('Diana', '12345678');
call sp_AgregarUsuario('Esteban', 'contraseñaSegura');

Delimiter $$
create procedure sp_AgregarPalabra(
    in p_palabra varchar(50),
    in p_pista1 varchar(255),
    in p_pista2 varchar(255),
    in p_pista3 varchar(255)
)
begin
    insert into palabras (palabra, pista1, pista2, pista3)
    values (p_palabra, p_pista1, p_pista2, p_pista3);
end $$
Delimiter ;

call sp_AgregarPalabra('pirámide', 'Construcción antigua', 'Egipto', 'Forma triangular');
call sp_AgregarPalabra('planeta', 'Cuerpo celeste', 'Gira alrededor del sol', 'Tierra es uno');
call sp_AgregarPalabra('volcán', 'Relacionado con lava', 'Montaña', 'Puede hacer erupción');


select * from usuarios;
select * from palabras;