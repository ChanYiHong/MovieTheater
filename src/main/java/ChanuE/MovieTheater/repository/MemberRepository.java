package ChanuE.MovieTheater.repository;

import ChanuE.MovieTheater.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public Member findByIdPassword(String nickname, String password){
        return em.createQuery("select m from Member m where m.nickname = :nickname and m.password = :password", Member.class)
                .setParameter("nickname", nickname)
                .setParameter("password", password)
                .getSingleResult();
    }
}
