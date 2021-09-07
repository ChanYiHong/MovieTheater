package ChanuE.MovieTheater.service.member;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.Reservation;
import ChanuE.MovieTheater.domain.Review;
import ChanuE.MovieTheater.dto.member.MemberInfoDTO;
import ChanuE.MovieTheater.dto.member.MemberSaveDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationResponseDTO;
import ChanuE.MovieTheater.dto.review.ReviewResponseDTO;
import ChanuE.MovieTheater.security.dto.AuthMemberDTO;

import java.util.List;

public interface MemberService {

    void joinMember(MemberSaveDTO memberSaveDTO);

    MemberInfoDTO getMemberInfo(AuthMemberDTO member, PageRequestDTO pageRequestDTO);

    default MemberInfoDTO makeInfo(PageResponseDTO<Review, ReviewResponseDTO> pageResponseDTO, List<ReservationResponseDTO> reservations,
                                   AuthMemberDTO member, Integer totalReviewCnt) {

        return MemberInfoDTO.builder()
                .reservationCnt(reservations.size())
                .reviewResponseDTO(pageResponseDTO)
                .reviewCnt(totalReviewCnt)
                .reservationResponseDTO(reservations)
                .build();

    }

}
