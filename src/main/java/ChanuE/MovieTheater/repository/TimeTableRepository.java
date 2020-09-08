package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.TimeTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TimeTableRepository {

    private final EntityManager em;

    public Long save(TimeTable timeTable){
        em.persist(timeTable);
        return timeTable.getId();
    }

    public TimeTable findOne(Long id){
        return em.find(TimeTable.class, id);
    }

    public List<TimeTable> findAll(){
        return em.createQuery("select t from TimeTable t", TimeTable.class)
                .getResultList();
    }

    public void delete(TimeTable timeTable){
        em.remove(timeTable);
    }

}
