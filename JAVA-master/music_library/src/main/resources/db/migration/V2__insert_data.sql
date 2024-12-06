-- Insert data into genres table
INSERT INTO genres (name) VALUES ('Rock');
INSERT INTO genres (name) VALUES ('Pop');
INSERT INTO genres (name) VALUES ('Jazz');
-- Insert data into artists table
INSERT INTO artists (artist_name, genre_id) VALUES ('John Doe', 1); -- John Doe is a Rock artist
INSERT INTO artists (artist_name, genre_id) VALUES ('Jane Smith', 2); -- Jane Smith is a Pop artist
INSERT INTO artists (artist_name, genre_id) VALUES ('Bob Johnson', 3); -- Bob Johnson is a Jazz artist
-- Insert data into albums table
INSERT INTO albums (title, release_date, artist_id) VALUES ('Album 1', '2022-01-01', 1);
INSERT INTO albums (title, release_date, artist_id) VALUES ('Album 2', '2022-02-01', 2);
INSERT INTO albums (title, release_date, artist_id) VALUES ('Album 3', '2022-03-01', 3);
-- Insert data into songs table
INSERT INTO songs (song_title, duration, album_id, artist_id, genre_id) VALUES ('Song 1', 180, 1, 1, 1);
INSERT INTO songs (song_title, duration, album_id, artist_id, genre_id) VALUES ('Song 2', 210, 2, 2, 2);
INSERT INTO songs (song_title, duration, album_id, artist_id, genre_id) VALUES ('Song 3', 160, 3, 3, 3);
