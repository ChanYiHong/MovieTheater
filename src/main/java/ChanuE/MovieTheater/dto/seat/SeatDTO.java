package ChanuE.MovieTheater.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatDTO {

    private Long id;
    private boolean isAvailable;
    private int row;
    private int col;

}
