package ChanuE.MovieTheater.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String memberName;

    @Embedded
    private Address address;

    @Enumerated
    private Authority authority;

    @OneToMany(mappedBy = "member")
    List<Reservation> reservations = new ArrayList<>();

    @Builder
    public Member(String memberName){
        this.memberName = memberName;
    }


}
