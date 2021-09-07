package ChanuE.MovieTheater.service.reservation;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationRequestDTO;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;

import java.util.List;

public interface ReservationService {

    Long reservation(ReservationRequestDTO reservationDTO, String memberId);

    PageResponseDTO<Object[], ReservationDTO> getList(ReservationSearch reservationSearch, PageRequestDTO pageRequestDTO);

    void cancelReservation(Long id);

    ReservationDTO getOne(Long id);

    default ReservationDTO entityToDTO(Reservation reservation, Member member) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .memberName(member.getName())
                .movieName(reservation.getMovieName())
                .area(reservation.getArea())
                .specificArea(reservation.getSpecificArea())
                .createdDate(reservation.getCreatedDate())
                .status(reservation.getStatus())
                .build();
    }
//
//    default Reservation dtoToEntity(ReservationDTO reservationDTO) {
//        Movie movie = Movie.builder().id(reservationDTO.getMovieId()).build();
//        Member member = Member.builder().id(reservationDTO.getMemberId()).build();
//        return Reservation.builder()
//                .id(reservationDTO.getId())
//                .member(member)
//                .movie(movie)
//                .area(reservationDTO.getAreaName())
//                .specificArea(reservationDTO.getSpecificAreaName())
//                .status(reservationDTO.getStatus())
//                .build();
//    }

}
