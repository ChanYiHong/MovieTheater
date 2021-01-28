package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void getMovie() throws Exception {

        Movie movie = movieRepository.getOne(1L);
        System.out.println(movie);

    }

}