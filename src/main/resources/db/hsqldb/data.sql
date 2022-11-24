-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO users(username,password,enabled) VALUES ('pabquide','p4bqu1de',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'pabquide','player');

INSERT INTO users(username,password,enabled) VALUES ('meriglmar','mer1glm4r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'meriglmar','player');

INSERT INTO users(username,password,enabled) VALUES ('angbermar1','4ngbermar1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'angbermar1','player');

INSERT INTO users(username,password,enabled) VALUES ('paomarsan','p4om4rs4n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'paomarsan','player');

INSERT INTO users(username,password,enabled) VALUES ('sanzultor','s4nzult0r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'sanzultor','player');

INSERT INTO users(username,password,enabled) VALUES ('andgarriv','4ndg4rr1v',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'andgarriv','player');


INSERT INTO players(id,first_name,last_name,username,mail) VALUES (1,'Pablo','Quindos','pabquide','pabquide@alum.us.es');
INSERT INTO players(id,first_name,last_name,username,mail) VALUES (2,'Mercedes','Iglesias','meriglmar','meriglmar@alum.us.es');
INSERT INTO players(id,first_name,last_name,username,mail) VALUES (3,'Ángela','Bernal','angbermar1','angbermar1@alum.us.es');
INSERT INTO players(id,first_name,last_name,username,mail) VALUES (4,'Paola','Martín','paomarsan','paomarsan@alum.us.es');
INSERT INTO players(id,first_name,last_name,username,mail) VALUES (5,'Santiago','Zuleta','sanzultor','sanzultor@alum.us.es');
INSERT INTO players(id,first_name,last_name,username,mail) VALUES (6,'Andrés','García','andgarriv','andgarriv@alum.us.es');



INSERT INTO tableros(id,filas,columnas,numero_minas) VALUES (1,9,14,15);
INSERT INTO tableros(id,filas,columnas,numero_minas) VALUES (2,13,17,30);
INSERT INTO tableros(id,filas,columnas,numero_minas) VALUES (3,18,18,65);
INSERT INTO tableros(id,filas,columnas,numero_minas) VALUES (4,4,2,11);


INSERT INTO casillas(id, coordenada_x, coordenada_y, num_minas_adyacentes, esta_cubierta, contenido) VALUES (1,4,10,4,TRUE,'BOMBA'); 
INSERT INTO casillas(id, coordenada_x, coordenada_y, num_minas_adyacentes, esta_cubierta, contenido) VALUES (2,14,8,4,FALSE,'BANDERA'); 
INSERT INTO casillas(id, coordenada_x, coordenada_y, num_minas_adyacentes, esta_cubierta, contenido) VALUES (3,4,4,4,TRUE,'BOMBA'); 
INSERT INTO casillas(id, coordenada_x, coordenada_y, num_minas_adyacentes, esta_cubierta, contenido) VALUES (4,11,14,7,TRUE,'VACIA'); 


INSERT INTO games(id,difficulty,start_time, finish_time,num_clicks,in_progress,lost_game,username,id_tablero) VALUES (1,'EASY','1990-06-20 08:03:00.0', '1990-06-20 08:20:00.0',10,FALSE, TRUE,'pabquide',1);
INSERT INTO games(id,difficulty,start_time, finish_time,num_clicks,in_progress,lost_game,username,id_tablero) VALUES (2,'EASY','1990-06-20 08:04:00.0', '1990-06-20 08:21:00.0',11,TRUE, FALSE,'meriglmar',1);
INSERT INTO games(id,difficulty,start_time, finish_time,num_clicks,in_progress,lost_game,username,id_tablero) VALUES (3,'EASY','1990-06-20 08:05:00.0', '1990-06-20 08:22:00.0',12,TRUE, FALSE,'pabquide',1);
INSERT INTO games(id,difficulty,start_time, finish_time,num_clicks,in_progress,lost_game,username,id_tablero) VALUES (4,'MEDIUM','1990-06-20 08:06:00.0', '1990-06-20 08:23:00.0',13,TRUE, FALSE,'meriglmar',1);
INSERT INTO games(id,difficulty,start_time, finish_time,num_clicks,in_progress,lost_game,username,id_tablero) VALUES (5,'DIFFICULT','1990-06-20 08:07:00.0', '1990-06-20 08:24:00.0',14,FALSE, TRUE,'pabquide',1);
INSERT INTO games(id,difficulty,start_time, finish_time,num_clicks,in_progress,lost_game,username,id_tablero) VALUES (6,'DIFFICULT','1990-06-20 08:07:00.0', '1990-06-20 08:24:00.0',14,FALSE, TRUE,'paomarsan',1);
INSERT INTO games(id,difficulty,start_time, finish_time,num_clicks,in_progress,lost_game,username,id_tablero) VALUES (7,'MEDIUM','1990-06-20 08:07:00.0', '1990-06-20 08:24:00.0',14,FALSE, TRUE,'paomarsan',1);



