package com.music_library.music_library.Controller.DTO;

public class ArtistRequestDTO {
    private String name;
    private Long genreId;

    // Constructors, getters, and setters

    public ArtistRequestDTO() {
    }

    public ArtistRequestDTO(String name, Long genreId) {
        this.name = name;
        this.genreId = genreId;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}
