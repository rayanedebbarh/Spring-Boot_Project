-- Activity 7: DDL Script for Book Library Database
-- Database: SQLite
-- Entities: Book, Library (One-to-Many relationship)

-- Create Libraries table
CREATE TABLE IF NOT EXISTS libraries (
                                         id INTEGER PRIMARY KEY,
                                         name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL
    );

-- Create Books table with foreign key to Libraries
CREATE TABLE IF NOT EXISTS books (
                                     id INTEGER PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    year INTEGER NOT NULL,
    library_id INTEGER,
    FOREIGN KEY (library_id) REFERENCES libraries(id)
    );

-- Create sequence tables for ID generation
CREATE TABLE IF NOT EXISTS libraries_seq (
                                             next_val BIGINT
);

CREATE TABLE IF NOT EXISTS books_seq (
                                         next_val BIGINT
);

-- Initialize sequences
INSERT INTO libraries_seq VALUES (1);
INSERT INTO books_seq VALUES (1);

-- Index for better query performance on foreign key
CREATE INDEX IF NOT EXISTS idx_books_library_id ON books(library_id);