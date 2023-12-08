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
)