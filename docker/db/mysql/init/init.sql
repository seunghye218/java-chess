USE chess;

CREATE TABLE movement
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    source VARCHAR(2) NOT NULL,
    target VARCHAR(2) NOT NULL,
    turn   varchar(5) NOT NULL
);
