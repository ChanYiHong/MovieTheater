package ChanuE.MovieTheater.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 아이디는 반드시 필요합니다!!")
    private String nickname;

    @NotEmpty(message = "회원 비밀번호는 반드시 필요합니다!!")
    private String password;

    @NotEmpty(message = "회원 이름은 반드시 필요합니다!!")
    private String name;

    private String city;
    private String email;
}
