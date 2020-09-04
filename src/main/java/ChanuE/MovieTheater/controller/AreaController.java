package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Area;
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

@Controller
@RequiredArgsConstructor
public class AreaController {

    private final MovieService movieService;
    private final AreaService areaService;

    @GetMapping("/{movie_name}/area/create")
    public String createArea(@PathVariable("movie_name") String name, Model model){

        MovieResponseDto responseDto = movieService.findOneMovie(name);

        model.addAttribute("movie", responseDto);
        model.addAttribute("area", new AreaSaveRequestDto());

        return "/areas/area_create";
    }

    @PostMapping("/{movie_name}/area/create")
    public String saveArea(@PathVariable("movie_name") String name, AreaSaveRequestDto requestDto){

        areaService.saveArea(requestDto, name);

        return "redirect:/";

    }
}
