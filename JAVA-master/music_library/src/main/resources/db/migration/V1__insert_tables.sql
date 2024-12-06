CREATE TABLE genres (
                        genre_id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255)
);

CREATE TABLE artists (
                         artist_id INT PRIMARY KEY AUTO_INCREMENT,
                         artist_name VARCHAR(255),
                         genre_id INT,
                         FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);


CREATE TABLE albums (
                        album_id INT PRIMARY KEY AUTO_INCREMENT,
                        title VARCHAR(255),
                        release_date DATE,
                        artist_id INT,
                        FOREIGN KEY (artist_id) REFERENCES artists(artist_id)
);


CREATE TABLE songs (
                       song_id INT PRIMARY KEY AUTO_INCREMENT,
                       song_title VARCHAR(255),
                       duration INT,
                       album_id INT,
                       artist_id INT,
                       genre_id INT,
                       FOREIGN KEY (album_id) REFERENCES albums(album_id),
                       FOREIGN KEY (artist_id) REFERENCES artists(artist_id),
                       FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);
