CREATE TABLE IF NOT EXISTS candidates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    gender VARCHAR(10),
    salary_expected DECIMAL(10, 2)
);

INSERT INTO candidates (name, email, gender, salary_expected)
VALUES
    ('John wick', 'johnwick@gmail.com', 'Male', 90000.00),
    ('Jade Smith', 'jadesmith12@prueba.com', 'Female', 30000.00),
    ('pepe Suarez', 'pepez@gmail.com', 'Female', 40000.00),
    ('Austin Santos', 'asantos@gmail.com', 'Female', 2000.00),
    ('Pedro Jaramillo', 'pedrojara@gmail.com', 'Female', 7000.00)
;
