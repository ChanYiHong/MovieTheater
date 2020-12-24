package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.repository.movie.MovieSpringDataJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class MovieSpringDataJpaRepositoryTest {

    @Autowired
    MovieSpringDataJpaRepository movieRepository;

    @Autowired
    EntityManager em;

    @Test
    public void save() throws Exception {
        //given
        Movie movie = new Movie("반지의 제왕");
        movieRepository.save(movie);
        //when
        List<Movie> result = movieRepository.findMovieByMovieName(movie.getMovieName());
        //then
        assertThat(result.get(0).getMovieName()).isEqualTo(movie.getMovieName());
        assertThat(result.get(0)).extracting("movieName").containsExactly("반지의 제왕");
    }
}