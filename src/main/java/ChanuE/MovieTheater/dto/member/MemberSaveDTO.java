package ChanuE.MovieTheater.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSaveDTO {

    private String email;
    private String password;
    private String name;

}
