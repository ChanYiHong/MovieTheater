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
    public void saveMovie() throws Exception {
        //given
        Movie movie = Movie.builder().movieName("name").build();

        Long id = movieRepository.save(movie);

        System.out.println("id: " + id);

        //when

        Movie findMovie = movieRepository.findOne(id);

        //then

        assertThat(findMovie.getMovieName()).isEqualTo(movie.getMovieName());

    }

    @Test
    public void findAllMovie() throws Exception {
        //given

        Movie movie1 = Movie.builder().movieName("name1").build();
        Movie movie2 = Movie.builder().movieName("name2").build();
        Movie movie3 = Movie.builder().movieName("name3").build();

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);

        //when

        List<Movie> movies = movieRepository.findAll();

        //then
        assertThat(movies.get(0).getMovieName()).isEqualTo(movie1.getMovieName());
        assertThat(movies.get(1).getMovieName()).isEqualTo(movie2.getMovieName());
        assertThat(movies.get(2).getMovieName()).isEqualTo(movie3.getMovieName());

    }

}