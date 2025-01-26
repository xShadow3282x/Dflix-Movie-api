package com.delflix.delflixMovieApi.service;

import com.delflix.delflixMovieApi.dto.MovieDto;
import com.delflix.delflixMovieApi.entities.Movie;
import com.delflix.delflixMovieApi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final FileService fileService;
    @Value("${posters:/default/path}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;

    public MovieServiceImpl(MovieRepository movieRepository, FileService fileService) {
        this.movieRepository = movieRepository;
        this.fileService = fileService;
    }

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
        // 1. upload the file
        String uploadedFIleName=fileService.uploadFile(path,file);
        // 2. set the value of field 'poster' as filename
        movieDto.setPoster(uploadedFIleName);
        // 3. map dto to Movie object
        Movie movie=new Movie(movieDto.getMovieId(),movieDto.getTitle(),movieDto.getDirector(),movieDto.getStudio(),movieDto.getMovieCast(),movieDto.getReleaseYear(),movieDto.getPoster());
        //4. save the movie object -> saved movie object
        Movie savedMovie=movieRepository.save(movie);
        // 5. generate the poster url
        String posterUrl=baseUrl+"/file/"+uploadedFIleName;

        // 6. map Movie object to dto object and return it
        MovieDto response=new MovieDto(savedMovie.getMovieId(),savedMovie.getTitle(),savedMovie.getDirector(),savedMovie.getStudio(),savedMovie.getMovieCast(),savedMovie.getReleaseYear(),savedMovie.getPoster(),posterUrl);
        return response;
    }

    @Override
    public MovieDto getMovie(Integer movieId) {
        return null;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return List.of();
    }
}
