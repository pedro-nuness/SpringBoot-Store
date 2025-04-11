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

CREATE TABLE users_address(
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    cep TEXT NOT NULL,
    uf TEXT NOT NULL,
    city TEXT NOT NULL,
    address TEXT NOT NULL,
    users_id TEXT NOT NULL,
    CONSTRAINT fk_users FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE CASCADE
);