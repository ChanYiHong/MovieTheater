package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.Cinema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CinemaRepository {

    private final EntityManager em;

    public Long saveCinema(Cinema cinema){
        em.persist(cinema);
        return cinema.getId();
    }

    public Cinema findOne(Long id){
        return em.find(Cinema.class, id);
    }

    public List<Cinema> findAllCinemaByDateId(Long dateId){
        return em.createQuery("select c from Cinema join Date d on d.id = c.date" +
                " where c.date = :dateId", Cinema.class)
                .setParameter("dateId", dateId)
                .getResultList();
    }

}
