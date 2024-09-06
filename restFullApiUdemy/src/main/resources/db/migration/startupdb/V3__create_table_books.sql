CREATE TABLE IF NOT EXISTS book (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(60) NOT NULL,
    `description` VARCHAR(400),
    `author` VARCHAR(80) NOT NULL,
    PRIMARY KEY (`id`)
);