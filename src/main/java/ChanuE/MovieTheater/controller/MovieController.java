package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.movie.MovieSaveRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {

    @GetMapping("/movie/create")
    public String createMovie(Model model){
        model.addAttribute("movie", new MovieSaveRequestDto());
        return "/movie/create";
    }
}
