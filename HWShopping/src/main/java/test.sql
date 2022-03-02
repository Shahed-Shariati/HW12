CREATE TABLE IF NOT EXISTS role(
                    id SERIAL PRIMARY KEY ,
                    role_name VARCHAR (50)
                );

CREATE TABLE IF NOT EXISTS users(
                        id SERIAL PRIMARY KEY,
                        role_id INTEGER,
                        first_name VARCHAR(100),
                        last_name VARCHAR(100),
                        phone_number VARCHAR(20),
                        address VARCHAR(200),
                        user_name VARCHAR (50),
                        password VARCHAR (50),
                        FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE );

insert into role(role_name) values ('Administrator'),('customer');
insert into users (role_id, first_name, last_name, phone_number, address, user_name, password)
values (1,'admin','admin','09154785126','teh','admin','admin');

CREATE TABLE IF NOT EXISTS customer(
                id SERIAL PRIMARY KEY,
                user_id INTEGER,
                balance DOUBLE PRECISION,
                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE );

CREATE TABLE IF NOT EXISTS category(
                             id SERIAL PRIMARY KEY,
                             ctaegory_name VARCHAR (50),
                             category_id INTEGER ,
                             FOREIGN KEY (category_id) REFERENCES  category(id) ON DELETE CASCADE
                             );

CREATE TABLE IF NOT EXISTS product(
                              id SERIAL PRIMARY KEY,
                              product_name VARCHAR(70),
                              price DOUBLE PRECISION,
                              stock INTEGER ,
                              category_id INTEGER ,
                              FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
                              );

CREATE TABLE IF NOT EXISTS Shopping(
                              id SERIAL PRIMARY KEY,
                              customer_id INTEGER ,
                              total DOUBLE PRECISION,
                              status INTEGER ,
                              FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
                              );

CREATE TABLE IF NOT EXISTS itemcart(
                                       id SERIAL PRIMARY KEY,
                                       product_id INTEGER ,
                                       cart_id INTEGER ,
                                       quantity INTEGER ,
                                       sum DOUBLE PRECISION,
                                       FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
                                       FOREIGN KEY (cart_id) REFERENCES shopping(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Orders(
                                     id SERIAL PRIMARY KEY,
                                     customer_id INTEGER ,
                                     total DOUBLE PRECISION,
                                     status VARCHAR(50) ,
                                     FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS itemorder(
                                        id SERIAL PRIMARY KEY,
                                        product_id INTEGER ,
                                        order_id INTEGER ,
                                        quantity INTEGER ,
                                        sum DOUBLE PRECISION,
                                        FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
                                        FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

