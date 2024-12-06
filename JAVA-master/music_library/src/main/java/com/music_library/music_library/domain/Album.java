package com.music_library.music_library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "albums")
@Getter
@Setter
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long albumId;

    private String title;

    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    // Getters and setters
}