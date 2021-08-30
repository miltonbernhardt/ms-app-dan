INSERT INTO ms_producto.unidad (id_unidad, descripcion)
VALUES (100, 'unidad'),
       (101, 'metro'),
       (102, 'kilo'),
       (103, 'litro');

INSERT INTO ms_producto.material (id_material, descripcion, nombre, precio, stock_actual, stock_minimo, id_unidad)
VALUES (100, 'Clavos de 3mm', 'Clavo', 10, 100, 20, 100),
       (101, 'Planchas de madera de 2cm de espesor', 'Plancha madera', 500, 30, 5, 101),
       (102, 'Ladrillos de barro premium', 'Ladrillo premium', 75, 3000, 500, 100),
       (103, 'Chapa de aluminio', 'Chapa', 1000, 25, 5, 101),
       (104, 'Vigas de acero reforzado', 'Viga', 34, 10, 2, 100);

INSERT INTO ms_producto.provision (id_provision, fecha_provision)
VALUES (100, '2020-01-01'),
       (101, '2020-01-02'),
       (102, '2020-01-03'),
       (103, '2020-01-04'),
       (104, '2020-01-05');

INSERT INTO ms_producto.detalle_pedido (id_detalle_pedido, cantidad, id_material)
VALUES (100, 6, 100),
       (101, 6, 101),
       (102, 6, 102),
       (103, 6, 103),
       (104, 6, 104);

INSERT INTO ms_producto.detalle_provision (id_detalle_provision, cantidad, id_material, id_provision)
VALUES (100, 5, 100, 100),
       (101, 10, 101, 101),
       (102, 6, 102, 102),
       (103, 18, 103, 103),
       (104, 15, 104, 104);

INSERT INTO ms_producto.movimiento_stock (id_movimiento_stock, cantidad_entrada, cantidad_salida, fecha, id_detalle_pedido, id_detalle_provision, id_material)
VALUES (100, 10, 5, '2020-01-09', 100, 100, 100),
       (101, 14, 51, '2020-01-10', 101, 101, 101),
       (102, 155, 234, '2020-01-11', 102, 102, 102),
       (103, 123, 1, '2020-01-12', 103, 103, 103),
       (104, 12, 8, '2020-01-13', 104, 104, 104);
      