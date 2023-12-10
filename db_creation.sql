CREATE TABLE IF NOT EXISTS corporate_wellness.profile
(
    user_id SERIAL PRIMARY KEY,
    name varchar(255),
    surname varchar(255),
    email varchar(255) NOT NULL UNIQUE,
    nickname varchar(255),
    sex varchar(255),
    height numeric(6,2),
    weight numeric(6,2),
    heart_rate numeric(6,2),
);

---------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.route
(
    route_id SERIAL PRIMARY KEY,
    name varchar(255),
    description varchar(255)
);

---------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.routeperformance
(
    performance_id SERIAL PRIMARY KEY,
    route_id INT NOT NULL references corporate_wellness.route(route_id),
    user_id INT NOT NULL references corporate_wellness.profile(user_id),
    timestamp_start TIMESTAMP WITH TIME ZONE,
    heart_rate_start numeric(6,2),
    timestamp_end TIMESTAMP WITH TIME ZONE,
    heart_rate_end numeric(6,2)
);

---------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.heartrateupdate
(
    update_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL references corporate_wellness.profile(user_id),
    heart_rate numeric(6,2),
    timestamp_update TIMESTAMP WITH TIME ZONE
);

CREATE OR REPLACE FUNCTION update_heart_rate_function()
RETURNS TRIGGER AS $$
BEGIN
    -- Check if the specific column is updated
    IF NEW.heart_rate <> OLD.heart_rate THEN
        -- Insert a new row into the audit table
        INSERT INTO corporate_wellness.heartrateupdate (user_id, heart_rate, timestamp_update)
        VALUES (NEW.user_id, NEW.heart_rate, now());
    END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER update_heart_rate_trigger
AFTER UPDATE ON corporate_wellness.profile
FOR EACH ROW
EXECUTE FUNCTION update_heart_rate_function();

---------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.weightupdate
(
    update_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL references corporate_wellness.profile(user_id),
    weight numeric(6,2),
    timestamp_update TIMESTAMP WITH TIME ZONE
);

CREATE OR REPLACE FUNCTION update_weight_function()
RETURNS TRIGGER AS $$
BEGIN
    -- Check if the specific column is updated
    IF NEW.weight <> OLD.weight THEN
        -- Insert a new row into the audit table
        INSERT INTO corporate_wellness.weightupdate (user_id, weight, timestamp_update)
        VALUES (NEW.user_id, NEW.weight, now());
    END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER update_weight_trigger
AFTER UPDATE ON corporate_wellness.profile
FOR EACH ROW
EXECUTE FUNCTION update_weight_function();

---------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.profileauth
(
    auth_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL references corporate_wellness.profile(user_id),
    email varchar(255) NOT NULL references corporate_wellness.profile(email),
    hashed_password varchar(255),
    salt varchar(255)
);

---------------------------------------------------------------------

CREATE TABLE corporate_wellness.questionnaire (
    questionnaire_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description varchar(255)
);

CREATE TABLE corporate_wellness.question (
    question_id SERIAL PRIMARY KEY,
    questionnaire_id INTEGER REFERENCES corporate_wellness.questionnaire(questionnaire_id),
    question_text TEXT NOT NULL
);

CREATE TABLE corporate_wellness.response (
    response_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL references corporate_wellness.profile(user_id),
    questionnaire_id INTEGER REFERENCES corporate_wellness.questionnaire(questionnaire_id),
    question_id INTEGER REFERENCES corporate_wellness.question(question_id),
    answer varchar(255),
    timestamp_answer TIMESTAMP WITH TIME ZONE
);