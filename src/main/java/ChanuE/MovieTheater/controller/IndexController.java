package ChanuE.MovieTheater.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public String index(){
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
