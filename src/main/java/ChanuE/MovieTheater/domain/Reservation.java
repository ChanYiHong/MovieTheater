package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = {"member", "movie"})
public class Reservation extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    private int totalPerson;
    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    // 다 : 1 "단 방향"
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    private String area;
    private String specificArea;
    private LocalDate date;


    // == 생성 메서드 == //
    // 예약 생성이 복잡하므로 따로 만든다. 예약 생성 관련이 바뀌면 여기만 바꾸자.
//    public static Reservation createReservation(Member member, Movie movie){
//        Reservation reservation = Reservation.builder().movie(movie).status(ReservationStatus.RESERVED).build();
//        reservation.setMember(member);
//        return reservation;
//    }


    // == 취소 메서드 == //
    // 예약 취소!
    public void cancel(){
        if(this.status == ReservationStatus.CANCEL){
            throw new IllegalStateException("이미 취소된 예약입니다~!!!");
        }
        this.status = ReservationStatus.CANCEL;
    }
}
