-- -- Create Albums Table
-- CREATE TABLE Albums (
--     albumId INT PRIMARY KEY auto_increment,
--     albumTitle VARCHAR(255),
--     releaseDate DATE,
--     artist_id INT,
--     FOREIGN KEY (artist_id) REFERENCES Artists(artistId)
-- );
--
-- -- Create Artists Table
-- CREATE TABLE Artists (
--     artistId INT PRIMARY KEY auto_increment,
--     artistName VARCHAR(255),
--     genre_id INT,
--     FOREIGN KEY (genre_id) REFERENCES Genres(genreId)
-- );
--
-- -- Create Songs Table
-- CREATE TABLE Songs (
--     songId INT PRIMARY KEY auto_increment,
--     songTitle VARCHAR(255),
--     duration INT,
--     album_id INT,
--     artist_id INT,
--     genre_id INT,
--     FOREIGN KEY (album_id) REFERENCES Albums(albumId),
--     FOREIGN KEY (artist_id) REFERENCES Artists(artistId),
--     FOREIGN KEY (genre_id) REFERENCES Genres(genreId)
-- );
--
-- -- Create Genres Table
-- CREATE TABLE Genres (
--     genreId INT PRIMARY KEY auto_increment,
--     genreName VARCHAR(255)
-- );
-- Create Albums Table
CREATE TABLE "users" (
                         id INTEGER NOT NULL PRIMARY KEY auto_increment,
                         username VARCHAR(100),
                         email VARCHAR(100),
                         CONSTRAINT username_unique UNIQUE (username)
);

CREATE TABLE "tournament_group" (
                                    id INTEGER NOT NULL PRIMARY KEY auto_increment,
                                    name TEXT
);

CREATE TABLE "team" (
                        id INTEGER NOT NULL PRIMARY KEY auto_increment,
                        name TEXT,
                        ranking_points NUMERIC(10,2),
                        flag_path TEXT,
                        tournament_group_id INTEGER,
                        CONSTRAINT fk_tournament_group_team
                            FOREIGN KEY(tournament_group_id)
                                REFERENCES tournament_group(id)
);

CREATE TABLE "result" (
                          id INTEGER NOT NULL PRIMARY KEY auto_increment,
                          home_team_score INTEGER,
                          away_team_score INTEGER,
                          result_type varchar(50)
);

CREATE TABLE "match" (
                         id INTEGER NOT NULL PRIMARY KEY auto_increment,
                         start_time TIMESTAMP,
                         stadium TEXT,
                         result_id INTEGER,
                         home_team_id INTEGER,
                         away_team_id INTEGER,
                         tournament_group_id INTEGER NOT NULL,
                         CONSTRAINT fk_result_match
                             FOREIGN KEY(result_id)
                                 REFERENCES result(id),
                         CONSTRAINT fk_home_team
                             FOREIGN KEY(home_team_id)
                                 REFERENCES team(id),
                         CONSTRAINT fk_away_team
                             FOREIGN KEY(away_team_id)
                                 REFERENCES team(id),
                         CONSTRAINT fk_tournament_group_match
                             FOREIGN KEY(tournament_group_id)
                                 REFERENCES tournament_group(id)
);

CREATE TABLE "bet" (
                       id INTEGER NOT NULL PRIMARY KEY auto_increment,
                       result_id INTEGER,
                       match_id INTEGER,
                       user_id INTEGER,
                       CONSTRAINT fk_result_bet
                           FOREIGN KEY(result_id)
                               REFERENCES result(id),
                       CONSTRAINT fk_match_bet
                           FOREIGN KEY(match_id)
                               REFERENCES match(id),
                       CONSTRAINT fk_users_bet
                           FOREIGN KEY(user_id)
                               REFERENCES users(id),
                       CONSTRAINT match_user_unique UNIQUE (user_id, match_id)
);
create sequence hibernate_sequence;