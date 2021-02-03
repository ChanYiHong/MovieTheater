package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Theater;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.theater.TheaterDTO;
import ChanuE.MovieTheater.service.TheaterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/theaters")
@RequiredArgsConstructor
@Log4j2
public class TheaterController {

    private final TheaterService theaterService;

    @GetMapping("/all")
    public String getList(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<Theater, TheaterDTO> result = theaterService.list(pageRequestDTO);
        model.addAttribute("result", result);
        return "/theaters/theater_list";
    }
}
