package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.Address;
import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/sign_up")
    public String createForm(Model model){
        model.addAttribute("modelForm", new MemberForm());
        return "/members/signup";
    }


    @PostMapping("/sign_up")
    public String create(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

        Address address = new Address(request.getParameter("signup_city"), request.getParameter("signup_email"));

        Member member = new Member();
        member.setNickname(request.getParameter("signup_id"));
        member.setPassword(request.getParameter("signup_pw"));
        member.setName(request.getParameter("signup_name"));

        member.setAddress(address);

        // 회원 이름이 중복이면 다시 입력하게!
        if(!memberService.validateMember(member.getName())) return "/members/signup_again";

        session.setAttribute("sessionedMember", member);

        memberService.join(member);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String checkUser(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String nickname = request.getParameter("login_id");
        String password = request.getParameter("login_pw");

        Member member = memberService.login(nickname, password);

        if(member != null) return "redirect:/reservation";
        else return "redirect:/reservation";
    }
}
