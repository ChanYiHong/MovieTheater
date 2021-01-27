package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieSaveRequestDto;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import ChanuE.MovieTheater.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movie/")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/list")
    public String movieList(@ModelAttribute("MovieSearch") MovieSearch movieSearch,
                            PageRequestDTO pageRequestDTO, Model model){
        log.info("Get Movie List");
        PageResponseDTO<Movie, MovieResponseDto> result = movieService.list(pageRequestDTO, movieSearch);
        model.addAttribute("result", result);
        return "/movies/movie_list";
    }

    @GetMapping("/create")
    public String createMovie(){
        log.info("Get Movie Create View");
        return "/movies/movie_create";
    }

    @PostMapping("/create")
    public String saveMovie(@ModelAttribute MovieSaveRequestDto requestDto){
        log.info("이름 : " + requestDto.getName());
        movieService.saveMovie(requestDto);
        return "redirect:/movie/list";
    }
}
