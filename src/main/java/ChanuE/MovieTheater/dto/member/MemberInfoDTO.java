package ChanuE.MovieTheater.dto.member;

import ChanuE.MovieTheater.domain.Review;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationResponseDTO;
import ChanuE.MovieTheater.dto.review.ReviewResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberInfoDTO {

    private Integer reviewCnt;
    private PageResponseDTO<Review, ReviewResponseDTO> reviewResponseDTO;

    private Integer reservationCnt;
    private List<ReservationResponseDTO> reservationResponseDTO;

}
