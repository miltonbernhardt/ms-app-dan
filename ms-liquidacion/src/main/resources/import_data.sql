INSERT INTO ms_liquidacion.empleado (id_empleado)
VALUES (100),
       (101),
       (102),
       (103),
       (104);

INSERT INTO ms_liquidacion.pedido (id_pedido)
VALUES (100),
       (101),
       (102),
       (103),
       (104);


INSERT INTO ms_liquidacion.liquidacion_sueldo (id_liquidacion, id_empleado, fecha, monto)
VALUES (100, 100, '2020-02-01', 500.50),
       (101, 101, '2020-02-01', 570.90),
       (102, 102, '2020-02-01', 456.50),
       (103, 103, '2020-02-01', 890.50),
       (104, 104, '2020-02-01', 998.50);


INSERT INTO ms_liquidacion.sueldo (id_sueldo, id_empleado, monto, comision)
VALUES (100, 100, 500.50, 40.6),
       (101, 101, 570.9, 13.6),
       (102, 102, 234.6, 98.1),
       (103, 103, 156.87, 12.9),
       (104, 104, 765.12, 42.9);

INSERT INTO ms_liquidacion.venta (id_venta, monto, pendiente, id_empleado)
VALUES (100, 423.3, TRUE, 100),
       (101, 523.75, FALSE, 101),
       (102, 765.87, TRUE, 102),
       (103, 542.65, FALSE, 103),
       (104, 984.12, TRUE, 104);