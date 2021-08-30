INSERT INTO ms_usuario.usuario (id_usuario, password, username, tipo_usuario)
VALUES (100, '$2a$10$7tm4QB5I8tgjQ8tAKQWwHO..LT6ZKwoSYNNc6qUt.E1XKqftbuYd.', 'martinperez', 1),
       (101, '$2a$10$7tm4QB5I8tgjQ8tAKQWwHO..LT6ZKwoSYNNc6qUt.E1XKqftbuYd.', 'lizamartinez', 1),
       (102, '$2a$10$7tm4QB5I8tgjQ8tAKQWwHO..LT6ZKwoSYNNc6qUt.E1XKqftbuYd.', 'juancruz', 1),
       (103, '$2a$10$7tm4QB5I8tgjQ8tAKQWwHO..LT6ZKwoSYNNc6qUt.E1XKqftbuYd.', 'luciagaribaldi', 0),
       (104, '$2a$10$7tm4QB5I8tgjQ8tAKQWwHO..LT6ZKwoSYNNc6qUt.E1XKqftbuYd.', 'lucasferrero', 0),
       (1000, '$2a$10$7tm4QB5I8tgjQ8tAKQWwHO..LT6ZKwoSYNNc6qUt.E1XKqftbuYd.', 'dan2021', 1);

INSERT INTO ms_usuario.cliente (id_cliente, cuit, habilitado_online, mail, max_cuenta_corriente, razon_social,
                                id_usuario)
VALUES (100, '20410923412', true, 'luciagaribaldi@mail.com', 89000, 'Lucia Garibaldi', 103),
       (101, '20378293108', true, 'lucasferrero@mail.com', 500000, 'Lucas Ferrero', 104);

INSERT INTO ms_usuario.empleado (id_empleado, mail, nombre, id_usuario)
VALUES (100, 'martinperez@mail.com', 'Martin Perez', 100),
       (101, 'lizamartinez@mail.com', 'Liza Martinez', 101),
       (102, 'juancruz@mail.com', 'Juan Cruz', 102),
       (1000, 'dan2021', 'dan2021', 1000);

INSERT INTO ms_usuario.obra (id_obra, descripcion, direccion, latitud, longitud, superficie, tipo_obra, id_cliente)
VALUES (100, 'Casa', '25 de Mayo 3358', 31, 14, 120, 1, 100),
       (101, 'Reforma', 'San Martin 2552', 2415, 46, 90, 0, 100),
       (103, 'Edificio', 'Belgrano 1002', 14, 14, 250, 2, 100),
       (104, 'Vial', 'Rivadavia 2552', 16, 14, 100, 3, 100),
       (105, 'Casa', 'Soler 567', 23, 11, 50, 1, 101),
       (106, 'Casa', 'Francia 2345', 23, 22, 78, 1, 101);