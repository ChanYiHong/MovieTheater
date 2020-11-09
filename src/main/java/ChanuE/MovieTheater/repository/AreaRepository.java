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
        return em.createQuery("select a from Area a left join a.movie m on m.id = :movieId", Area.class)
                .setParameter("movieId", movieId)
                .getResultList();
    }

//    "select a from Area a join Movie m on m.id = :movieId"

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
