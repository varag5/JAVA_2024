package com.music_library.music_library.Repository.Interface;

import com.music_library.music_library.domain.Song;

import java.util.List;

public interface SongRepository {

    List<Song> getAllSongs();
}