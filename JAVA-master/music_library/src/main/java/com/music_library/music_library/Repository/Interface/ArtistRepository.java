package com.music_library.music_library.Repository.Interface;
import com.music_library.music_library.Controller.DTO.ArtistRequestDTO;
import com.music_library.music_library.domain.Artist;
import java.util.List;

public interface ArtistRepository {

    List<Artist> getAllArtists();

    Artist getArtistById(Long id);

    Artist addArtist(ArtistRequestDTO artist);

    Artist updateArtist(Artist artist);

    void deleteArtist(Long id);

}