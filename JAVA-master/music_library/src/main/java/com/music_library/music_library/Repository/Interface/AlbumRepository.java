package com.music_library.music_library.Repository.Interface;

import com.music_library.music_library.domain.Album;

import java.util.List;

public interface AlbumRepository {

    List<Album> getAllAlbums();
}
