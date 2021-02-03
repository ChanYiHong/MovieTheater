package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.service.ReservationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations/")
@RequiredArgsConstructor
public class ReservationApiController {

    private final ReservationServiceImpl reservationServiceImpl;

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

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private List<T> data;
        private int count;
    }
}