

INSERT INTO ms_usuario.usuario (id_usuario, password, mail, tipo_usuario)
VALUES
    (100, 'dan2021', 'martinperez@mail.com',1),
    (101, 'dan2021', 'lizamartinez@mail.com', 1),
    (103, 'dan2021', 'luciagaribaldi@mail.com', 0),
    (104, 'dan2021', 'lucasferrero@mail.com', 0);

INSERT INTO ms_usuario.cliente (id_cliente, cuit, habilitado_online, mail, max_cuenta_corriente, razon_social, id_usuario)
VALUES
    (100, '20410923412', true, 'luciagaribaldi@mail.com', 89000, 'Lucia Garibaldi', 103),
    (101, '20378293108', true, 'lucasferrero@mail.com', 500000, 'Lucas Ferrero', 104);

INSERT INTO ms_usuario.empleado (id_empleado, mail, nombre, id_usuario)
VALUES
    (100, 'martinperez@mail.com', 'Martin Perez', 100),
    (101, 'lizamartinez@mail.com', 'Liza Martinez', 101);

INSERT INTO ms_usuario.obra (id_obra, descripcion, direccion, longitud, superficie, tipo_obra, id_cliente)
VALUES
    (100, 'Casa', '25 de Mayo 3358', 14, 120, 1, 100),
    (101, 'Reforma', 'San Martin 2552', 15, 90, 0, 100),
    (103, 'Edificio', 'Belgrano 1002', 14, 250, 2, 100),
    (104, 'Vial', 'Rivadavia 2552', 14, 100, 3, 100),
    (105, 'Casa', 'Soler 567', 23, 50, 1, 101),
    (106, 'Casa', 'Francia 2345', 23, 78, 1, 101);