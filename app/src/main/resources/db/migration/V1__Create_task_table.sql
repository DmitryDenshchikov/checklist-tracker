CREATE TABLE task (
    title VARCHAR(255),
    description VARCHAR(255),
    isCompleted BOOLEAN NOT NULL,
    PRIMARY KEY (title, description)
);