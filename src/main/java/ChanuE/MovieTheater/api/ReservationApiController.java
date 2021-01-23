package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.dto.reservation.ReservationResponseDto;
import ChanuE.MovieTheater.repository.Reservation.ReservationSearch;
import ChanuE.MovieTheater.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationApiController {

    private final ReservationService reservationService;

    @GetMapping("/api/reservations")
    public Result reservationList(@ModelAttribute("reservationSearch") ReservationSearch reservationSearch){
        List<ReservationResponseDto> result = reservationService.findAll(reservationSearch);
        return new Result(result.size(), result);
    }

    @PostMapping("/api/reservations/v2")
    public Result reservationListV2(@RequestBody ReservationSearch reservationSearch){
        List<ReservationResponseDto> result = reservationService.findAll(reservationSearch);
        return new Result(result.size(), result);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }
}