package com.music_library.music_library.Repository.implementation;

import com.music_library.music_library.domain.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Repository
@RequiredArgsConstructor

public class GenreRepositoryImp implements com.music_library.music_library.Repository.Interface.GenreRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Genre addGenre(Genre genre) {
        entityManager.persist(genre);
        return genre;
    }

    public List<Genre> getAllGenres() {
        try {
            return entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
        } catch (Exception e) {
            log.error("Error fetching all genres", e);
            throw new RuntimeException("Error fetching all genres", e);
        }
    }

    @Override
    @Transactional
    public Genre updateGenre(Genre genre) {
        return null;
    }

    @Override
    public Genre getGenreById(Long id) {
        return entityManager.find(Genre.class, id);
    }


    //@Override
    public Page<Genre> getAllGenresPaged(int page, int size) {
        try {
            List<Genre> genres = entityManager.createQuery("SELECT g FROM Genre g", Genre.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();

            return new PageImpl<>(genres, PageRequest.of(page, size), countTotalGenres());
        } catch (Exception e) {
            log.error("Error fetching paged genres", e);
            throw new RuntimeException("Error fetching paged genres", e);
        }
    }

    private long countTotalGenres() {
        return entityManager.createQuery("SELECT COUNT(g) FROM Genre g", Long.class)
                .getSingleResult();
    }

    public Page<Genre> getAllGenresPaged(int page, int size, Sort sort) {
        try {
            List<Genre> genres = entityManager.createQuery("SELECT g FROM Genre g", Genre.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();

            return new PageImpl<>(genres, PageRequest.of(page, size, sort), countTotalGenres());
        } catch (Exception e) {
            log.error("Error fetching paged genres", e);
            throw new RuntimeException("Error fetching paged genres", e);
        }
    }


    //@Override
    public Page<Genre> getPaginatedGenre(Pageable pageable) {
        Sort sort = pageable.getSort();
        String orderByClause = sort.stream()
                .map(order -> order.getProperty() + " " + order.getDirection())
                .collect(Collectors.joining(", "));

        TypedQuery<Genre> query = entityManager.createQuery("SELECT p FROM Genre p ORDER BY " + orderByClause, Genre.class);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Genre> resultList = query.getResultList();

        return new PageImpl<>(resultList, pageable, getCount());
    }

    private long getCount() {
        return entityManager.createQuery("SELECT COUNT(p) FROM Genre p", Long.class).getSingleResult();
    }









}





