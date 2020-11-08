package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.Area;
import ChanuE.MovieTheater.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AreaRepository {

    private final EntityManager em;

    public Long save(Area area){
        em.persist(area);
        return area.getId();
    }

    public Area findOne(Long id){
        return em.find(Area.class, id);
    }

    public List<Area> findAllAreaByMovieId(Long movieId){
        return em.createQuery("select ", Area.class)
                .setParameter("movieId", movieId)
                .getResultList();

        //SELECT m, t FROM Member m LEFT JOIN m.team t on t.name = 'A'  

        //SELECT m, t FROM Member m LEFT JOIN Team t on m.username = t.name 

        //select t from Team t join fetch t.members  where t.name = ‘팀A'
    }

//    public List<Area> findAreaByMovie(Long movieId){
//        return em.createQuery("select m.areas from Movie m where m.id =:movie_id", Movie.class)
//                .setParameter("movie_id", movieId)
//                .getResultList();
//    }

    public List<Area> findAllAreaByAreaName(String name){
        return em.createQuery("select a from Area a where a.name = :name", Area.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Area> findAll(){
        return em.createQuery("select a from Area a",Area.class)
                .getResultList();
    }

    public void delete(Area area){
        em.remove(area);
    }
}
