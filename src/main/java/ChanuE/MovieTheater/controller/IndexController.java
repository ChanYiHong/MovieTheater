package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.movie.MovieRatingHomeViewDTO;
import ChanuE.MovieTheater.service.movie.MovieService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final MovieService movieService;

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public String index(Model model){

        List<MovieRatingHomeViewDTO> movieForHomeView = movieService.getMovieForHomeView();

        for (MovieRatingHomeViewDTO movieRatingHomeViewDTO : movieForHomeView) {
            log.info("{}", movieRatingHomeViewDTO);
        }
        model.addAttribute("topRateMovie", movieForHomeView);

        return "index";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/login/test/user")
    public String userLogin() {
        return "/login/usertest";
    }

    @GetMapping("/admin")
    public String adminPage() { return "admin/admin"; }

    @GetMapping("/uploadEx")
    public String uploadEx(@ModelAttribute ExFileUpload exFileUpload) {
        return "uploadEx";
    }

    @PostMapping("/uploadEx")
    public String uploadExPost(@ModelAttribute ExFileUpload exFileUpload) {

        log.info("name : {}", exFileUpload.getName());

        List<MultipartFile> uploadFiles = exFileUpload.getUploadFiles();

        for (MultipartFile uploadFile : uploadFiles) {
            log.info("image Name : {}", uploadFile.getOriginalFilename());
        }

        return "redirect:/";
    }

    @Data
    static class ExFileUpload {
        private String name;
        private List<MultipartFile> uploadFiles;
    }
}
