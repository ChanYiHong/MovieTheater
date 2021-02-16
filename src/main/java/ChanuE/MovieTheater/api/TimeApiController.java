package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.dto.time.TimeApiDTO;
import ChanuE.MovieTheater.dto.time.TimeResponseDTO;
import ChanuE.MovieTheater.service.TimeService;
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
@RequestMapping("/times")
@RequiredArgsConstructor
@Log4j2
public class TimeApiController {

    private final TimeService timeService;

    @GetMapping(value = "/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<TimeResponseDTO>> getTimeList(@PathVariable("movieId") Long movieId,
                                                               @ModelAttribute("timeApiDTO") TimeApiDTO timeApiDTO){

        log.info("timeApiDTO = " + timeApiDTO);
        List<TimeResponseDTO> result = timeService.listForAPI(movieId, timeApiDTO);

        return new ResponseEntity<>(new Result(result, result.size()), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private List<T> data;
        private int size;
    }


}
