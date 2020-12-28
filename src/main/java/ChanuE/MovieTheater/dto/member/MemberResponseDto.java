package ChanuE.MovieTheater.dto.member;

import ChanuE.MovieTheater.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private String memberName;

    @Builder
    public MemberResponseDto(Member member){
        this.memberName = member.getMemberName();
    }

}
