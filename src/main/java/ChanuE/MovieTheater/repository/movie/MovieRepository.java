package ChanuE.MovieTheater.repository.movie;

import ChanuE.MovieTheater.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryCustom{

    Optional<Movie> findMovieByTitle(String name);

    @Query("select m, count(r), avg(coalesce(r.grade, 1)) from Movie m join Review r on r.movie = m where m.id = :id")
    List<Object[]> findMovieWithReviewCount(@PathVariable("id") Long id);

    @Query("select m, mi, count(r), avg(coalesce(r.grade, 1)) from Movie m left join MovieImage mi on mi.movie = m " +
            "left join Review r on r.movie = m where m.id = :id")
    List<Object[]> findMovieWithImageAndReviewCount(@PathVariable("id") Long id);

    // 메인 화면에 띄울 2개의 영화. (레이팅 높은 순)
//    @Query("select m, avg(coalesce(r.grade, 1)) as g from Movie m join Review r on r.movie = m order by g desc")
//    List<Object[]> findMovieWithAvgRatingForHomeView();

}
