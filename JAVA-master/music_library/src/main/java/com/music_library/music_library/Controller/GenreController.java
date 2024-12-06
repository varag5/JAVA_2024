package com.music_library.music_library.Controller;

import com.music_library.music_library.Repository.implementation.GenreRepositoryImp;
import com.music_library.music_library.domain.Genre;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepositoryImp genreRepository;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paginated list of Genres retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid url")
    })
    @GetMapping("/Pagination")
    public Page<Genre> getPaginatedGenre(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            @RequestParam(name = "sort", defaultValue = "genreId") String sortField,
            @RequestParam(name = "order", defaultValue = "asc") String sortOrder
    ) {
        if (!sortOrder.equalsIgnoreCase("desc") && !sortOrder.equalsIgnoreCase("asc")) {
            throw new IllegalArgumentException("Invalid sorting");
        }

        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        return genreRepository.getPaginatedGenre(pageable);
    }




}