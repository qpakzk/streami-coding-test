CREATE DATABASE IF NOT EXISTS coin;
USE coin;
CREATE TABLE IF NOT EXISTS coin (
    `id` varchar(255) not null,
    `type` varchar(255) not null,
    `price` bigint not null,
    `current_at` datetime not null,
    `created_at` datetime not null,
    `updated_at` datetime not null,
     PRIMARY KEY (id)
);