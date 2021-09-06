package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.*;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationRequestDTO;
import ChanuE.MovieTheater.repository.Reservation.ReservationRepository;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import ChanuE.MovieTheater.repository.seat.SeatRepository;
import ChanuE.MovieTheater.repository.time.TimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional(readOnly = true)
@Log4j2
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final MovieRepository movieRepository;
    private final MemberRepository memberRepository;
    private final SeatRepository seatRepository;
    private final TimeRepository timeRepository;

    /** 예약 **/
    @Transactional
    public Long reservation(ReservationRequestDTO reservationDTO, String memberId){

        // Entity inquire (조회)
        Member member = memberRepository.findByEmail(false, memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 member 없음 id = " + memberId));

        Movie movie = movieRepository.findById(reservationDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("해당 movie 없음 id = " + reservationDTO.getMovieId()));

        Time time = timeRepository.findById(reservationDTO.getTimeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 Time 없음 id = " + reservationDTO.getTimeId()));

        // 예약 만들기.
        Reservation reservation = Reservation.createReservation(reservationDTO, movie.getMovieName(), time, member);

        // 좌석을 찾아서 unavailable 로 바꾸고, 예약 설정.
        List<Long> seats = reservationDTO.getSeatList();

        for (Long seatId : seats) {
            Optional<Seat> seatOptional = seatRepository.findById(seatId);
            if(!seatOptional.isPresent()) {
                throw new IllegalStateException("해당 좌석 없음 id = " + seatId);
            }
            Seat seat = seatOptional.get();
            seat.setAvailable(false);
            seat.setReservation(reservation);
        }

        reservationRepository.save(reservation);

        return reservation.getId();
    }

    /** 검색 **/
    public PageResponseDTO<Object[], ReservationDTO> getList(ReservationSearch reservationSearch, PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());
        Page<Object[]> result = reservationRepository.findAllBySearchCond(reservationSearch, pageable);
        Function<Object[], ReservationDTO> fn = objects -> entityToDTO((Reservation)objects[0], (Member)objects[1]);
        return new PageResponseDTO<>(result, fn);
    }

    @Override
    public ReservationDTO getOne(Long id) {

        Object[] result = reservationRepository.getReservation(id);
        return entityToDTO((Reservation) result[0], (Member) result[1]);

    }

    /** 취소 **/
    @Transactional
    public void cancelReservation(Long id){
        Reservation findReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 없습니다. id = " + id));

        // 예약을 취소하면 해당 좌석도 다시 사용 가능하게 만들어 주어야 한다.
        List<Seat> seats = seatRepository.findByReservationId(id);

        for (Seat seat : seats) {
            seat.setAvailable(true);
        }

        findReservation.cancel();
    }

}
