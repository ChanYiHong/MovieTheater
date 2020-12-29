package ChanuE.MovieTheater.dto.member;

import ChanuE.MovieTheater.domain.Address;
import ChanuE.MovieTheater.domain.Authority;
import ChanuE.MovieTheater.domain.Member;
import lombok.*;

@Data
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String memberName;
    private Address address;
    private Authority authority;

    @Builder
    public MemberResponseDto(Member member){
        this.id = member.getId();
        this.memberName = member.getMemberName();
        this.address = member.getAddress();
        this.authority = member.getAuthority();
    }

}
