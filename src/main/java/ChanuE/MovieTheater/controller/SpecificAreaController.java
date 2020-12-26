package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaResponseDto;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaSaveRequestDto;
import ChanuE.MovieTheater.service.AreaService;
import ChanuE.MovieTheater.service.MovieService;
import ChanuE.MovieTheater.service.SpecificAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SpecificAreaController {

    private final SpecificAreaService specificAreaService;
    private final AreaService areaService;
    private final MovieService movieService;

    @GetMapping("/{movie_id}/{area_id}/specificArea")
    public String specificAreaList(@PathVariable("movie_id") Long movieId,@PathVariable("area_id") Long areaId, Model model) {

        List<SpecificAreaResponseDto> allSpecificArea = specificAreaService.findAllSpecificArea(areaId);
        AreaResponseDto area = areaService.findOne(areaId);
        MovieResponseDto movie = movieService.findOne(movieId);

        model.addAttribute("specificArea", allSpecificArea);
        model.addAttribute("area", area);
        model.addAttribute("movie", movie);

        return "/specific_area/specific_area_list";
    }

    @GetMapping("/{movie_id}/{area_id}/specificArea/create")
    public String specificAreaCreate(@PathVariable("movie_id") Long movieId, @PathVariable("area_id") Long areaId, Model model) {

        AreaResponseDto area = areaService.findOne(areaId);
        MovieResponseDto movie = movieService.findOne(movieId);

        model.addAttribute("specificArea", new SpecificAreaSaveRequestDto());
        model.addAttribute("area", area);
        model.addAttribute("movie", movie);

        return "/specific_area/specific_area_create";
    }

    @PostMapping("/{movie_id}/{area_id}/specificArea/create")
    public String specificAreaSave(@PathVariable("movie_id") Long movieId, @PathVariable("area_id") Long areaId, SpecificAreaSaveRequestDto requestDto) {

        specificAreaService.saveSpecificArea(requestDto, areaId);

        return "redirect:/{movie_id}/{area_id}/specificArea";
    }
}



