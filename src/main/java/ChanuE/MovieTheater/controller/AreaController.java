package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.dto.area.AreaSaveRequestDto;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.service.AreaService;
import ChanuE.MovieTheater.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AreaController {

    private final MovieService movieService;
    private final AreaService areaService;

    @GetMapping("/{movie_id}/area")
    public String areaList(@PathVariable("movie_id") Long id, Model model){

        List<AreaResponseDto> areas = areaService.findAllAreaByMovieId(id);
        MovieResponseDto movie = movieService.findOne(id);
        model.addAttribute("areas", areas);
        model.addAttribute("movie", movie);

        return "/areas/area_list";
    }

    @GetMapping("/{movie_id}/area/create")
    public String createArea(@PathVariable("movie_id") Long id, Model model){

        MovieResponseDto movie = movieService.findOne(id);
        model.addAttribute("movie", movie);
        model.addAttribute("area", new AreaSaveRequestDto());

        return "/areas/area_create";
    }

    @PostMapping("/{movie_id}/area/create")
    public String saveArea(@PathVariable("movie_id") Long id, AreaSaveRequestDto requestDto){

        areaService.saveArea(requestDto, id);

        return "redirect:/{movie_id}/area";

    }
}
