package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.AgeLimit;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    EntityManager em;

    @Test
    public void getMovie() throws Exception {

        Movie movie = movieRepository.getOne(1L);
        System.out.println(movie);

    }

    @Test
    public void getMovieForReservation() throws Exception {

        List<Object[]> result = movieRepository.findMovieForReservation("반지의 제왕");

        result.forEach(objects -> System.out.println(objects.toString()));

    }

    @Test
    @Transactional
    @Rollback(false)
    public void converterTest() throws Exception {

        Movie movie = Movie.builder().movieName("Test").ageLimit(AgeLimit.NINETEEN).build();
        movieRepository.save(movie);

        Query nativeQuery = em.createQuery("Select m from Movie m where m.movieName = :name", Movie.class);
        nativeQuery.setParameter("name", "Test");
        List<Movie> results = nativeQuery.getResultList();

        System.out.println(results.get(0));

    }

}