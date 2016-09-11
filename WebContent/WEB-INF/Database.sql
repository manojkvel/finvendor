ALTER TABLE `users`
	ADD COLUMN `verified` CHAR(1) NULL AFTER `registration_date`;
	
CREATE TABLE `user_verification` (
	`username` VARCHAR(45) NOT NULL,
	`registration_id` VARCHAR(36) NOT NULL,
	`email` VARCHAR(150) NOT NULL,
	`created_date` TIMESTAMP NOT NULL,
	`verified_date` TIMESTAMP NULL,
	PRIMARY KEY (`username`)
);

ALTER TABLE `users`
	ADD COLUMN `email` VARCHAR(100) NOT NULL AFTER `password`;
ALTER TABLE `user_verification`
	DROP COLUMN `email`;
UPDATE users u 
	JOIN vendor v ON v.username = u.username  
   SET u.email = v.email;
UPDATE users u 
	JOIN consumer c ON c.username = u.username  
   SET u.email = c.email;
ALTER TABLE `vendor`
	DROP COLUMN `email`;
ALTER TABLE `consumer`
	DROP COLUMN `email`;
	
INSERT INTO users (username,  password, email, enabled, last_login, login_attempts, last_modified, registration_date, verified)
	VALUES ('finvendor_admin', '$2a$10$FRzyZdEUiBE9o9lEkf8llOVkIxFO2yvapr/Aywfu.MkNkiKi4VYjK', 'support@finvendor.com', 1, NULL, 0, NULL, CURRENT_TIMESTAMP, 'Y');
INSERT INTO user_roles (username, role_id) VALUES ('finvendor_admin', 1);

ALTER TABLE `country`
	ADD COLUMN `region_id` INT NULL DEFAULT NULL AFTER `iso_3_cd`,
	ADD CONSTRAINT `FK1_REGION_ID` FOREIGN KEY (`region_id`) REFERENCES `region` (`region_id`);
	
ALTER TABLE `currency`
	CHANGE COLUMN `major_minor` `major_minor` VARCHAR(2) NULL DEFAULT 'MJ' AFTER `iso_cd`;
	
ALTER TABLE `consumer`
	ADD COLUMN `designation` VARCHAR(100) NOT NULL AFTER `tags`,
	ADD COLUMN `secondary_email` VARCHAR(150) NULL AFTER `designation`,
	ADD COLUMN `company_url` VARCHAR(100) NOT NULL AFTER `secondary_email`,
	ADD COLUMN `company_info` VARCHAR(500) NOT NULL AFTER `company_url`;

ALTER TABLE `consumer`
	ADD COLUMN `regionofincorp` INT NOT NULL AFTER `company_info`,
	ADD COLUMN `countryofincorp` INT NOT NULL AFTER `regionofincorp`;
ALTER TABLE `consumer`
	ADD COLUMN `yearofincorp` INT NOT NULL AFTER `countryofincorp`;
ALTER TABLE `consumer`
	ADD COLUMN `logobytes` LONGBLOB NULL AFTER `yearofincorp`,
	ADD COLUMN `logotype` VARCHAR(10) NULL AFTER `logobytes`;

CREATE TABLE `company_sub_type` (
	`id` INT NOT NULL,
	`type` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`id`)
)
;

ALTER TABLE `consumer`
	ADD COLUMN `company_sub_type` INT NOT NULL AFTER `logotype`;
	
insert into company_sub_type (id, type) values (1, 'Hedge Fund');
insert into company_sub_type (id, type) values (2, 'Investment Banking');

ALTER TABLE `consumer`
	ADD COLUMN `city` VARCHAR(100) NULL AFTER `company_sub_type`;
	
/* To be run */
UPDATE `finven`.`security_types` SET `asset_class_id`=1 WHERE  `security_type_id`=1;

CREATE TABLE `con_mybusineeds_market_data` (
	`asset_class` VARCHAR(50) NOT NULL,
	`security_type` VARCHAR(50) NOT NULL,
	`data_coverage_region` VARCHAR(50) NOT NULL,
	`data_coverage_country` VARCHAR(50) NOT NULL,
	`data_coverage_exchange` VARCHAR(50) NOT NULL,
	`data_attribute` VARCHAR(500) NOT NULL
)
;
ALTER TABLE `con_mybusineeds_market_data`
	ADD COLUMN `consumer_id` VARCHAR(50) NOT NULL FIRST,
	ADD COLUMN `last_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `data_attribute`;
	
	
	
CREATE TABLE IF NOT EXISTS consumer_rfp (
rfp_id varchar(45) NOT NULL,
consumer_id varchar(45) NOT NULL,
rfp_title varchar(100) NOT NULL,
rfp_short_desc varchar(500) NOT NULL,
rfp_detailed_desc varchar(1500) NOT NULL,
target_vendor_type varchar(50) NOT NULL,
created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
rfp_end_date timestamp,
rfp_closed char(1),
rfp_closed_date timestamp 
)


CREATE TABLE IF NOT EXISTS vendor_rfp_interest (
rfp_id varchar(45) NOT NULL,
vendor_id varchar(45) NOT NULL,
interest_shown_date timestamp,
interset_revoke_date timestamp,
rfp_vendor_response varchar(1500) NULL,
shortlisted char(1),
shortlisted_date timestamp,
finalized char(1),
finalized_date timestamp,
more_info_requested char(1),
more_info_requested_date timestamp,
more_info_provided_date timestamp
)

CREATE TABLE IF NOT EXISTS rfp_more_info_details (
id varchar(45) NOT NULL,
rfp_id varchar(45) NOT NULL,
vendor_id varchar(45) NOT NULL,
more_info varchar(1000) NOT NULL,
more_info_requested_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
consumer_reply varchar(1000) NULL,
more_info_provided_date timestamp
)

