ALTER TABLE user_entity ADD COLUMN "is_infected" BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE user_entity ADD COLUMN "infection_date" VARCHAR(255);

CREATE TABLE contacts(id_contact serial PRIMARY KEY, id_user1 VARCHAR(255), id_user2 VARCHAR(255), contacted_on VARCHAR(255));

ALTER TABLE contacts ALTER COLUMN contacted_on TYPE VARCHAR(255);


