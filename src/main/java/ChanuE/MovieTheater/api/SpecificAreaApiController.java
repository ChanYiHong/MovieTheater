package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.dto.specificArea.SpecificAreaResponseDto;
import ChanuE.MovieTheater.service.SpecificAreaService;
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
@RequestMapping("/api/specificAreas/")
@Log4j2
@RequiredArgsConstructor
public class SpecificAreaApiController {

    private final SpecificAreaService specificAreaService;

//    @GetMapping(value = "/{areaId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ReservationApiController.Result<SpecificAreaResponseDto>> getSpecificArea(@PathVariable("areaId") Long areaId) {
//        List<SpecificAreaResponseDto> specificAreas = specificAreaService.findAllSpecificArea(areaId);
//        return new ResponseEntity<>(new ReservationApiController.Result<>(specificAreas, specificAreas.size()), HttpStatus.OK);
//    }

}
