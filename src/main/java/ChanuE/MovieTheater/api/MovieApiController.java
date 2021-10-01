package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.dto.movie.MovieApiSaveDTO;
import ChanuE.MovieTheater.dto.movie.MovieKobisApiSaveDTO;
import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.service.movie.MovieService;
import ChanuE.MovieTheater.service.movie.MovieServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Slf4j
public class MovieApiController {

    private final MovieService movieService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<MovieResponseDTO>> initMovieList() {
        List<MovieResponseDTO> result = movieService.findAll();
        return new ResponseEntity<>(new Result<>(result, result.size()), HttpStatus.OK);
    }

    // 관리자 권한으로 영화 저장. (네이버 api로 검색 후 저장버튼 눌렀을 때)
//    @PostMapping(value = "/api/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveMovie(@RequestBody MovieApiSaveDTO movieApiSaveDTO) {

        log.info("movie api save request : {}", movieApiSaveDTO);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // 관리자 권한으로 영화 저장. (Kobis API로 검색후 저장버튼 눌렀을 때)
    @PostMapping(value = "/api/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveMovieKobis(@RequestBody MovieKobisApiSaveDTO movieKobisApiSaveDTO) {

        log.info("movie kobis api save request : {}", movieKobisApiSaveDTO);

        // true 면 success, false 면 오류. (동일한 이름의 영화 이미 존재)

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private List<T> data;
        private int size;
    }
}
