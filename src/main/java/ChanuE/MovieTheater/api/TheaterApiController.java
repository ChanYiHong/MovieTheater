package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.dto.theater.TheaterAreaApiDTO;
import ChanuE.MovieTheater.dto.theater.TheaterSpecAreaApiDTO;
import ChanuE.MovieTheater.service.theater.TheaterService;
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
@RequestMapping("/theaters")
@RequiredArgsConstructor
@Log4j2
public class TheaterApiController {

    private final TheaterService theaterService;

    @GetMapping(value = "/area/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<TheaterAreaApiDTO>> initTheaterAreaList(@PathVariable("movieId") Long movieId){
        List<TheaterAreaApiDTO> result = theaterService.getAllAreas(movieId);
        return new ResponseEntity<>(new Result<>(result, result.size()), HttpStatus.OK);
    }

    @GetMapping(value = "/specificArea", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<TheaterSpecAreaApiDTO>> initTheaterSpecList(@RequestParam("area") String area,
                                                                             @RequestParam("movieId") Long movieId){

        System.out.println(area);
        List<TheaterSpecAreaApiDTO> result = theaterService.getAllSpecAreas(area, movieId);

        return new ResponseEntity<>(new Result<>(result, result.size()), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private List<T> data;
        private int size;
    }

}
