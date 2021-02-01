package ChanuE.MovieTheater.api;


import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.service.AreaService;
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
@RequestMapping("/api/areas/")
@RequiredArgsConstructor
@Log4j2
public class AreaApiController {

    private final AreaService areaService;

//    @GetMapping(value = "/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ReservationApiController.Result<AreaResponseDto>> getArea(@PathVariable("movieId") Long movieId) {
//        List<AreaResponseDto> areas = areaService.findAllAreaByMovieId(movieId);
//        return new ResponseEntity<>(new ReservationApiController.Result<>(areas, areas.size()), HttpStatus.OK);
//    }

}
