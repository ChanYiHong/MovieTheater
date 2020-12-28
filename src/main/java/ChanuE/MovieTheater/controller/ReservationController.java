package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.dto.member.MemberResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.service.MemberService;
import ChanuE.MovieTheater.service.MovieService;
import ChanuE.MovieTheater.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final MovieService movieService;

    @GetMapping("/reservation")
    public String reservationList(@ModelAttribute("reservation") ReservationSearch reservationSearch, Model model){

        List<Reservation> reservations = reservationService.findAll(reservationSearch);
        model.addAttribute("reservations", reservations);

        return "/reservation/reservation_list";

    }

    @GetMapping("/reservation/create")
    public String reservationForm(Model model){

        List<MemberResponseDto> findMembers = memberService.findAll();
        List<MovieResponseDto> findMovies = movieService.findAll();
        model.addAttribute("members", findMembers);
        model.addAttribute("movies", findMovies);

        return "/reservation/reservation_form";

    }

    @PostMapping("/reservation/create")
    public String createReservation(@ModelAttribute("member") Long memberId, @ModelAttribute("movie") Long movieId){

        reservationService.reservation(memberId, movieId);

        return "redirect:/";

    }


}
