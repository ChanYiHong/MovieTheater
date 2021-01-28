package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.reservation.ReservationDTO;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationServiceImpl reservationServiceImpl;
    private final MemberService memberService;
    private final MovieService movieService;
    private final AreaService areaService;
    private final SpecificAreaService specificAreaService;

    @GetMapping("/reservations")
    public String reservationList(@ModelAttribute("reservationSearch") ReservationSearch reservationSearch, Model model){

       // List<ReservationDTO> reservations = reservationServiceImpl.getList(reservationSearch);
        //model.addAttribute("reservations", reservations);

        return "/reservation/reservation_list";

    }

    @GetMapping("/reservation/create")
    public String reservationForm(Model model)
    {
        List<MovieResponseDto> movies = movieService.findAll();
        model.addAttribute("movies", movies);

        return "/reservation/reservation_form";
    }

    @PostMapping("/reservation/create")
    public String createReservation(@RequestParam("memberId") Long memberId, @RequestParam("movieId") Long movieId){

       // reservationServiceImpl.reservation(memberId, movieId);

        return "redirect:/";

    }

    @PostMapping("/reservation/{reservation_id}/cancel")
    public String cancelReservation(@PathVariable("reservation_id") Long id){
        reservationServiceImpl.cancelReservation(id);
        return "redirect:/reservations";
    }

}
