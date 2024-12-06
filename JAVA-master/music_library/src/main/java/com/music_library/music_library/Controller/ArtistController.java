package com.music_library.music_library.Controller;

import com.music_library.music_library.Controller.DTO.ArtistRequestDTO;
import com.music_library.music_library.Repository.implementation.GenreRepositoryImp;
import com.music_library.music_library.domain.Artist;
import com.music_library.music_library.Repository.implementation.ArtistRepositoryImp;
import com.music_library.music_library.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Artist")
@RequiredArgsConstructor
@RestController
public class ArtistController {
    private final ArtistRepositoryImp artistRepository;
    private  final GenreRepositoryImp genreRepository;
    @GetMapping
    public List<Artist> getAllnames() {

        var x = artistRepository.getAllArtists();
        return artistRepository.getAllArtists();
    }
    @PostMapping
    public Artist addArtist(@RequestBody ArtistRequestDTO artist) {
        try {
            artistRepository.addArtist(artist);
            return null;

        } catch (Exception e) {

            return null;
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateArtist(@PathVariable Long id, @RequestBody ArtistRequestDTO artistRequestDTO) {
        try {
            // Check if the artist with the given ID exists
            Artist existingArtist = artistRepository.getArtistById(id);
            if (existingArtist == null) {
                return ResponseEntity.notFound().build();
            }

            // Update existing artist with data from DTO
            existingArtist.setName(artistRequestDTO.getName());

            // Assuming you have a GenreService to retrieve the Genre by ID
            Genre genre = genreRepository.getGenreById(artistRequestDTO.getGenreId());
            existingArtist.setGenre(genre);

            // Update the entity
            artistRepository.updateArtist(existingArtist);

            return ResponseEntity.ok("Artist updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating artist");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteArtist(@PathVariable Long id) {
        try {
            // Check if the artist with the given ID exists
            Artist existingArtist = artistRepository.getArtistById(id);
            if (existingArtist == null) {
                return ResponseEntity.notFound().build();
            }

            // Delete the entity
            artistRepository.deleteArtist(id);

            return ResponseEntity.ok("Artist deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting artist");
        }
    }


}