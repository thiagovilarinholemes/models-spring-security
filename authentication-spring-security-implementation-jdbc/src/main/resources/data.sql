INSERT INTO users(username, password, enabled) VALUES('carina', '123', true);
INSERT INTO users(username, password, enabled) VALUES('thiago', '123', true);

INSERT INTO authorities(username, authority) VALUES('carina', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES('thiago', 'ROLE_ADMIN');

