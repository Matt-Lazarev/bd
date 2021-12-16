CREATE TABLE user (
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(40)  NOT NULL,
    lastname  VARCHAR(40)  NOT NULL,
    login     VARCHAR(40)  NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(10)  NOT NULL,
    blocked   bit(1),
    enabled   bit(1)
);

CREATE TABLE manager (
    manager_id   INT         NOT NULL AUTO_INCREMENT,
    firstname    VARCHAR(30) NOT NULL,
    lastname     VARCHAR(30) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    PRIMARY KEY (manager_id)
);


CREATE TABLE client (
    client_id       INT         NOT NULL AUTO_INCREMENT,
    firstname       VARCHAR(30) NOT NULL,
    lastname        VARCHAR(30) NOT NULL,
    payment_account VARCHAR(30) NOT NULL,
    PRIMARY KEY (client_id)
);


CREATE TABLE contract (
    contract_id   INT         NOT NULL AUTO_INCREMENT,
    name          VARCHAR(30) NOT NULL,
    conclusion_at DATE        NOT NULL,
    manager_id    INT         NOT NULL,
    client_id     INT         NOT NULL,
    PRIMARY KEY (contract_id),
    FOREIGN KEY (manager_id) REFERENCES manager (manager_id),
    FOREIGN KEY (client_id) REFERENCES client (client_id)
);


CREATE TABLE project (
    project_id    INT  NOT NULL AUTO_INCREMENT,
    work_start_at DATE NOT NULL,
    work_end_at   DATE NOT NULL,
    description   VARCHAR(80) NULL,
    contract_id   INT  NOT NULL,
    PRIMARY KEY (project_id),
    FOREIGN KEY (contract_id) REFERENCES contract (contract_id)
);


CREATE TABLE object (
    object_id   INT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(30) NOT NULL,
    description VARCHAR(80) DEFAULT NULL,
    project_id  INT         NOT NULL,
    PRIMARY KEY (object_id),
    FOREIGN KEY (project_id) REFERENCES project (project_id)
);


CREATE TABLE brigade (
    brigade_id INT         NOT NULL AUTO_INCREMENT,
    name       VARCHAR(45) NOT NULL,
    object_id  INT         NOT NULL,
    PRIMARY KEY (brigade_id),
    FOREIGN KEY (object_id) REFERENCES object (object_id)
);


CREATE TABLE worker (
    worker_id  INT         NOT NULL AUTO_INCREMENT,
    firstname  VARCHAR(30) NOT NULL,
    lastname   VARCHAR(30) NOT NULL,
    brigade_id INT         NOT NULL,
    PRIMARY KEY (worker_id),
    FOREIGN KEY (brigade_id) REFERENCES brigade (brigade_id)
);


CREATE TABLE object_address (
    object_address_id INT         NOT NULL AUTO_INCREMENT,
    city              VARCHAR(30) NOT NULL,
    street            VARCHAR(30) NOT NULL,
    house             INT         NOT NULL,
    object_id         INT         NOT NULL,
    PRIMARY KEY (object_address_id),
    FOREIGN KEY (object_id) REFERENCES object (object_id)
);


CREATE TABLE material (
    material_id INT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(45) NOT NULL,
    unit        VARCHAR(10) DEFAULT NULL,
    price       INT         NOT NULL,
    PRIMARY KEY (material_id)
);


CREATE TABLE used_material (
    used_material_id INT  NOT NULL AUTO_INCREMENT,
    amount           INT  NOT NULL,
    date             DATE NOT NULL,
    object_id        INT  NOT NULL,
    material_id      INT  NOT NULL,
    PRIMARY KEY (used_material_id),
    FOREIGN KEY (object_id) REFERENCES object (object_id),
    FOREIGN KEY (material_id) REFERENCES material (material_id)
);


INSERT INTO client
VALUES (null, "Mike", "Tyson", 111),
       (null, "Bob", "Winner", 222),
       (null, "Tommy", "Shelby", 333);


INSERT INTO manager
VALUES (null, "Manager", "First", 7777),
       (null, "Manager", "Second", 8888),
       (null, "Manager", "Third", 9999);


INSERT INTO contract
VALUES (null, "House Contract", now(), 1, 1),
       (null, "Appartment Contract", now(), 2, 2),
       (null, "Pool Contract", now(), 3, 3);


INSERT INTO project
VALUES (null, now(), '2021-11-23', "Project for House", 1),
       (null, now(), '2021-11-24', "Project for Apartments", 2),
       (null, now(), '2021-11-25', "Project for Swimming Pool", 3);


INSERT INTO object
VALUES (null, "House", "House object", 1),
       (null, "Appartment", "Apartment object", 2),
       (null, "Pool", "Swimming pool object", 3);


INSERT INTO brigade
VALUES (null, "house-brigade", 1),
       (null, "appartment-brigade", 2),
       (null, "other-brigade", 3);


INSERT INTO worker
VALUES (null, "Michael", "Kas", 1), (null, "Karl", "Gram", 1),
       (null, "Bob", "Match", 1),   (null, "Dean", "Win", 2),
       (null, "Sam", "Win", 2),     (null, "Nick", "Goder", 2),
       (null, "Arthur", "Shel", 3), (null, "Finn", "Shel", 3),
       (null, "Luci", "Fer", 3);


INSERT INTO object_address
VALUES (null, "Moscow", "Lenina", 1, 1),
       (null, "Moscow", "Kutusovskiy pr-kt", 10, 2),
       (null, "Moscow", "Pushkina", 15, 3);


INSERT INTO material
VALUES (null, "Wood", "m^2", 100),    (null, "Iron", "kg", 200),
       (null, "Plastic", "kg", 20),   (null, "Shingles", "m^2", 300),
       (null, "Nails", "apiece", 10), (null, "Clay", "ml", 100);


INSERT INTO used_material
VALUES (null, 100, now(), 1, 1),
       (null, 200, now(), 1, 2),
       (null, 300, now(), 1, 4),
       (null, 250, now(), 1, 6),
       (null, 100, now(), 2, 1),
       (null, 200, now(), 2, 2),
       (null, 50, now(), 2, 3),
       (null, 80, now(), 3, 3),
       (null, 10, now(), 3, 4),
       (null, 450, now(), 3, 5);