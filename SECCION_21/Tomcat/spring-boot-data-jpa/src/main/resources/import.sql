INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (1,'Jhon','Galvis','jg@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (2,'Jairo','Arenales','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (3,'Antonio','Cepada','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (4,'Harold','Zapata','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (5,'Ferney','Ocampo','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (6,'Javier','Florez','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (7,'Carlos','Torres','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (8,'Esteban','Quiroz','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (9,'Alfredo','Morelos','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (10,'Joel','Zuñiga','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (11,'Sergio','Rodriguez','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (12,'Memo','Orozco','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (13,'Tuto','Quintero','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (14,'Mauro','Peñaranda','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (15,'Dany','Martinez','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (16,'Jorge','Paez','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (17,'Cristian','Ramirez','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (18,'Flor','Carrillo','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (19,'Maria','Galvez','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (20,'Julia','Bohorquez','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (21,'Angie','Galvis','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (22,'Jeison','Galvis','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (23,'Emiliano','Galvis','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (24,'Edward','Camargo','ja@gmail.com','2020-03-12','');
INSERT INTO clientes (id,nombre,apellido,email,create_at, foto) VALUES (25,'Miguel','Serrano','ja@gmail.com','2020-03-12','');

INSERT INTO productos (id,create_at,nombre,precio) VALUES (1,'2020-03-22','Panasonic Pantalla LCD',10000);
INSERT INTO productos (id,create_at,nombre,precio) VALUES (2,'2020-03-22','Sony Camara digital',20000);
INSERT INTO productos (id,create_at,nombre,precio) VALUES (3,'2020-03-22','Apple iPod',30000);
INSERT INTO productos (id,create_at,nombre,precio) VALUES (4,'2020-03-22','Sony NoteBook',40000);
INSERT INTO productos (id,create_at,nombre,precio) VALUES (5,'2020-03-22','Mica Comoda 5 Cajones',50000);

INSERT INTO USERS (ID,USERNAME,PASSWORD,ENABLE) VALUES (1,'admin','$2a$10$ELXChcE6PLa2K5CJn7dQM.7bShCgx7nE1cfdgkMDPiGgABHXt.7Tm',1);
INSERT INTO USERS (ID,USERNAME,PASSWORD,ENABLE) VALUES (2,'andres','$2a$10$62Sq2ZsMOMZ/2IER9OSvXeZ0HmAOlxFI7DdNTRFMa6sz9NXx6.n3e',1); 

INSERT INTO AUTHORITIES (user_id,authority) VALUES (1,'ROLE_ADMIN');
INSERT INTO AUTHORITIES (user_id,authority) VALUES (2,'ROLE_USER');

