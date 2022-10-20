# Spring_Boot_Example
# Create db with 

CREATE DATABASE `employee-schema`;
USE `employee-schema`;

CREATE TABLE `employee` (
`emp_id` int NOT NULL AUTO_INCREMENT,
`first_name` varchar(45) DEFAULT NULL,
`last_name` varchar(45) DEFAULT NULL,
`email_id` varchar(45) DEFAULT NULL,
`create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
`update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`emp_id`)
);


CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `emp_id` int DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  KEY `emp_id_idx` (`emp_id`),
  CONSTRAINT `emp_id` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`)
);

CREATE TABLE `image` (
`image_id` int NOT NULL AUTO_INCREMENT,
`resource_type` varchar(45) NOT NULL,
`url` varchar(255) NOT NULL,
`secure_url` varchar(255) NOT NULL,
`create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`image_id`)
)