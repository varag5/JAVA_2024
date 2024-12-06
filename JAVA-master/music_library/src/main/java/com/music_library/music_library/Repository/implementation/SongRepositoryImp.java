package com.music_library.music_library.Repository.implementation;

import com.music_library.music_library.domain.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SongRepositoryImp implements com.music_library.music_library.Repository.Interface.SongRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Song> getAllSongs() {
        try {
            return entityManager.createQuery("SELECT s FROM Song s", Song.class).getResultList();
        } catch (Exception e) {
            log.error("Error fetching all songs", e);
            throw new RuntimeException("Error fetching all songs", e);
        }
    }
}