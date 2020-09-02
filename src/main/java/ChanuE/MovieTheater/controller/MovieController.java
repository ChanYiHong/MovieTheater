package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.movie.MovieSaveRequestDto;
import ChanuE.MovieTheater.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movie/create")
    public String createMovie(Model model){
        model.addAttribute("movie", new MovieSaveRequestDto());
        return "/movies/movie_create";
    }

    @PostMapping("/movie/create")
    public String saveMovie(MovieSaveRequestDto requestDto){

        System.out.println("이름 : " + requestDto.getName());
        movieService.saveMovie(requestDto);
        return "redirect:/";
    }
}
