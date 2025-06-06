--liquibase formatted sql

--changeset reservation:2

INSERT INTO contact VALUES (1,'sacco.andres@gmail.com','54911111111'),(2,'sacco.andres@gmail.com','54911111111'),(3,'sacco.andres@gmail.com','54911111111'),(4,'sacco.andres@gmail.com','54911111111');
INSERT INTO reservation VALUES (1,1,'2','2'),(2,2,'2','2'),(3,3,'2','2'),(4,4,'2','2');
INSERT INTO passenger VALUES (1, '1985-01-01','ARG',1,'987654321','DNI','Andres','Sacco'),(2, '1985-01-01','ARG',2,'987654321','DNI','Andres','Sacco'),(3, '1985-01-01','ARG',3,'987654321','DNI','Andres','Sacco'),(4,'1985-01-01','ARG',4,'987654321','DNI','Andres','Sacco');
