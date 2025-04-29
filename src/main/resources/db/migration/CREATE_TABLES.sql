CREATE TABLE product (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    description TEXT NOT NULL,
    rating DOUBLE PRECISION,
    promotion_value DOUBLE PRECISION,
    category_id TEXT,
    product_collection_id TEXT,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL,
    CONSTRAINT fk_product_collection FOREIGN KEY (product_collection_id) REFERENCES product_collection(id)
);

CREATE TABLE product_image (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    product_id TEXT NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

CREATE TABLE users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    email TEXT NOT NULL UNIQUE,
    first_name TEXT,
    last_name TEXT,
    cpf TEXT,
    birth_day INTEGER,
    birth_month INTEGER,
    birth_year INTEGER,
    password TEXT NOT NULL,
    role TEXT NOT NULL,
    CONSTRAINT unique_cpf UNIQUE (cpf)
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

CREATE TABLE user_cart (
   id TEXT PRIMARY KEY UNIQUE NOT NULL,
   user_id TEXT UNIQUE NOT NULL,
   CONSTRAINT fk_user_cart_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE user_cart_item (
   id TEXT PRIMARY KEY UNIQUE NOT NULL,
   cart_id TEXT NOT NULL,
   product_id TEXT NOT NULL,
   quantity INTEGER NOT NULL,
   CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES user_cart(id) ON DELETE CASCADE,
   CONSTRAINT fk_product_cart FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);


CREATE TABLE category (
   id TEXT PRIMARY KEY UNIQUE NOT NULL,
   name TEXT NOT NULL
);

CREATE TABLE category_image (
   id TEXT PRIMARY KEY UNIQUE NOT NULL,
   category_id TEXT NOT NULL,
   CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);


CREATE TABLE product_type (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE product_type_image (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    product_type_id TEXT NOT NULL,
    CONSTRAINT fk_product_type FOREIGN KEY (product_type_id) REFERENCES product_type(id) ON DELETE CASCADE
);


CREATE TABLE image_entity (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    url TEXT UNIQUE NOT NULL
);


CREATE TABLE product_collection (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE product_collection_image (
     id TEXT PRIMARY KEY UNIQUE NOT NULL,
     product_collection_id TEXT NOT NULL,
     CONSTRAINT fk_product_collection FOREIGN KEY (product_collection_id) REFERENCES product_collection(id) ON DELETE CASCADE
);

CREATE TABLE product_variation (
                                   id TEXT PRIMARY KEY UNIQUE NOT NULL,
                                   color TEXT NOT NULL,
                                   product_id TEXT NOT NULL,
                                   CONSTRAINT fk_product_variation_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

CREATE TABLE variation_size (
                                id TEXT PRIMARY KEY UNIQUE NOT NULL,
                                size TEXT NOT NULL,
                                stock INTEGER NOT NULL,
                                variation_id TEXT NOT NULL,
                                CONSTRAINT fk_variation_size_variation FOREIGN KEY (variation_id) REFERENCES product_variation(id) ON DELETE CASCADE
);

CREATE TABLE product_variation_image(
     id TEXT PRIMARY KEY UNIQUE NOT NULL,
     product_variation_id TEXT NOT NULL,
     CONSTRAINT fk_product_variation FOREIGN KEY (product_variation_id) REFERENCES product_variation(id) ON DELETE CASCADE
);


CREATE TABLE refresh_tokens (
                                id TEXT PRIMARY KEY UNIQUE NOT NULL,
                                user_id TEXT NOT NULL,
                                token_hash TEXT NOT NULL UNIQUE,
                                active BOOLEAN NOT NULL DEFAULT TRUE,
                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT fk_refresh_tokens_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
