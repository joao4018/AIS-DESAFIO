package com.microsservices.movie.endpoint.service;

import com.microsservices.core.model.Movie;
import com.microsservices.core.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author joao4018
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {
    private final MovieRepository movieRepository;

    public Iterable<Movie> list(Pageable pageable) {
        log.info("Listing all movies");
        return movieRepository.findAll(pageable);
    }

    public Movie save(Movie pageable) {
        log.info("Listing all movies");
        return movieRepository.save(pageable);
    }

    public Optional<Movie> findOne(Long id) {
        log.info("Listing one movie");
        return movieRepository.findById(id);
    }

    public void delete(Long id) {
        log.info("delete movie by id");
        movieRepository.deleteById(id);
    }
}
