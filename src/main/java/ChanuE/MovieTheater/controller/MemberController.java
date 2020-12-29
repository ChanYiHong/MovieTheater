package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.member.MemberResponseDto;
import ChanuE.MovieTheater.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public String memberList(Model model){
        List<MemberResponseDto> members = memberService.findAll();
        model.addAttribute("members", members);
        return "/members/member_list";
    }

}
