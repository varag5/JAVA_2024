package com.music_library.music_library.Repository.implementation;

import com.music_library.music_library.Controller.DTO.ArtistRequestDTO;
import com.music_library.music_library.domain.Album;
import com.music_library.music_library.domain.Artist;
import com.music_library.music_library.domain.Genre;
import com.music_library.music_library.domain.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor

public class ArtistRepositoryImp implements com.music_library.music_library.Repository.Interface.ArtistRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final GenreRepositoryImp genreRepository;

    @Override
    public List<Artist> getAllArtists() {
        try {
            return entityManager.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
        } catch (Exception e) {
            log.error("Error fetching all artists", e);
            throw new RuntimeException("Error fetching all artists", e);
        }
    }

    @Override
    public Artist getArtistById(Long id) {
        return entityManager.find(Artist.class, id);
    }

    @Override
    @Transactional
    public Artist addArtist(ArtistRequestDTO artistRequestDTO) {
        try {
            // Convert DTO to entity
            Artist artist = new Artist();
            artist.setName(artistRequestDTO.getName());

            // Assuming you have a GenreService to retrieve the G   enre by ID
            Genre genre = genreRepository.getGenreById(artistRequestDTO.getGenreId());
            artist.setGenre(genre);

            // Persist the entity
            entityManager.persist(artist);

            return artist;
        } catch (Exception e) {
            log.error("Error adding artist", e);
            throw new RuntimeException("Error adding artist", e);
        }
    }

    @Override
    @Transactional
    public Artist updateArtist(Artist artist) {
        return entityManager.merge(artist);
    }

    @Override
    @Transactional
    public void deleteArtist(Long id) {
        try {
            Artist artist = entityManager.find(Artist.class, id);
            if (artist != null) {
                // Manually delete related records in the songs table
                List<Song> songs = entityManager.createQuery("SELECT s FROM Song s WHERE s.album.artist.id = :artistId", Song.class)
                        .setParameter("artistId", id)
                        .getResultList();

                // Delete related songs
                for (Song song : songs) {
                    entityManager.remove(song);
                }

                // Manually delete related records in the albums table
                List<Album> albums = entityManager.createQuery("SELECT a FROM Album a WHERE a.artist.id = :artistId", Album.class)
                        .setParameter("artistId", id)
                        .getResultList();

                // Delete related albums
                for (Album album : albums) {
                    entityManager.remove(album);
                }

                // Delete the artist
                entityManager.remove(artist);
            }
        } catch (Exception e) {
            log.error("Error deleting artist with ID: " + id, e);
            throw new RuntimeException("Error deleting artist", e);
        }
    }
}
