DROP TABLE IF EXISTS webmarket.products;

CREATE TABLE webmarket.products
(
  id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  release_year INTEGER NOT NULL,
  brand TEXT NOT NULL,
  type TEXT NOT NULL,
  description TEXT NOT NULL,
  price DOUBLE DEFAULT 0.0,
  amount INTEGER DEFAULT 0,
  date TIMESTAMP DEFAULT now()
);