CREATE TABLE IF NOT EXISTS users (
                      username VARCHAR(20) NOT NULL PRIMARY KEY,
                      password VARCHAR(200) NOT NULL,
                      email VARCHAR(50),
                      locked TINYINT NOT NULL,
                      disabled TINYINT NOT NULL
);

INSERT INTO users (username, password, email, locked, disabled)
VALUES
    ('admin', '1234', 'admin@gmail.com', 0, 0),
    ('ricardo', '3456', 'ricardoa@gmail.com', 0, 0);