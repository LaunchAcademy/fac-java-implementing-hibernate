DROP TABLE IF EXISTS deep_thoughts;

CREATE TABLE deep_thoughts (
  id SERIAL PRIMARY KEY,
  description TEXT,
  rating INTEGER
);

