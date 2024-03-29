package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Movie;

import ChanuE.MovieTheater.repository.movie.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@WebAppConfiguration
@Transactional
public class IndexControllerTest {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Autowired
    MovieRepository movieRepository;

    Movie movie1;
    Movie movie2;
    Movie movie3;

    @BeforeEach
    public void setting(){

        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();

        movie1 = Movie.builder().title("name1").build();
        movie2 = Movie.builder().title("name2").build();
        movie3 = Movie.builder().title("name3").build();

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
    }

    @Test
    public void mainPage() throws Exception {
        //given
        //when
        //then

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("movies"))
                .andExpect(model().attribute("movies", hasSize(3)));
    }

    @AfterEach
    public void tearDown(){
        movieRepository.delete(movie1);
        movieRepository.delete(movie2);
        movieRepository.delete(movie3);
    }

}