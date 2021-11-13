package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.cinema.CinemaDTO;
import ChanuE.MovieTheater.dto.time.TimeResponseDTO;
import ChanuE.MovieTheater.dto.time.TimeSaveDTO;
import ChanuE.MovieTheater.service.cinema.CinemaService;
import ChanuE.MovieTheater.service.time.TimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/times")
@RequiredArgsConstructor
@Log4j2
public class TimeController {

    private final TimeService timeService;
    private final CinemaService cinemaService;

    @GetMapping("/{cinema_id}/all")
    public String getTimes(@PathVariable("cinema_id") Long cinemaId,
                            @ModelAttribute("timeSaveDTO") TimeSaveDTO timeSaveDTO, Model model) {
        List<TimeResponseDTO> result = timeService.list(cinemaId);
        CinemaDTO cinemaDTO = cinemaService.get(cinemaId);
        model.addAttribute("result", result);
        model.addAttribute("cinemaDTO", cinemaDTO);
        return "/times/time_list";
    }

    @PostMapping("/{cinema_id}/new")
    public String createTime(@PathVariable("cinema_id") Long cinemaId,
                             @ModelAttribute("timeSaveDTO") TimeSaveDTO timeSaveDTO) {

        log.info("---------------------------------");
        log.info(timeSaveDTO);
        log.info("---------------------------------");

        timeService.save(timeSaveDTO);
        return "redirect:/times/{cinema_id}/all";
    }

    @PostMapping("/{timeId}/remove")
    public String removeTime(@PathVariable("timeId") Long timeId,
                             @RequestParam("cinemaId") Long cinemaId) {
        timeService.remove(timeId);
        return "redirect:/times/"+cinemaId+"/all";
    }
}
