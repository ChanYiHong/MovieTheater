package ChanuE.MovieTheater.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    private int totalPerson;

    private int totalPrice;

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

    // 연관관계 메서드를 위해 어쩔 수 없이 Movie setter를 열어둔 상황.. 어떻게 개선할지?
    public void setMovie(Movie movie){
        this.movie = movie;
    }

    public void setStatus(ReservationStatus status){
        this.status = status;
    }



    // == 생성 메서드 == //
    // 예약 생성이 복잡하므로 따로 만든다. 예약 생성 관련이 바뀌면 여기만 바꾸자.
    public static Reservation createReservation(Member member, Movie movie){
        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setMovie(movie);
        /** 수정 해야 될 수도 **/
        //reservation.status = ReservationStatus.RESERVED;
        reservation.setStatus(ReservationStatus.RESERVED);
        return reservation;
    }
}
