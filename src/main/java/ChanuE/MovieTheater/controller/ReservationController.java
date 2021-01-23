package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.dto.member.MemberResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationResponseDto;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaResponseDto;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.repository.area.AreaSearch;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import ChanuE.MovieTheater.repository.specificArea.SpecificAreaSearch;
import ChanuE.MovieTheater.service.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final MovieService movieService;
    private final AreaService areaService;
    private final SpecificAreaService specificAreaService;

    @GetMapping("/reservations")
    public String reservationList(@ModelAttribute("reservationSearch") ReservationSearch reservationSearch, Model model){

        List<ReservationResponseDto> reservations = reservationService.findAll(reservationSearch);
        model.addAttribute("reservations", reservations);

        return "/reservation/reservation_list";

    }

    @GetMapping("/reservation/create")
    public String reservationForm(Model model)
    {
        List<MemberResponseDto> findMembers = memberService.findAll();
        PageResponseDTO<Movie, MovieResponseDto> findMovies = movieService.findAll();
        model.addAttribute("members", findMembers);
        model.addAttribute("movies", findMovies);

        return "/reservation/reservation_form";
    }

    @GetMapping("/reservation/createV2")
    public String reservationFormV2(
            @ModelAttribute("areaSearch") AreaSearch areaSearch,
            @ModelAttribute("specificAreaSearch") SpecificAreaSearch specificAreaSearch,
            Model model
    ) {
        List<MemberResponseDto> findMembers = memberService.findAll();
        PageResponseDTO<Movie, MovieResponseDto> findMovies = movieService.findAll();
        List<AreaResponseDto> findAreas = areaService.findAllByAreaSearch(areaSearch);
        List<SpecificAreaResponseDto> findSpecificAreas = specificAreaService.findAllBySpecificAreaSearch(specificAreaSearch);
        model.addAttribute("members", findMembers);
        model.addAttribute("movies", findMovies);
        model.addAttribute("areas", findAreas);
        model.addAttribute("specificAreas",findSpecificAreas);

        return "/reservation/reservation_form_v2";
    }

    @PostMapping("/reservation/create")
    public String createReservation(@RequestParam("memberId") Long memberId, @RequestParam("movieId") Long movieId){

        reservationService.reservation(memberId, movieId);

        return "redirect:/";

    }

    @PostMapping("/reservation/{reservation_id}/cancel")
    public String cancelReservation(@PathVariable("reservation_id") Long id){
        reservationService.cancelReservation(id);
        return "redirect:/reservations";
    }

}
