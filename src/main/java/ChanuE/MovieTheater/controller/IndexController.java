package ChanuE.MovieTheater.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
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
}
