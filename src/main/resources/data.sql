INSERT INTO rol (nombre,descripcion) VALUES ('ADMINISTRADOR','Administador de la plazoleta');
INSERT INTO rol (nombre,descripcion) VALUES ('PROPIETARIO','Propietario de el restaurante');
INSERT INTO rol (nombre,descripcion) VALUES ('EMPLEADO','Empleado de el restaurante');

INSERT INTO public.usuario
(fecha_nacimiento, documento_identidad, id, id_rol, apellido, celular, clave, correo, nombre)
VALUES('2000-05-15', 12345678, 1, 1, 'perez', '+573130012345', '$2a$10$6hOfOOo6nASA3SdhhYQOU.Aw2sul3INEpTuiBvrGsiv4ab8v/udqe', 'juan.perez2@email.com', 'tato');
