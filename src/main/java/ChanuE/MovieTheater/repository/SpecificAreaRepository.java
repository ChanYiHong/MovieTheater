package ChanuE.MovieTheater.repository;

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

    public List<SpecificArea> findSpecificAreaByAreaId(Long id){
        return em.createQuery("select s from SpecificArea left join s.area a on a.name =:id", SpecificArea.class)
                .setParameter("id", id)
                .getResultList();
    }
}
