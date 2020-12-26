package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.TimeTable.TimeTableResponseDto;
import ChanuE.MovieTheater.dto.TimeTable.TimeTableSaveRequestDto;
import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.dto.date.DateResponseDto;
import ChanuE.MovieTheater.dto.date.DateSaveRequestDto;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaResponseDto;
import ChanuE.MovieTheater.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TimeTableController {

    private final MovieService movieService;
    private final AreaService areaService;
    private final SpecificAreaService specificAreaService;
    private final DateService dateService;
    private final TimeTableService timeTableService;

    @GetMapping("/{movie_id}/{area_id}/{specific_id}/{date_id}/time_table_list")
    public String timeTableList(@PathVariable("movie_id") Long movieId, @PathVariable("area_id") Long areaId,
                                @PathVariable("specific_id") Long specificId, @PathVariable("date_id") Long dateId, Model model){

        MovieResponseDto movie = movieService.findOne(movieId);
        AreaResponseDto area = areaService.findOne(areaId);
        SpecificAreaResponseDto specificArea = specificAreaService.findOne(specificId);
        DateResponseDto date = dateService.findOne(dateId);
        List<TimeTableResponseDto> timeTables = timeTableService.findAllTimeTableByDateId(dateId);

        model.addAttribute("movie", movie);
        model.addAttribute("area", area);
        model.addAttribute("specificArea", specificArea);
        model.addAttribute("date", date);
        model.addAttribute("timeTables", timeTables);

        return "/time_tables/time_table_list";

    }

    @GetMapping("/{movie_id}/{area_id}/{specific_id}/{date_id}/time_table_create")
    public String createTimeTable(@PathVariable("movie_id") Long movieId, @PathVariable("area_id") Long areaId,
                                  @PathVariable("specific_id") Long specificId, @PathVariable("date_id") Long dateId, Model model){

        MovieResponseDto movie = movieService.findOne(movieId);
        AreaResponseDto area = areaService.findOne(areaId);
        SpecificAreaResponseDto specificArea = specificAreaService.findOne(specificId);
        DateResponseDto date = dateService.findOne(dateId);

        model.addAttribute("movie", movie);
        model.addAttribute("area", area);
        model.addAttribute("specificArea", specificArea);
        model.addAttribute("date", date);
        model.addAttribute("timeTable", new TimeTableSaveRequestDto());

        return "/time_tables/time_table_create";
    }

    @GetMapping("/{movie_id}/{area_id}/{specific_id}/{date_id}/time_table_done")
    public String saveTimeTable(@PathVariable("movie_id") Long movieId, @PathVariable("area_id") Long areaId,
                           @PathVariable("specific_id") Long specificId, @PathVariable("date_id") Long dateId,
                                @ModelAttribute("date") TimeTableSaveRequestDto requestDto){

        timeTableService.saveTimeTable(requestDto, dateId);

        return "redirect:/{movie_id}/{area_id}/{specific_id}/{date_id}/time_table_list";
    }
}
