package ChanuE.MovieTheater.repository.movie;

import ChanuE.MovieTheater.domain.AgeLimit;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.MovieImage;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    @Rollback(false)
    public void converterTest() throws Exception {

        Movie movie = Movie.builder().title("Test").ageLimit(AgeLimit.NINETEEN).build();
        movieRepository.save(movie);

        Query nativeQuery = em.createQuery("Select m from Movie m where m.title = :name", Movie.class);
        nativeQuery.setParameter("name", "Test");
        List<Movie> results = nativeQuery.getResultList();

        System.out.println(results.get(0));

    }

    @Test
    public void movieListTest() throws Exception {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Object[]> result = movieRepository.findAllBySearchCond(new MovieSearch(), pageable);

        List<Object[]> content = result.getContent();
        content.forEach(objects -> {
            System.out.println((Movie)objects[0]);
            System.out.println((Long)objects[1]);
            System.out.println((Double)objects[2]);
        });
    }

    @Test
    public void findMovieByTitleTest() throws Exception {
        Optional<Movie> result = movieRepository.findMovieByTitle("소울");
        Movie movie = result.get();
        System.out.println(movie);
    }

    @Test
    void findMovieWithImageAndReviewCntTest(){
        List<Object[]> result = movieRepository.findMovieWithImageAndReviewCount(6L);

        Object[] objects = result.get(0);
        Movie movie = (Movie) objects[0];
        MovieImage movieImage = (MovieImage) objects[1];
        Long reviewCnt = (Long)objects[2];
        Double avg = (Double)objects[3];

        System.out.println(movie);
        System.out.println(movieImage);
        System.out.println(reviewCnt);
        System.out.println(avg);
    }

    @Test
    void findTwoMovieForMainPage(){
        List<Object[]> result = movieRepository.findMovieWithAvgRatingForHomeView();

        for (Object[] objects : result) {
            Movie movie = (Movie) objects[0];
//            Double avg = (Double) objects[1];

            System.out.println(movie);
//            System.out.println(avg);
        }
    }

}