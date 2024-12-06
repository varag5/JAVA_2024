package com.music_library.music_library.Repository.implementation;

import com.music_library.music_library.domain.Album;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AlbumRepositoryImp implements com.music_library.music_library.Repository.Interface.AlbumRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Album> getAllAlbums() {
        try {
            return entityManager.createQuery("SELECT a FROM Album a", Album.class).getResultList();
        } catch (Exception e) {
            log.error("Error fetching all albums", e);
            throw new RuntimeException("Error fetching all albums", e);
        }
    }
}