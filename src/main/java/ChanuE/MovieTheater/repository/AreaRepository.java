package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.Area;
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

    public List<Area> findAllByMovieId(Long movieId){
        return em.createQuery("select a from Area a where a.movie_id = :movieId", Area.class)
                .setParameter("movieId", movieId)
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
