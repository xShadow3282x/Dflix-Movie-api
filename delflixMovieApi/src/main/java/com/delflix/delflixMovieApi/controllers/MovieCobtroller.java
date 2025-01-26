package com.delflix.delflixMovieApi.controllers;

import com.delflix.delflixMovieApi.dto.MovieDto;
import com.delflix.delflixMovieApi.entities.Movie;
import com.delflix.delflixMovieApi.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController@RequestMapping("/api/v1/movie")
public class MovieCobtroller {
    private final MovieService movieService;


    public MovieCobtroller(MovieService movieService) {
        this.movieService = movieService;
    }
    @PostMapping("/add-movie")
    public ResponseEntity<Movie> addMovieHandler(@RequestPart MultipartFile file,@RequestPart String movieDto) {
    MovieDto dto=convertToMovieDto(movieDto);
    return new ResponseEntity<>(movieService.addMovie(dto,file), HttpStatus.CREATED)
    }
    private MovieDto convertToMovieDto(String movieDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieDtoObj,MovieDto.class);
    }

}
