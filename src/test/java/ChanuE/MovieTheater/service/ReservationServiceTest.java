package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.domain.ReservationStatus;
import ChanuE.MovieTheater.repository.Reservation.ReservationRepository;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import ChanuE.MovieTheater.repository.movie.MovieSpringDataJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MovieSpringDataJpaRepository movieRepository;

    @Test
    @Rollback(false)
    public void saveReservation() throws Exception {
        //given
        Member member = new Member("member1");
        Movie movie = new Movie("movie1");

        memberRepository.save(member);
        movieRepository.save(movie);

        reservationService.reservation(member.getId(), movie.getId());

        ReservationSearch reservationSearch = new ReservationSearch();
        reservationSearch.setMemberName(member.getMemberName());
        reservationSearch.setMovieName(movie.getMovieName());
        //reservationSearch.setReservationStatus(ReservationStatus.RESERVED);

        //when

        List<Reservation> result = reservationService.findAll(reservationSearch);

        //then

        System.out.println(result.size());
        System.out.println("=============== 출력되나? =============");
        for (Reservation reservation : result) {
            System.out.println(reservation.getId());
            System.out.println(reservation.getMember().getMemberName());
            System.out.println(reservation.getMovie().getMovieName());
            System.out.println(reservation.getStatus());
        }
        System.out.println("======================================");

    }

}