

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