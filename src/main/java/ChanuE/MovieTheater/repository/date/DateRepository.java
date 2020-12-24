package ChanuE.MovieTheater.repository.date;

import ChanuE.MovieTheater.domain.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DateRepository {

    private final EntityManager em;

    public Long saveDate(Date date){
        em.persist(date);
        return date.getId();
    }

    public Date findOne(Long id){
        return em.find(Date.class, id);
    }

    public List<Date> findAllDateBySpecificAreaId(Long id){
        return em.createQuery("select d from Date d join SpecificArea s on d.specificArea = s.id" +
                " where s.id = :id", Date.class)
                .setParameter("id", id)
                .getResultList();
    }

}
