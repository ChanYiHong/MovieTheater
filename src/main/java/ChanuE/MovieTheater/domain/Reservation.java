package ChanuE.MovieTheater.domain;

import ChanuE.MovieTheater.dto.reservation.ReservationDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    private String movieName;
    private String area;
    private String specificArea;
    private LocalDate date;
    private LocalTime time;

    @OneToMany(mappedBy = "reservation")
    @Builder.Default
    private List<Seat> seats = new ArrayList<>();


    // == 생성 메서드 == //
    // 예약 생성이 복잡하므로 따로 만든다. 예약 생성 관련이 바뀌면 여기만 바꾸자.
//    public static Reservation createReservation(Member member, Movie movie){
//        Reservation reservation = Reservation.builder().movie(movie).status(ReservationStatus.RESERVED).build();
//        reservation.setMember(member);
//        return reservation;
//    }

    public static Reservation createReservation(ReservationDTO reservationDTO, Member member) {
        Reservation reservation = Reservation.builder()
                .movieName(reservationDTO.getMovieName())
                .area(reservationDTO.getArea())
                .specificArea(reservationDTO.getSpecificArea())
                .date(reservationDTO.getDate())
                .time(reservationDTO.getTime())
                .status(ReservationStatus.RESERVED)
                .totalPerson(reservationDTO.getTotalPerson())
                .totalPrice(reservationDTO.getTotalPrice())
                .member(member)
                .build();

        return reservation;
    }


    // == 취소 메서드 == //
    // 예약 취소!
    public void cancel(){
        if(this.status == ReservationStatus.CANCEL){
            throw new IllegalStateException("이미 취소된 예약입니다~!!!");
        }
        this.status = ReservationStatus.CANCEL;
    }
}
