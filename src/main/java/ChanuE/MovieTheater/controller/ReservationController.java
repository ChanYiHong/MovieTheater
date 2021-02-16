package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationDTO;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final MovieService movieService;

    @GetMapping("")
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


}
