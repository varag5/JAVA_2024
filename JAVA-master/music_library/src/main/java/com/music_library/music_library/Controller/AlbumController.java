package com.music_library.music_library.Controller;

import com.music_library.music_library.Repository.implementation.AlbumRepositoryImp;
import com.music_library.music_library.domain.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Album")
@RequiredArgsConstructor
@RestController
public class AlbumController {

    private final AlbumRepositoryImp albumRepository;

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.getAllAlbums();
    }
}