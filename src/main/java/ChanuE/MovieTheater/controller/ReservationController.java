package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationInfoForSeatReservationDTO;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import ChanuE.MovieTheater.service.reservation.ReservationService;
import ChanuE.MovieTheater.service.seat.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    private final SeatService seatService;
    private final MovieRepository movieRepository;

    @GetMapping
    public String reservationList(@ModelAttribute("reservationSearch") ReservationSearch reservationSearch,
                                  PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO<Object[], ReservationDTO> reservations = reservationService.getList(reservationSearch, pageRequestDTO);
        model.addAttribute("result", reservations);

        return "/reservation_list";

    }

    @GetMapping("/create")
    public String reservationForm(Model model)
    {
        return "/reservations/reservation_form";
    }


    @PostMapping("/{reservation_id}/cancel")
    public String cancelReservation(@PathVariable("reservation_id") Long id){
        reservationService.cancelReservation(id);
        return "redirect:/reservations";
    }

    @PostMapping("/seats/{timeId}")
    public String reservationSeats(@PathVariable("timeId") Long timeId,
                                   @ModelAttribute ReservationInfoForSeatReservationDTO dto, Model model) {

        log.info("Reservation Seat View, reservation infos... : {}", dto);

        Optional<Movie> optionalMovie = movieRepository.findById(dto.getMovieId());
        if (!optionalMovie.isPresent()) {
            throw new IllegalArgumentException("해당 id 영화가 없네요..." + dto.getMovieId());
        }

        model.addAttribute("info", dto);
        model.addAttribute("timeId", timeId);
        model.addAttribute("title", optionalMovie.get().getTitle());

        return "/reservations/reservation_seat";
    }

//    @PostMapping("/create")
//    public String createReservation(@ModelAttribute("reservationDTO") ReservationDTO reservationDTO){
//        Long id = reservationService.reservation(reservationDTO);
//
//        return "redirect:/reservations/"+id;
//    }

    // 예약이 끝난 결과 화면 출력용. 하나만 가져옴.
//    @GetMapping("/{id}")
//    public String getReservationResult(@PathVariable("id") Long id, Model model) {
//        ReservationDTO result = reservationService.getOne(id);
//        model.addAttribute("dto", )
//    }

}
