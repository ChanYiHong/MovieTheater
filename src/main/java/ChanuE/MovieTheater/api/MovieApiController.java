package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.movie.MovieSaveRequestDto;
import ChanuE.MovieTheater.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieApiController {

    private final MovieService movieService;

    @GetMapping("/api/movies")
    public Result movieList(){
        List<MovieResponseDto> result = movieService.findAll();
        return new Result(result.size(), result);
    }

    @PostMapping("/api/movie/create")
    public saveResponseDto createMovie(@RequestBody @Valid MovieSaveRequestDto dto){
        Long result = movieService.saveMovie(dto);
        return new saveResponseDto(result);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class saveResponseDto{
        private Long id;
    }

}
