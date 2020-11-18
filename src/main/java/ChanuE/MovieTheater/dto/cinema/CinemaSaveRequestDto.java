package ChanuE.MovieTheater.dto.cinema;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.domain.Display;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CinemaSaveRequestDto {

    private int cinemaNumber;
    private Display display;

    @Builder
    public CinemaSaveRequestDto(int cinemaNumber, Display display){
        this.cinemaNumber = cinemaNumber;
        this.display = display;
    }

    public Cinema toEntity(){
        return Cinema.builder().cinemaNumber(this.cinemaNumber).display(this.display).build();
    }

}
