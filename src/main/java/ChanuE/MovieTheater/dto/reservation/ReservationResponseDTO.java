package ChanuE.MovieTheater.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponseDTO {

    private Long reservationId;
    private String movieName;
    private String area;
    private String specific;
    private LocalDate date;
    private LocalTime time;
    private Integer totalPrice;
    private Integer totalPerson;

}
