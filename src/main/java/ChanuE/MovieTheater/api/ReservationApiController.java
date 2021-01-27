package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.dto.reservation.ReservationResponseDto;
import ChanuE.MovieTheater.dto.specificArea.SpecificAreaResponseDto;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.service.AreaService;
import ChanuE.MovieTheater.service.ReservationService;
import ChanuE.MovieTheater.service.SpecificAreaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations/")
@RequiredArgsConstructor
public class ReservationApiController {

    private final ReservationService reservationService;
    private final AreaService areaService;
    private final SpecificAreaService specificAreaService;

//    @GetMapping("")
//    public Result reservationList(@ModelAttribute("reservationSearch") ReservationSearch reservationSearch){
//        List<ReservationResponseDto> result = reservationService.findAll(reservationSearch);
//        return new Result(result.size(), result);
//    }
//
//    @PostMapping("/v2")
//    public Result reservationListV2(@RequestBody ReservationSearch reservationSearch){
//        List<ReservationResponseDto> result = reservationService.findAll(reservationSearch);
//        return new Result(result.size(), result);
//    }

    @GetMapping(value = "/area/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<AreaResponseDto>> getArea(@PathVariable("movieId") Long movieId) {
        List<AreaResponseDto> areas = areaService.findAllAreaByMovieId(movieId);
        return new ResponseEntity<>(new Result<>(areas, areas.size()), HttpStatus.OK);
    }

    @GetMapping(value = "/specificArea/{areaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<SpecificAreaResponseDto>> getSpecificArea(@PathVariable("areaId") Long areaId) {
        List<SpecificAreaResponseDto> specificAreas = specificAreaService.findAllSpecificArea(areaId);
        return new ResponseEntity<>(new Result<>(specificAreas, specificAreas.size()), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private List<T> data;
        private int count;
    }
}