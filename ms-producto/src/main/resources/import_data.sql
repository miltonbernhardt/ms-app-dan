INSERT INTO ms_producto.unidad (id_unidad, descripcion)
VALUES (100, 'unidad'),
       (101, 'metro'),
       (102, 'kilo'),
       (103, 'litro');

INSERT INTO ms_producto.material (id_material, descripcion, nombre, precio, stock_actual, stock_minimo, id_unidad)
VALUES (100, 'Clavos de 3mm', 'Clavo', 10, 100, 20, 100),
       (101, 'Planchas de madera de 2cm de espesor', 'Plancha madera', 500, 30, 5, 101),
       (102, 'Ladrillos de barro premium', 'Ladrillo premium', 75, 3000, 500, 100),
       (103, 'Chapa de aluminio', 'Chapa', 1000, 25, 5, 101);

-- INSERT INTO ms_producto.provision (id_provision, fecha_provision) VALUES
--     (),
--     (),
--     (),
--     ();

-- INSERT INTO ms_producto.detalle_pedido (id_detalle_pedido, cantidad, id_material) VALUES
--     (),
--     (),
--     (),
--     ();

-- INSERT INTO ms_producto.detalle_provision (id_detalle_provision, cantidad, id_material, id_provision) VALUES
--     (),
--     (),
--     (),
--     ();

-- INSERT INTO ms_producto.movimiento_stock (id_movimiento_stock, cantidad_entrada, cantidad_salida, fecha, id_detalle_pedido, id_detalle_provision, id_material)  VALUES
--     (),
--     (),
--     (),
--     ();