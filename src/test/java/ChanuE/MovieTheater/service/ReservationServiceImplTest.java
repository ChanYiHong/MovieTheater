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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

@SpringBootTest
@Transactional
class ReservationServiceImplTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void reservation() throws Exception {

        List<Long> seats = new ArrayList<>();
        seats.add(1L);
        seats.add(2L);

        ReservationDTO dto = ReservationDTO.builder()
                .memberId("user1@hcy.com")
                .movieName("소울")
                .area("서울")
                .specificArea("강남")
                .date(LocalDate.now())
                .time(LocalTime.now())
                .totalPerson(2)
                .totalPrice(20000)
                .seatId(seats)
                .build();

//        Long reservationId = reservationService.reservation(dto);
//
//        System.out.println(reservationId);

    }

    @Test
    public void reservationList() throws Exception {
        ReservationSearch rs = ReservationSearch.builder().movieName("소울").build();
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResponseDTO<Object[], ReservationDTO> list = reservationService.getList(rs, pageRequestDTO);

        List<ReservationDTO> result = list.getDtoList();

        for (ReservationDTO dto : result) {
            System.out.println(dto);
        }
    }
}