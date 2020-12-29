package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.dto.reservation.ReservationResponseDto;
import ChanuE.MovieTheater.repository.Reservation.ReservationRepository;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import ChanuE.MovieTheater.repository.movie.MovieSpringDataJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final MovieSpringDataJpaRepository movieRepository;

    /** 예약 **/
    @Transactional
    public void reservation(Long memberId, Long movieId){

        // Entity inquire (조회)
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 member 없음 id = " + memberId));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("해당 movie 없음 id = " + movieId));

        Reservation reservation = Reservation.createReservation(member, movie);

        memberRepository.save(member);
        movieRepository.save(movie);
        reservationRepository.save(reservation);
    }

    /** 검색 **/
    public List<ReservationResponseDto> findAll(ReservationSearch reservationSearch){
        List<Reservation> result = reservationRepository.findAllBySearchCond(reservationSearch);
        return result.stream()
                .map(ReservationResponseDto::new)
                .collect(Collectors.toList());
    }

}
