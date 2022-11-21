DROP DATABASE IF EXISTS guessthenumbertestdb;
CREATE DATABASE guessthenumbertestdb;

USE guessthenumbertestdb;

CREATE TABLE game(
    gameId INT AUTO_INCREMENT PRIMARY KEY,
    answer SMALLINT UNSIGNED NOT NULL,
    inProgress TINYINT NOT NULL
);

CREATE TABLE round(
    roundId INT AUTO_INCREMENT PRIMARY KEY,
    gameId INT NOT NULL,
    FOREIGN KEY fk_round_gameId (gameId) 
        REFERENCES game(gameId),
    guess SMALLINT UNSIGNED NOT NULL,
    guessTime DATETIME NOT NULL,
    result VARCHAR(11) NOT NULL
);