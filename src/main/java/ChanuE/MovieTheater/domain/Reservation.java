package ChanuE.MovieTheater.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY)
    private Movie movie;


    // == 연관관계 메서드 == //
    // member (1) <--> reservation (다)
    public void setMember(Member member){
        this.member = member;
        member.getReservations().add(this);
    }

}
