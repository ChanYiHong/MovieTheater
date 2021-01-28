package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationDTO;
import ChanuE.MovieTheater.repository.Reservation.ReservationRepository;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@Transactional(readOnly = true)
@Log4j2
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    /** 예약 **/
    @Transactional
    public Long reservation(ReservationDTO reservationDTO){

//        // Entity inquire (조회)
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 member 없음 id = " + memberId));
//
//        Movie movie = movieRepository.findById(movieId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 movie 없음 id = " + movieId));
//
//        Reservation reservation = Reservation.createReservation(member, movie);
//
//        memberRepository.save(member);
//        movieRepository.save(movie);
//        reservationRepository.save(reservation);

        Reservation reservation = dtoToEntity(reservationDTO);

        log.info("Reservation Register");
        log.info(reservation);

        reservationRepository.save(reservation);

        return reservation.getId();
    }

    /** 검색 **/
    public PageResponseDTO<Object[], ReservationDTO> getList(ReservationSearch reservationSearch, PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());
        Page<Object[]> result = reservationRepository.findAllBySearchCond(reservationSearch, pageable);
        Function<Object[], ReservationDTO> fn = objects -> entityToDTO((Reservation)objects[0], (Movie)objects[1], (Member)objects[2]);
        return new PageResponseDTO<>(result, fn);
    }

    /** 취소 **/
    @Transactional
    public void cancelReservation(Long id){
        Reservation findReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다. id = " + id));
        findReservation.cancel();
    }

}
