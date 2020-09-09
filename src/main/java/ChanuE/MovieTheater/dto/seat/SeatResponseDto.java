package ChanuE.MovieTheater.dto.seat;

import ChanuE.MovieTheater.domain.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class SeatResponseDto {

    private String location;
    private int available;

    public SeatResponseDto(Seat seat){
        this.location = seat.getLocation();
        this.available = seat.getIsAvailable();
    }

    public static List<SeatResponseDto> seatToSeatResponseDto(List<Seat> seats){
        List<SeatResponseDto> dtos = new ArrayList<>();

        for(Seat s : seats){
            dtos.add(new SeatResponseDto(s));
        }
        return dtos;
    }
}
