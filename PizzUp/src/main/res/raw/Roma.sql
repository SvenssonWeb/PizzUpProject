BEGIN TRANSACTION;

INSERT INTO restaurant VALUES(5,'Roma','Västra Esplenaden 6B, Växjö',047025900,0);

INSERT INTO pizza VALUES(61,'Calzone (inbakad)',60,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,2);
INSERT INTO p_i VALUES(5,3);

INSERT INTO pizza VALUES(62,'Vesuvio',60,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,2);
INSERT INTO p_i VALUES(5,3);

INSERT INTO pizza VALUES(63,'Hawaii',60,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,2);
INSERT INTO p_i VALUES(5,3);
INSERT INTO p_i VALUES(5,4);

INSERT INTO pizza VALUES(64,'Tomaso',60,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,2);
INSERT INTO p_i VALUES(5,3);
INSERT INTO p_i VALUES(5,12);

INSERT INTO pizza VALUES(65,'Venezia',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,7);

INSERT INTO pizza VALUES(66,'Caruso',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,8);
INSERT INTO p_i VALUES(5,10);


INSERT INTO pizza VALUES(67,'Cappricciosa',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,3);
INSERT INTO p_i VALUES(5,11);

INSERT INTO pizza VALUES(68,'Capri',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,3);
INSERT INTO p_i VALUES(5,12);


INSERT INTO pizza VALUES(69,'Florida',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,3);
INSERT INTO p_i VALUES(5,13);

INSERT INTO pizza VALUES(70,'Africana',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,19);
INSERT INTO p_i VALUES(5,4);

INSERT INTO pizza VALUES(71,'Blecko',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,12);
INSERT INTO p_i VALUES(5,11);

INSERT INTO pizza VALUES(72,'Jamaica',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,3);
INSERT INTO p_i VALUES(5,11);
INSERT INTO p_i VALUES(5,12);

INSERT INTO pizza VALUES(73,'Malta',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,13);
INSERT INTO p_i VALUES(5,12);
INSERT INTO p_i VALUES(5,9);

INSERT INTO pizza VALUES(74,'Madrid',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,18);
INSERT INTO p_i VALUES(5,9);
INSERT INTO p_i VALUES(5,26);
INSERT INTO p_i VALUES(5,27);

INSERT INTO pizza VALUES(75,'Maffia',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,3);
INSERT INTO p_i VALUES(5,11);
INSERT INTO p_i VALUES(5,14);


INSERT INTO pizza VALUES(76,'Disco',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,3);
INSERT INTO p_i VALUES(5,12);
INSERT INTO p_i VALUES(5,7);

INSERT INTO pizza VALUES(77,'La Mare',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,3);
INSERT INTO p_i VALUES(5,11);
INSERT INTO p_i VALUES(5,4);
INSERT INTO p_i VALUES(5,12);

INSERT INTO pizza VALUES(78,'Ciao Ciao (inbakad)',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,9);
INSERT INTO p_i VALUES(5,14);
INSERT INTO p_i VALUES(5,11);
INSERT INTO p_i VALUES(5,10);

INSERT INTO pizza VALUES(79,'Havspizza',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,12);
INSERT INTO p_i VALUES(5,24);
INSERT INTO p_i VALUES(5,22);
INSERT INTO p_i VALUES(5,25);

INSERT INTO pizza VALUES(80,'Kycklingpizza',75,0,5);
INSERT INTO p_i VALUES(5,1);
INSERT INTO p_i VALUES(5,16);
INSERT INTO p_i VALUES(5,28);
INSERT INTO p_i VALUES(5,29);
INSERT INTO p_i VALUES(5,20);

COMMIT;