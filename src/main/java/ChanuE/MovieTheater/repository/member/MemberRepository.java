package ChanuE.MovieTheater.repository.member;

import ChanuE.MovieTheater.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
