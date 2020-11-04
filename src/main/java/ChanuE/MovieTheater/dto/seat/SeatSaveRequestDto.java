package ChanuE.MovieTheater.dto.seat;

import ChanuE.MovieTheater.domain.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SeatSaveRequestDto {

    private String location;
    private int isAvailable;

    @Builder
    public SeatSaveRequestDto(String location, int isAvailable){
        this.location = location;
        this.isAvailable = isAvailable;
    }

    public Seat toEntity(){
        return Seat.builder().location(this.location).isAvailable(this.isAvailable).build();
    }
}
