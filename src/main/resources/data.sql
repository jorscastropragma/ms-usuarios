INSERT INTO rol (nombre,descripcion) VALUES ('ADMINISTRADOR','Administador de la plazoleta');
INSERT INTO rol (nombre,descripcion) VALUES ('PROPIETARIO','Propietario de el restaurante');
INSERT INTO rol (nombre,descripcion) VALUES ('EMPLEADO','Empleado de el restaurante');
INSERT INTO rol (nombre,descripcion) VALUES ('CLIENTE','Cliente de los restaurantes');

INSERT INTO public.usuario
(fecha_nacimiento, documento_identidad, id, id_rol, apellido, celular, clave, correo, nombre)
VALUES('2000-05-15', 12345678, 1, 1, 'perez', '+573130012345', '$2a$10$q4rl2R8QS.I8.Jv8ZMLBPOq/vtMEyovrpRfx7isVu054STKYZvqX6', 'juan.perez2@email.com', 'tato');

ALTER SEQUENCE usuario_id_seq RESTART WITH 2;