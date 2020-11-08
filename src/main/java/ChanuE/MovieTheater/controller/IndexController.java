package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.repository.MovieRepository;
import ChanuE.MovieTheater.service.AreaService;
import ChanuE.MovieTheater.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MovieService movieService;
    private final AreaService areaService;

    @GetMapping("/")
    public String index(Model model){

        List<MovieResponseDto> allMovies = movieService.findAllMovie();

        if(allMovies != null) {
            model.addAttribute("movies", allMovies);
        }

        return "index";
    }
}
