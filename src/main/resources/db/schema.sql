create table account(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(20) not null,
    balance int not null,
    create_date timestamp not null default current_timestamp,
    update_date timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table transaction(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    user VARCHAR(20) NOT NULL,
    action VARCHAR(20) NOT NULL,
    amount INT NOT NULL,
    transaction_date timestamp default current_timestamp
)