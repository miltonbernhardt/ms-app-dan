INSERT INTO ms_pedido.unidad (id_unidad, descripcion)
VALUES (100, 'unidad'),
       (101, 'metro'),
       (102, 'kilo'),
       (103, 'litro');

INSERT INTO ms_pedido.producto (id_producto, descripcion, precio, nombre)
VALUES (100, 'Clavos de 3mm', 34, 'Clavo'),
       (101, 'Planchas de madera de 2cm de espesor', 100, 'Plancha madera'),
       (102, 'Ladrillos de barro premium', 75, 'Ladrillo premium'),
       (103, 'Chapa de aluminio', 1000, 'Chapa'),
       (104, 'Vigas de acero reforzado', 34, 'Viga');

INSERT INTO ms_pedido.obra (id_obra, descripcion)
VALUES (100, 'Puente colgante'),
       (101, 'Nuevo shopping'),
       (102, 'Departamentos'),
       (103, 'Complejo residencial'),
       (104, 'Puente');

INSERT INTO ms_pedido.pedido (id_pedido, fecha_pedido , id_obra, estado)
VALUES (100, '2021-05-04', 100, 0),
       (101, '2021-05-04', 101, 1),
       (102, '2021-05-04', 102, 2),
       (103, '2021-05-04', 103, 4),
       (104, '2021-05-04', 104, 5);

INSERT INTO ms_pedido.detalle_pedido (id_detalle, cantidad, precio, id_producto, id_pedido)
VALUES (100, 3, 123.7, 100, 100),
       (101, 7, 154, 101, 101),
       (102, 9, 654.6, 102, 102),
       (103, 10, 543.7, 103, 103),
       (104, 1, 756.3, 104, 104);