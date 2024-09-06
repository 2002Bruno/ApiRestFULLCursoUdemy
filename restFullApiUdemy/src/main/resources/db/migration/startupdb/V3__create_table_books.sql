CREATE TABLE IF NOT EXISTS book (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(60) NOT NULL,
    `launch_date` TIMESTAMP NOT NULL,
    `price` DECIMAL(10, 2) NOT NULL,
    `description` VARCHAR(400),
    `author_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`author_id`) REFERENCES person(`id`)
);