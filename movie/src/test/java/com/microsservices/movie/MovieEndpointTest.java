package com.microsservices.movie;

import com.microsservices.core.model.Movie;
import com.microsservices.movie.endpoint.service.MovieService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.HttpMethod.DELETE;

/**
 * @author joao4018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovieEndpointTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    private MovieService movieService;
    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().basicAuthorization("toyo", "ala");
        }
    }

    @Before
    public void setup() {
        Movie movie = new Movie(1L, "Legolas", 10L);
        BDDMockito.when(movieService.findOne(movie.getId())).thenReturn(java.util.Optional.of(movie));
    }


    @Test
    public void getMovieByIdShouldReturnStatusCode200() {
        ResponseEntity<Movie> response = restTemplate.getForEntity("/v1/admin/movie/movies/{id}", Movie.class, 1L);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    public void deleteWhenUserHasRoleAdminAndStudentExistsShouldReturnStatusCode200() {
        BDDMockito.doNothing().when(movieService).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/v1/admin/movie/movies/{id}", DELETE, null, String.class, 1L);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @WithMockUser(username = "xx", password = "xx", roles = {"USER", "ADMIN"})
    public void deleteWhenUserHasRoleAdminAndStudentDoesNotExistShouldReturnStatusCode404() throws Exception {
        BDDMockito.doNothing().when(movieService).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v0/admin/movie/movies/{id}", -1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void createWhenNameIsNullShouldReturnStatusCode400BadRequest() throws Exception {
        Movie movie = new Movie(3L, null, 10L);
        BDDMockito.when(movieService.save(movie)).thenReturn(movie);
        ResponseEntity<String> response = restTemplate.postForEntity("/v1/admin/movie/movies/", movie, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void createShouldPersistDataAndReturnStatusCode201() throws Exception {
        Movie movie = new Movie(3L, "Sam", 10L);
        BDDMockito.when(movieService.save(movie)).thenReturn(movie);
        ResponseEntity<Movie> response = restTemplate.postForEntity("/v1/admin/movie/movies/", movie, Movie.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody().getId()).isNotNull();
    }
}