DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS category;

CREATE TABLE category (
-- This is the Primary Key
	category_id INT AUTO_INCREMENT NOT NULL,
    category_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE project (
-- This is the Primary Key
	project_id INT AUTO_INCREMENT NOT NULL,
    project_name VARCHAR(128) NOT NULL,
    estimated_hours DECIMAL(7,2),
    actual_hours DECIMAL(7,2),
    difficulty INT,
    notes TEXT,
    PRIMARY KEY (project_id)
);

CREATE TABLE material (
-- This is the Primary Key
    material_id INT AUTO_INCREMENT NOT NULL,
-- Foregin Key form the project table
   project_id INT NOT NULL,
   material_name VARCHAR(128) NOT NULL,
   num_required INT,
   cost DECIMAL(7, 2),
   PRIMARY KEY (material_id),
   FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE TABLE step (
-- This is the primary key
	step_id INT AUTO_INCREMENT NOT NULL,
-- This is a foregien key from the project table
	project_id INT NOT NULL,
    step_text TEXT NOT NULL,
    step_order INT NOT NULL,
    PRIMARY KEY (step_id),
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE TABLE project_category (
-- This will bring in forgein keys from the project and category table
	project_id INT NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
    UNIQUE KEY (project_id, category_id)
);