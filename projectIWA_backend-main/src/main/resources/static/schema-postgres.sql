ALTER TABLE user_entity ADD COLUMN "is_infected" BOOLEAN NOT NULL DEFAULT FALSE;

CREATE TABLE contacts(id_contact serial PRIMARY KEY, id_user1 VARCHAR(255), id_user2 VARCHAR(255), contacted_on TIMESTAMP);