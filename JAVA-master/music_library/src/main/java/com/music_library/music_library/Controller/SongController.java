package com.music_library.music_library.Controller;

import com.music_library.music_library.Repository.Interface.SongRepository;
import com.music_library.music_library.domain.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Songs")
@RequiredArgsConstructor
public class SongController {

    private final SongRepository songRepository;

    @GetMapping
    public List<Song> getAllSongs() {

        //Page<Song> songs = songRepository.findAll(PageRequest.of(0, limit, sort));
        return songRepository.getAllSongs();
    }
}
