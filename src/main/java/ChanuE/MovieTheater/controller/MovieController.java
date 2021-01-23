package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieSaveRequestDto;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movie/create")
    public String createMovie(Model model){
        model.addAttribute("movie", new MovieSaveRequestDto());
        return "/movies/movie_create";
    }

    @GetMapping("/api/movie/create")
    public String createMovieForm(){
        return "/movies/movie_api_create";
    }

    @PostMapping("/movie/create")
    public String saveMovie(MovieSaveRequestDto requestDto){

        System.out.println("이름 : " + requestDto.getName());
        movieService.saveMovie(requestDto);
        return "redirect:/";
    }

    @GetMapping("/movies")
    public String movieList(Model model){
        PageResponseDTO<Movie, MovieResponseDto> result = movieService.findAll();
        model.addAttribute("result", result);
        return "/movies/movie_list";
    }
}
