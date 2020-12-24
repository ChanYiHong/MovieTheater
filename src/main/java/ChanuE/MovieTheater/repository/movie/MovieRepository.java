package ChanuE.MovieTheater.repository.movie;

import ChanuE.MovieTheater.domain.Area;
import ChanuE.MovieTheater.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepository {

    private final EntityManager em;

    public Long save(Movie movie){
        em.persist(movie);
        return movie.getId();
    }

    public Movie findOne(Long id){
        return em.find(Movie.class, id);
    }

    public List<Movie> findOneByName(String name){
        return em.createQuery("select m from Movie m where m.name = :name", Movie.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Movie> findAll(){
        return em.createQuery("select m from Movie m", Movie.class)
                .getResultList();
    }

    public void deleteOne(Movie movie){
        em.remove(movie);
    }
}
