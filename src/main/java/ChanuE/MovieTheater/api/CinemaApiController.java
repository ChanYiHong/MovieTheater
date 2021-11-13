package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.dto.cinema.CinemaDateApiDTO;
import ChanuE.MovieTheater.service.cinema.CinemaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
@RequiredArgsConstructor
@Log4j2
public class CinemaApiController {

    private final CinemaService cinemaService;

    @GetMapping(value = "/date/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<CinemaDateApiDTO>> getDate(@PathVariable("movieId") Long movieId,
                                                            @RequestParam("area") String area,
                                                            @RequestParam("specific") String specificArea) {
        List<CinemaDateApiDTO> result = cinemaService.getDateForAPI(movieId, area, specificArea);
        return new ResponseEntity<>(new Result<>(result, result.size()), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private List<T> data;
        private int size;
    }

}
