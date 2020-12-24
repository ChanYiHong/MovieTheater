package ChanuE.MovieTheater.repository.specificArea;

import ChanuE.MovieTheater.domain.SpecificArea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpecificAreaRepository {

    private final EntityManager em;

    public Long saveSpecificArea(SpecificArea specificArea){
        em.persist(specificArea);
        return specificArea.getId();
    }

    public SpecificArea findOne(Long id){
        return em.find(SpecificArea.class, id);
    }

    public List<SpecificArea> findOneByName(String name){
        return em.createQuery("select s from SpecificArea s where s.name = :name",SpecificArea.class)
                .setParameter("name", name)
                .getResultList();
    }

    //"select a from Area a left join a.movie m on m.id = :movieId"

    public List<SpecificArea> findSpecificAreaByAreaId(Long areaId){
        return em.createQuery("select s from SpecificArea s join Area a on s.area = a.id " +
                "where a.id = :areaId", SpecificArea.class)
                .setParameter("areaId", areaId)
                .getResultList();
    }

    // "select s from SpecificArea s left join s.area a on a.id = :id"

}
