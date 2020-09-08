package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SeatRepository {

    private final EntityManager em;

    public Long save(Seat seat){
        em.persist(seat);
        return seat.getId();
    }

    public Seat findOne(Long id){
        return em.find(Seat.class, id);
    }

    public List<Seat> findAll(){
        return em.createQuery("select s from Seat s", Seat.class)
                .getResultList();
    }

    public void delete(Seat seat){
        em.remove(seat);
    }
}
