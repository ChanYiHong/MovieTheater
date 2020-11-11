package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Date;
import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.dto.date.DateResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaResponseDto;
import ChanuE.MovieTheater.service.AreaService;
import ChanuE.MovieTheater.service.DateService;
import ChanuE.MovieTheater.service.MovieService;
import ChanuE.MovieTheater.service.SpecificAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DateController {

    private final MovieService movieService;
    private final AreaService areaService;
    private final SpecificAreaService specificAreaService;
    private final DateService dateService;

    @GetMapping("/{movie_id}/{area_id}/{specific_id}/date_list")
    public String dateList(@PathVariable("movie_id") Long movieId, @PathVariable("area_id") Long areaId,
                           @PathVariable("specific_id") Long specificId, Model model){

        MovieResponseDto movie = movieService.findOneMovieById(movieId);
        AreaResponseDto area = areaService.findOne(areaId);
        SpecificAreaResponseDto specificArea = specificAreaService.findOne(specificId);
        List<DateResponseDto> dates = dateService.findAllDatesBySpecificAreaId(specificId);

        model.addAttribute("movie", movie);
        model.addAttribute("area", area);
        model.addAttribute("specificArea", specificArea);
        model.addAttribute("dates", dates);

        return "/dates/date_list";
    }

}
