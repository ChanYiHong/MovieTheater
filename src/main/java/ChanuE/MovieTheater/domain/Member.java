package ChanuE.MovieTheater.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id")
    private String nickname;
    private String password;
    private String name;

    @Embedded
    private Address address;

}
