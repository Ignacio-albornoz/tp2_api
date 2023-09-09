INSERT INTO conceptos (id, hs_minimo, hs_maximo, laborable, nombre)
VALUES (1, 6, 8, true, 'Turno Normal');
INSERT INTO conceptos (id, hs_minimo, hs_maximo, laborable, nombre)
VALUES (2, 2, 6, true, 'Turno Extra');
INSERT INTO conceptos (id, hs_minimo, hs_maximo, laborable, nombre)
VALUES (3, null, null, false, 'Dia Libre');
INSERT INTO empleados (id, nro_documento, nombre, apellido, email, fecha_nacimiento, fecha_ingreso, fecha_creacion)
VALUES (99, 37321123,'Nacho', 'Albornoz', 'n@gmail.com', '1993-08-08', '2022-01-01', '2023-02-08');
INSERT INTO jornadas (id, id_empleado, id_concepto, fecha, hs_trabajadas)
VALUES (99, 99, 2, '2022-01-01', 6);
