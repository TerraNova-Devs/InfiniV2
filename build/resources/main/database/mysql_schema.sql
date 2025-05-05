# Set the storage engine
SET DEFAULT_STORAGE_ENGINE = INNODB;
--
# Enable foreign key constraints
SET FOREIGN_KEY_CHECKS = 1;
--
CREATE TABLE IF NOT EXISTS `items` (
    `UUID` varchar(36) NOT NULL,
    `contents` BLOB NOT NULL,
    PRIMARY KEY (`UUID`)
) DEFAULT CHARSET=utf8
  COLLATE=utf8_unicode_ci;
--
CREATE TABLE IF NOT EXISTS `item_templates` (
    `id` varchar(50) NOT NULL,
    `contents` BLOB NOT NULL,
    PRIMARY KEY (`UUID`)
) DEFAULT CHARSET=utf8
  COLLATE=utf8_unicode_ci;
--
CREATE TABLE IF NOT EXISTS `entities` (
    `UUID` varchar(36) NOT NULL,
    `contents` BLOB NOT NULL,
    PRIMARY KEY (`UUID`)
) DEFAULT CHARSET=utf8
  COLLATE=utf8_unicode_ci;
--
CREATE TABLE IF NOT EXISTS `mob_templates` (
    `id` varchar(50) NOT NULL,
    `contents` BLOB NOT NULL,
    PRIMARY KEY (`UUID`)
) DEFAULT CHARSET=utf8
  COLLATE=utf8_unicode_ci;
