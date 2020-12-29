package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
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

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
