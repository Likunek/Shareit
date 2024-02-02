--DROP TABLE users CASCADE;
--DROP TABLE items CASCADE;
--DROP TABLE booking CASCADE;
--DROP TABLE comments CASCADE;


CREATE TABLE IF NOT EXISTS users (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
email varchar(320),
name varchar(100),
CONSTRAINT AK_email UNIQUE(email)
);

CREATE TABLE IF NOT EXISTS requests (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
description varchar(300),
user_id BIGINT REFERENCES users(id),
create_date timestamp
);

CREATE TABLE IF NOT EXISTS items (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name varchar(100),
description varchar(300),
available bool default false,
user_id BIGINT REFERENCES users(id),
--request_id BIGINT REFERENCES requests(id)
CONSTRAINT AK_id UNIQUE(id, user_id)
);
CREATE TABLE IF NOT EXISTS booking (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
start_time TIMESTAMP WITHOUT TIME ZONE,
end_time timestamp WITHOUT time zone,
item_id BIGINT REFERENCES items(id),
user_id BIGINT REFERENCES users(id),
status varchar(100),
CONSTRAINT AK_booking UNIQUE(id, item_id)
);

CREATE TABLE IF NOT EXISTS comments (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
text varchar(300),
author_name varchar(50),
item_id BIGINT REFERENCES items(id),
user_id BIGINT REFERENCES users(id),
created timestamp WITHOUT time zone
);