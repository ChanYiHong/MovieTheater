package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.TimeTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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

}
