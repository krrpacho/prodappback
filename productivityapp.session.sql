USE productivityapp;

CREATE TABLE IF NOT EXISTS goals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    target_time VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    created_at DATE NOT NULL
);