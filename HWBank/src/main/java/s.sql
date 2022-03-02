CREATE TABLE IF NOT EXISTS role(
                   id SERIAL PRIMARY KEY ,
                   role_name VARCHAR (50);

    insert into role(role_name) values ('boss'),('customer'),('clerk');

    CREATE TABLE IF NOT EXISTS users(
         id SERIAL PRIMARY KEY,
         role_id INTEGER,
         first_name VARCHAR(100),
         last_name VARCHAR(100),
         national_code VARCHAR(10) UNIQUE,
    phone_number VARCHAR(20),
         address VARCHAR(200),
    user_name VARCHAR (50),
           password VARCHAR (50),
    FOREIGN KEY (role_id) REFERENCES role(id) );


    CREATE TABLE IF NOT EXISTS boss(
    id SERIAL PRIMARY KEY,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id));


   CREATE TABLE IF NOT EXISTS clerks(
   id SERIAL PRIMARY KEY,
   user_id INTEGER,
   boss_id INTEGER ,
   FOREIGN KEY (boss_id) REFERENCES boss(id),
   FOREIGN KEY (user_id) REFERENCES users(id));



    CREATE TABLE IF NOT EXISTS bank(
               id SERIAL PRIMARY KEY,
               name INTEGER,
               address VARCHAR(250));

    CREATE TABLE IF NOT EXISTS bankbranch(
                id SERIAL PRIMARY KEY,
                bank_id INTEGER,
                branchcode INTEGER);


    CREATE TABLE IF NOT EXISTS creditcard(
                id SERIAL PRIMARY KEY,
                number VARCHAR(16) UNIQUE ,
                expirDate DATE ,
                cvv INTEGER,
                password VARCHAR(4),
                password2 VARCHAR (6),
                isactive INTEGER ,
                failed_password INTEGER );


    CREATE TABLE IF NOT EXISTS types(
                id SERIAL PRIMARY KEY,
                type VARCHAR
                );

    CREATE TABLE IF NOT EXISTS account(
                id SERIAL PRIMARY KEY,
                account_number VARCHAR UNIQUE ,
                balance DOUBLE PRECISION,
                credit_card_id INTEGER,
                type_id INTEGER ,
                FOREIGN KEY (credit_card_id) REFERENCES creditcard(id),
                FOREIGN KEY (type_id) REFERENCES types(id));

    CREATE TABLE IF NOT EXISTS customer(
                id SERIAL PRIMARY KEY,
                user_id INTEGER,
                account_id INTEGER,
                FOREIGN KEY (user_id) REFERENCES users(id),
                FOREIGN KEY (account_id) REFERENCES account(id));


    CREATE TABLE IF NOT EXISTS branchcustomer(
               id SERIAL PRIMARY KEY,
               branch_id INTEGER,
               customer_id INTEGER ,
               FOREIGN KEY (branch_id) REFERENCES bankbranch(id),
               FOREIGN KEY (customer_id) REFERENCES customer(id));

    CREATE TABLE IF NOT EXISTS accoutncustoer(
               id SERIAL PRIMARY KEY,
               account_id INTEGER,
               customer_id INTEGER ,
               FOREIGN KEY (account_id) REFERENCES account(id),
               FOREIGN KEY (customer_id) REFERENCES customer(id));

    CREATE TABLE IF NOT EXISTS transactions (
             id SERIAL    PRIMARY KEY,
             accountId     INTEGER ,
             amount       DOUBLE PRECISION ,
             status       varchar(10),
             types        varchar(10),
             transaction_date date ,
             account_destination INTEGER ,
             FOREIGN KEY (account_destination) REFERENCES account (id),
             FOREIGN KEY (accountId) REFERENCES account (id));