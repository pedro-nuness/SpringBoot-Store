

CREATE TABLE product (
                         id TEXT PRIMARY KEY UNIQUE NOT NULL,
                         name TEXT NOT NULL,
                         price DOUBLE PRECISION NOT NULL,
                         description TEXT NOT NULL

);

CREATE TABLE product_image (
                               id TEXT PRIMARY KEY UNIQUE NOT NULL,
                               url TEXT UNIQUE NOT NULL,
                               product_id TEXT NOT NULL,
                               CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

CREATE TABLE users (
                       id TEXT PRIMARY KEY UNIQUE NOT NULL,
                       login TEXT NOT NULL UNIQUE,
                       password TEXT NOT NULL,
                       role TEXT NOT NULL

);