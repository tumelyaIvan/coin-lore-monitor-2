DROP SCHEMA IF EXISTS `crypto_prices_db`;

CREATE SCHEMA `crypto_prices_db` CHARACTER SET `utf8`;

USE `crypto_prices_db`;

CREATE TABLE `currency_prices` (
	`currency_id` INTEGER NOT NULL,
	`currency_symbol` VARCHAR(10) NOT NULL,
	`currency_price` DOUBLE NOT NULL,

	PRIMARY KEY (`currency_id`),

	CONSTRAINT `currency_symbol_unique` UNIQUE (`currency_symbol`)
);

CREATE TABLE `notify_prices` (
    `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_name` VARCHAR(50) NOT NULL,
    `currency_symbol` VARCHAR(10) NOT NULL,
    `currency_price` DOUBLE NOT NULL
);


