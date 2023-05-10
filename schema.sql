CREATE TABLE IF NOT EXISTS person
(
    id           BIGSERIAL PRIMARY KEY,
    name         TEXT    NOT NULL,
    age          INTEGER NOT NULL,
    is_married   BOOLEAN NOT NULL,
    created_at   TIMESTAMP DEFAULT now()
);