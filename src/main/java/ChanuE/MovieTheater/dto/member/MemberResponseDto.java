package ChanuE.MovieTheater.dto.member;

import ChanuE.MovieTheater.domain.Member;
import lombok.*;

@Data
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String memberName;

    @Builder
    public MemberResponseDto(Member member){
        this.id = member.getId();
        this.memberName = member.getMemberName();
    }

}
