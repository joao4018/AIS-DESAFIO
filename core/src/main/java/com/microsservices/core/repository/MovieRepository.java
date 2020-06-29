package com.microsservices.core.repository;



import com.microsservices.core.model.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author joao4018
 */
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
//    Movie findOne(Long id);
//
//    List<Movie> findByNameIgnoreCaseContaining(String name);
//
//    void delete(Long id);
}
