package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.dto.cinema.CinemaDTO;
import ChanuE.MovieTheater.dto.cinema.CinemaSaveDTO;
import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.theater.TheaterDTO;
import ChanuE.MovieTheater.repository.cinema.CinemaSearch;
import ChanuE.MovieTheater.service.CinemaService;
import ChanuE.MovieTheater.service.MovieService;
import ChanuE.MovieTheater.service.TheaterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cinemas")
@RequiredArgsConstructor
@Log4j2
public class CinemaController {

    private final CinemaService cinemaService;
    private final MovieService movieService;
    private final TheaterService theaterService;

    @GetMapping("/{theaterId}")
    public String list(@PathVariable("theaterId") Long theaterId,
                       @ModelAttribute("cinemaSearch") CinemaSearch cinemaSearch,
                       @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
        TheaterDTO theater = theaterService.get(theaterId);
        PageResponseDTO<Cinema, CinemaDTO> result = cinemaService.list(theaterId, cinemaSearch, pageRequestDTO);

        model.addAttribute("theater", theater);
        model.addAttribute("result", result);

        return "/cinemas/cinema_list";
    }

    // form 에서 th:object 사용하려면 @ModelAttribute로 객체를 넘겨줘야 함!!
    @GetMapping("/{theaterId}/new")
    public String create(@PathVariable("theaterId") Long theaterId,
                         @ModelAttribute("cinemaSaveDTO") CinemaSaveDTO dto, Model model) {
        List<MovieResponseDTO> movies = movieService.findAll();
        TheaterDTO theater = theaterService.get(theaterId);
        model.addAttribute("movies", movies);
        model.addAttribute("theater", theater);

        System.out.println(movies);
        System.out.println(theater);

        return "/cinemas/cinema_create";
    }

    @PostMapping("/{theaterId}/new")
    public String createCinema(@PathVariable("theaterId") Long theaterId,
                               @ModelAttribute("cinemaSaveDTO") CinemaSaveDTO dto){

        System.out.println(dto);
        cinemaService.save(dto, theaterId);

        return "redirect:/cinemas/"+theaterId;
    }

    @PostMapping("/{cinemaId}/remove")
    public String removeCinema(@PathVariable("cinemaId") Long cinemaId,
                               @RequestParam("theaterId") Long theaterId) {
        cinemaService.remove(cinemaId);
        return "redirect:/cinemas/"+theaterId;
    }
}
