CREATE TABLE wards (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE towns (
	id INT AUTO_INCREMENT PRIMARY KEY,
	ward_id INT NOT NULL,
	name VARCHAR(255) NOT NULL,
	FOREIGN KEY (ward_id) REFERENCES wards(id) ON DELETE CASCADE,
	UNIQUE (ward_id, name)
);

CREATE TABLE trash_types (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE trash_schedules (
	id INT AUTO_INCREMENT PRIMARY KEY,
	town_id INT NOT NULL,
	trash_type_id INT NOT NULL,
	week_of_month ENUM('1', '2', '3', '4', '5', 'Every') NOT NULL,
	collection_day ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL,
	FOREIGN KEY (town_id) REFERENCES towns(id) ON DELETE CASCADE,
	FOREIGN KEY (trash_type_id) REFERENCES trash_types(id) ON DELETE CASCADE,
	UNIQUE (town_id, trash_type_id, week_of_month, collection_day)
);

CREATE TABLE items (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE,
	trash_type_id INT NOT NULL,
	trash_type_id2 INT NULL,
	FOREIGN KEY (trash_type_id) REFERENCES trash_types(id) ON DELETE CASCADE,
	FOREIGN KEY (trash_type_id2) REFERENCES trash_types(id) ON DELETE SET NULL
);