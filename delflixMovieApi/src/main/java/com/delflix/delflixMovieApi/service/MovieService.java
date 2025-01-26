package com.delflix.delflixMovieApi.service;

import com.delflix.delflixMovieApi.dto.MovieDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    MovieDto addMovie(MovieDto movieDto , MultipartFile file) throws IOException;
    MovieDto getMovie(Integer movieId);
    List<MovieDto> getAllMovies();
}
