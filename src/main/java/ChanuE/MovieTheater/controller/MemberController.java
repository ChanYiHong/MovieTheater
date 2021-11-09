package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.member.MemberInfoDTO;
import ChanuE.MovieTheater.dto.member.MemberSaveDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.security.dto.AuthMemberDTO;
import ChanuE.MovieTheater.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute MemberSaveDTO memberSaveDTO) {
        return "members/signup";
    }

    @PostMapping("/signup")
    public String signupMember(@ModelAttribute MemberSaveDTO memberSaveDTO) {
        log.info("Member Save Request : {}", memberSaveDTO);

        memberService.joinMember(memberSaveDTO);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/memberInfo")
    public String memberInfoPage(@ModelAttribute PageRequestDTO pageRequestDTO, @AuthenticationPrincipal AuthMemberDTO memberDTO, Model model) {
        MemberInfoDTO memberInfo = memberService.getMemberInfo(memberDTO, pageRequestDTO);
        model.addAttribute("memberInfo", memberInfo);
        model.addAttribute("member", memberDTO);
        return "members/info";
    }

}
