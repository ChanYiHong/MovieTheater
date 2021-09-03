package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.MovieImage;
import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.dto.movie.MovieRequestDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import ChanuE.MovieTheater.service.movie.MovieService;
import ChanuE.MovieTheater.service.movie.MovieServiceImpl;
import ChanuE.MovieTheater.upload.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/movies/")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final FileStore fileStore;

    @GetMapping("/list")
    public String movieList(@ModelAttribute("MovieSearch") MovieSearch movieSearch,
                            PageRequestDTO pageRequestDTO, Model model){
        log.info("Get Movie List");
        PageResponseDTO<Object[], MovieResponseDTO> result = movieService.list(pageRequestDTO, movieSearch);
        model.addAttribute("result", result);
        return "/movies/movie_list";
    }

    @GetMapping("/create")
    public String createMovie(@ModelAttribute MovieRequestDTO movieRequestDTO){
        log.info("Get Movie Create View");
        return "/movies/movie_create";
    }

    @PostMapping("/create")
    public String saveMovie(@ModelAttribute MovieRequestDTO movieRequestDTO) throws IOException {
        log.info("이름 : " + movieRequestDTO.getMovieName());

        List<MultipartFile> imageFiles = movieRequestDTO.getImageFiles();

        for (MultipartFile imageFile : imageFiles) {
            log.info("이미지 이름 : {}", imageFile.getOriginalFilename());
            if (!imageFile.getContentType().startsWith("image")) {
                log.warn("This file is not image type");
                return "/movies/movie_create";
            }
        }

        List<MovieImage> movieImages = fileStore.storeFiles(imageFiles);

        for (MovieImage movieImage : movieImages) {
            log.info("movieImage : {}", movieImage);
        }

        movieService.saveMovie(movieRequestDTO, movieImages);
        return "redirect:/admin";
    }

    // 사용자 영화 목록 화면 조회용.
    @GetMapping("/info")
    public String movieInfo(@ModelAttribute("MovieSearch") MovieSearch movieSearch,
                            PageRequestDTO pageRequestDTO, Model model) {
        log.info("Movie Information");
        PageResponseDTO<Object[], MovieResponseDTO> result = movieService.list(pageRequestDTO, movieSearch);
        model.addAttribute("result", result);
        return "/movies/movie_info";
    }

    // 사용자 영화 상세 정보창.
    @GetMapping("/{id}")
    public String readMovie(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                            @PathVariable("id") Long id, Model model) {
        log.info("Movie Specific");
        MovieResponseDTO findMovie = movieService.findOne(id);

        log.info(findMovie);
        model.addAttribute("result", findMovie);
        return "/movies/movie_read";
    }
}
