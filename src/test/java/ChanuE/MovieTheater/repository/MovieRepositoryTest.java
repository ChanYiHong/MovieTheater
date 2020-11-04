package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
        Movie movie = Movie.builder().name("name").build();

        Long id = movieRepository.save(movie);

        System.out.println("id: " + id);

        //when

        Movie findMovie = movieRepository.findOne(id);

        //then

        assertThat(findMovie.getName()).isEqualTo(movie.getName());

    }

    @Test
    public void findAllMovie() throws Exception {
        //given

        Movie movie1 = Movie.builder().name("name1").build();
        Movie movie2 = Movie.builder().name("name2").build();
        Movie movie3 = Movie.builder().name("name3").build();

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);

        //when

        List<Movie> movies = movieRepository.findAll();

        //then
        assertThat(movies.get(0).getName()).isEqualTo(movie1.getName());
        assertThat(movies.get(1).getName()).isEqualTo(movie2.getName());
        assertThat(movies.get(2).getName()).isEqualTo(movie3.getName());

    }

}