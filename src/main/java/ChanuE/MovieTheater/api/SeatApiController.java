package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.dto.seat.SeatDTO;
import ChanuE.MovieTheater.service.SeatService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
@Log4j2
public class SeatApiController {

    private final SeatService seatService;

    @GetMapping(value = "/{timeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<SeatDTO>> getList(@PathVariable("timeId") Long id) {
        log.info("Get All Seats... time id = " + id);
        List<SeatDTO> result = seatService.getSeat(id);

        return new ResponseEntity<>(new Result<>(result, result.size()), HttpStatus.OK);
    }

    @AllArgsConstructor
    @Data
    static class Result<T> {
        private List<T> data;
        private int size;
    }

}
