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

    Optional<Movie> findMovieByMovieName(String name);

//    @Query("select m, a, s, d from Movie m left join Area a on a.movie = m " +
//            "left join SpecificArea s on s.area = a " +
//            "left join Date d on d.specificArea = s where m.movieName = :movieName")
//    List<Object[]> findMovieForReservation(@PathVariable("movieName") String movieName);

    @Query("select m, count(r), avg(coalesce(r.grade, 1)) from Movie m join Review r on r.movie = m where m.id = :id")
    List<Object[]> findMovieWithReviewCount(@PathVariable("id") Long id);

}
