INSERT INTO ms_cuentacorriente.cliente (id_cliente, razon_social , cuit, mail)
VALUES (100, 'Generadores S.R.L', '30-71492781-3', 'generadoressrl@gmail.com'),
       (101, 'Maderera Sur S.A', '30-63976843-7', 'madererasur@gmail.com'),
       (102, 'FV Plomeria Integral', '33-71107495-9', 'plomeriaintegral@gmail.com'),
       (103, 'D&M Construccion S.R.L', '30-71434975-5', 'dymconstruccion@gmail.com'),
       (104, 'Aberturas Capital S.R.L', '30-69770395-7', 'capitalaberturas@gmail.com');

INSERT INTO ms_cuentacorriente.cheque (id, nro_cheque, fecha_cobro, banco, observacion)
VALUES (100, 1, '2020-01-01', 'Banco de Entre Rios', 'Observacion de CHEQUE 100'),
       (101, 2, '2020-01-02', 'Banco Santander', 'Observacion de CHEQUE 101'),
       (103, 3, '2020-01-03', 'Banco Galicia', 'Observacion de CHEQUE 102'),
       (104, 4, '2020-01-04', 'Banco de Santa Fe', 'Observacion de CHEQUE 103'),
       (105, 5, '2020-01-05', 'BBVA', 'Observacion de CHEQUE 104');

INSERT INTO ms_cuentacorriente.efectivo (id , nro_recibo, observacion)
VALUES (100, 500, 'Observacion de EFECTIVO 100'),
       (101, 30, 'Observacion de EFECTIVO 101'),
       (102, 110, 'Observacion de EFECTIVO 102'),
       (103, 998, 'Observacion de EFECTIVO 103'),
       (104, 429, 'Observacion de EFECTIVO 104');

INSERT INTO ms_cuentacorriente.transferencia(id, cbu_origen , cbu_destino , codigo_transferencia, observacion)
VALUES (100, '2381182911100098283988', '1352158111100035365823', 1000000, 'Observacion de TRANSFERENCIA 100'),
       (101, '7023615011100077420500', '6075773711100042099144', 2000000, 'Observacion de TRANSFERENCIA 101'),
       (102, '2983837611100042977576', '4350711111100040050909', 3000000, 'Observacion de TRANSFERENCIA 102'),
       (103, '5154366811100089101655', '8438402511100004261703', 4000000, 'Observacion de TRANSFERENCIA 103'),
       (104, '6648781811100010867816', '1518438811100000409136', 5000000, 'Observacion de TRANSFERENCIA 104');

INSERT INTO ms_cuentacorriente.pago (id_pago, id_cliente, id_cheque, id_efectivo, id_transferencia, fecha_pago)
VALUES (100, 100, 100, null, null, '2020-10-10'),
       (101, 101, null, 101, null, '2020-10-15'),
       (102, 102, null, null, 103, '2020-10-20'),
       (103, 103, 101, null, null, '2020-10-30'),
       (104, 104, null, 104, null, '2020-10-04');
