package ChanuE.MovieTheater.dto.cinema;

import ChanuE.MovieTheater.domain.Cinema;
import ChanuE.MovieTheater.domain.Display;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class CinemaResponseDto {

    private Long id;
    private int cinemaNumber;
    private Display display;

    public CinemaResponseDto(Cinema cinema){
        this.id = cinema.getId();
        this.cinemaNumber = cinema.getCinemaNumber();
        this.display = cinema.getDisplay();
    }

    public static List<CinemaResponseDto> CinemaToCinemaResponseDtos(List<Cinema> cinemas){

        List<CinemaResponseDto> collect = cinemas.stream()
                .map(c -> new CinemaResponseDto(c))
                .collect(Collectors.toList());

        return collect;
    }
}
