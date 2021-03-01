package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.*;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationDTO;
import ChanuE.MovieTheater.repository.Reservation.ReservationRepository;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReservationServiceImplTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    EntityManager em;

    @Test
    public void reservation() throws Exception {

        ReservationDTO reservation = ReservationDTO.builder()
                .movieId(1L)
                .memberId(1L)
                .movieName("반지의 제왕")
                .memberName("찬의")
                .areaName("area1")
                .specificAreaName("specarea1")
                .movieDate(LocalDateTime.now())
                .status(ReservationStatus.RESERVED)
                .build();

        //Long id = reservationService.reservation(reservation);

//        Reservation findReservation = reservationRepository.getOne(id);
//
//        System.out.println(findReservation);
//
//        assertThat(findReservation.getMovie().getMovieName()).isEqualTo(reservation.getMovieName());
//
//        System.out.println(findReservation.getMember());

//        em.flush();
//        em.clear();

        PageRequestDTO requestDTO = new PageRequestDTO();

        ReservationSearch search = ReservationSearch.builder().movieName("반지의 제왕").build();

        //PageResponseDTO<Object[], ReservationDTO> result = reservationService.getList(search, requestDTO);

       // System.out.println(result.getDtoList().get(0));

    }

}