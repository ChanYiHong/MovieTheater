package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.dto.reservation.ReservationRequestDTO;
import ChanuE.MovieTheater.security.dto.AuthMemberDTO;
import ChanuE.MovieTheater.service.ReservationService;
import ChanuE.MovieTheater.service.ReservationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@Slf4j
public class ReservationApiController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> reservationRegister(@RequestBody ReservationRequestDTO dto,
                                                      @AuthenticationPrincipal AuthMemberDTO memberDTO) {

        log.info("reservation : {}", dto);
        reservationService.reservation(dto, memberDTO.getEmail());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private List<T> data;
        private int count;
    }
}