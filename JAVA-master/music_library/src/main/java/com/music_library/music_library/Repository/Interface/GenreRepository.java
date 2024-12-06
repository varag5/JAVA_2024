package com.music_library.music_library.Repository.Interface;

import com.music_library.music_library.domain.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreRepository {

    Genre addGenre(Genre genre);

    Genre updateGenre(Genre genre);

    //get genre by id
    Genre getGenreById(Long id);

    List<Genre> getAllGenres();
    Page<Genre> getPaginatedGenre(Pageable pageable);


}
