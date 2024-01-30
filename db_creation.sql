CREATE SCHEMA IF NOT EXISTS corporate_wellness

CREATE TABLE IF NOT EXISTS corporate_wellness.company
(
    company_id SERIAL PRIMARY KEY,
    name varchar(255),
    location varchar(255),
    logo_link varchar(255)
);

---------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.profile
(
    user_id SERIAL PRIMARY KEY,
    company_id INT NOT NULL REFERENCES corporate_wellness.company(company_id),
    name varchar(255),
    surname varchar(255),
    date_of_birth date,
    email varchar(255) NOT NULL UNIQUE,
    nickname varchar(255),
    sex varchar(255),
    waistline numeric(6,2),
    height numeric(6,2),
    weight numeric(6,2),
    heart_rate numeric(6,2),
    step_length numeric(6,2)
);

---------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.route
(
    route_id SERIAL PRIMARY KEY,
    company_id INT NOT NULL REFERENCES  corporate_wellness.company(company_id),
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

CREATE TABLE IF NOT EXISTS corporate_wellness.heartratehistory
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
        INSERT INTO corporate_wellness.heartratehistory (user_id, heart_rate, timestamp_update)
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

CREATE TABLE IF NOT EXISTS corporate_wellness.weighthistory
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
        INSERT INTO corporate_wellness.weighthistory (user_id, weight, timestamp_update)
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

CREATE TABLE IF NOT EXISTS corporate_wellness.steplengthistory
(
    update_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL references corporate_wellness.profile(user_id),
    step_length numeric(6,2),
    timestamp_update TIMESTAMP WITH TIME ZONE
);

CREATE OR REPLACE FUNCTION update_steplength_function()
RETURNS TRIGGER AS $$
BEGIN
    -- Check if the specific column is updated
    IF NEW.weight <> OLD.weight THEN
        -- Insert a new row into the audit table
        INSERT INTO corporate_wellness.steplengthistory (user_id, step_length, timestamp_update)
        VALUES (NEW.user_id, NEW.step_length, now());
    END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER update_steplength_trigger
AFTER UPDATE ON corporate_wellness.profile
FOR EACH ROW
EXECUTE FUNCTION update_steplength_function();

---------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.profileauth
(
    auth_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL UNIQUE references corporate_wellness.profile(user_id),
    email varchar(255) NOT NULL UNIQUE references corporate_wellness.profile(email),
    hashed_password varchar(255)
);

---------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.loginhistory
(
    login_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL references corporate_wellness.profile(user_id),
    timestamp_login TIMESTAMP WITH TIME ZONE
);

---------------------------------------------------------------------

CREATE TABLE corporate_wellness.questionnaire (
    questionnaire_id SERIAL PRIMARY KEY,
    company_id INT NOT NULL REFERENCES corporate_wellness.company(company_id)
);

CREATE TABLE corporate_wellness.questionnaire_data (
	questionnaire_data_id SERIAL PRIMARY KEY,
    questionnaire_id INT NOT NULL REFERENCES corporate_wellness.questionnaire(questionnaire_id),
	language_code VARCHAR(10) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description varchar(1000),
	UNIQUE(questionnaire_id, language_code)
);

CREATE TABLE corporate_wellness.question (
    question_id SERIAL PRIMARY KEY,
    questionnaire_id INT NOT NULL REFERENCES corporate_wellness.questionnaire(questionnaire_id)
);

CREATE TABLE corporate_wellness.question_data (
	question_data_id SERIAL PRIMARY KEY,
    question_id INT NOT NULL REFERENCES corporate_wellness.question(question_id),
	language_code VARCHAR(10) NOT NULL,
    question_text VARCHAR(255) NOT NULL,
    question_type VARCHAR(16) NOT NULL,
	obligatory BOOLEAN NOT NULL,
	question_order INT NOT NULL,
	UNIQUE(question_id, language_code)
);

CREATE TABLE corporate_wellness.answer (
    answer_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL references corporate_wellness.profile(user_id),
    questionnaire_id INT NOT NULL REFERENCES corporate_wellness.questionnaire(questionnaire_id),
    question_id INT NOT NULL REFERENCES corporate_wellness.question(question_id),
	answer_type VARCHAR(16) NOT NULL,
	language_code VARCHAR(10) NOT NULL,
    answer_numeric INT,
    answer varchar(255),
    timestamp_answer TIMESTAMP WITH TIME ZONE
);

-----------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.apiuser
(
    api_user_id SERIAL PRIMARY KEY,
    username varchar(255),
    password varchar(255),
    role varchar(255)
);

-----------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.team
(
    team_id SERIAL PRIMARY KEY,
    name varchar(255),
    description varchar(255),
    logo_link varchar(255)
);

-----------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.project
(
    project_id SERIAL PRIMARY KEY,
    name varchar(255),
    description varchar(255),
    objective INT,
    logo_link varchar(255)
);

------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.companyproject
(
    companyproject_id SERIAL PRIMARY KEY,
    company_id INT NOT NULL REFERENCES corporate_wellness.company(company_id),
    project_id INT NOT NULL REFERENCES corporate_wellness.project(project_id)
);

------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.teamproject
(
    teamproject_id SERIAL PRIMARY KEY,
    team_id INT NOT NULL REFERENCES corporate_wellness.team(team_id),
    project_id INT NOT NULL REFERENCES corporate_wellness.project(project_id)
);

------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.teammember
(
    teammember_id SERIAL PRIMARY KEY,
    team_id INT NOT NULL REFERENCES corporate_wellness.team(team_id),
    user_id INT NOT NULL references corporate_wellness.profile(user_id)
);

------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.wallet
(
    wallet_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL references corporate_wellness.profile(user_id),
    balance INT
);

------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS corporate_wellness.forgotpassword
(
    forgotpassword_id SERIAL PRIMARY KEY,
    timestamp_request TIMESTAMP WITH TIME ZONE,
    email varchar(255) NOT NULL references corporate_wellness.profile(email),
    unique_code varchar(255)
);