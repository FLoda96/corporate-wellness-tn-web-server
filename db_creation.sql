CREATE TABLE IF NOT EXISTS corporate_wellness.profile
(
    user_id SERIAL NOT NULL,
    name varchar(255),
    surname varchar(255),
    email varchar(255) NOT NULL UNIQUE,
    nickname varchar(255),
    sex varchar(255),
    height numeric(6,2),
    weight numeric(6,2),
    heart_rate numeric(6,2),
    CONSTRAINT "Users_pkey" PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS corporate_wellness.route
(
    route_id SERIAL PRIMARY KEY,
    name varchar(255),
    description varchar(255)
);

CREATE TABLE IF NOT EXISTS corporate_wellness.routeperformance
(
    performance_id SERIAL PRIMARY KEY,
    route_id INT NOT NULL references corporate_wellness.route(route_id),
    user_id INT NOT NULL references corporate_wellness.profile(user_id),
    timestamp_start TIMESTAMP,
    timestamp_end TIMESTAMP
):