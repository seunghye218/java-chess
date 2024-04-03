USE chess;

CREATE TABLE movement
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    turn   varchar(5) NOT NULL,
    source VARCHAR(2) NOT NULL,
    target VARCHAR(2) NOT NULL
);
