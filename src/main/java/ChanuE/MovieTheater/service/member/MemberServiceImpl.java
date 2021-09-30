package ChanuE.MovieTheater.service.member;

import ChanuE.MovieTheater.domain.*;
import ChanuE.MovieTheater.dto.member.MemberInfoDTO;
import ChanuE.MovieTheater.dto.member.MemberSaveDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.reservation.ReservationResponseDTO;
import ChanuE.MovieTheater.dto.review.ReviewDTO;
import ChanuE.MovieTheater.dto.review.ReviewResponseDTO;
import ChanuE.MovieTheater.repository.Reservation.ReservationRepository;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import ChanuE.MovieTheater.repository.review.ReviewRepository;
import ChanuE.MovieTheater.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public void joinMember(MemberSaveDTO memberSaveDTO) {

        Set<MemberRole> roleSet = new HashSet<>();
        roleSet.add(MemberRole.USER);

        Member member = Member.builder()
                .email(memberSaveDTO.getEmail())
                .password(passwordEncoder.encode(memberSaveDTO.getPassword()))
                .name(memberSaveDTO.getName())
                .fromSocial(false)
                .roleSet(roleSet)
                .build();

        memberRepository.save(member);

    }

    @Override
    @Transactional(readOnly = true)
    public MemberInfoDTO getMemberInfo(AuthMemberDTO member, PageRequestDTO pageRequestDTO) {

        // 회원 정보 창에서 내가 단 댓글 목록..
        Page<Review> reviews = reviewRepository
                .findByMember(member.getName(), pageRequestDTO.getPageable(Sort.by("id").descending()));

        // 나의 예약 내역 확인..
        List<Reservation> reservations = reservationRepository.findByMemberName(member.getName(), ReservationStatus.RESERVED);

        Function<Review, ReviewResponseDTO> fn = review -> {
            return ReviewResponseDTO.builder()
                    .content(review.getContent())
                    .grade(review.getGrade())
                    .title(review.getMovie().getTitle())
                    .build();
        };

        PageResponseDTO<Review, ReviewResponseDTO> reviewResponseDTO = new PageResponseDTO<>(reviews, fn);

        List<ReservationResponseDTO> reservationDTos = reservations.stream().map(reservation ->
                ReservationResponseDTO.builder().reservationId(reservation.getId()).title(reservation.getTitle())
                        .area(reservation.getArea()).specific(reservation.getSpecificArea()).date(reservation.getDate())
                        .time(reservation.getTime()).totalPerson(reservation.getTotalPerson()).totalPrice(reservation.getTotalPrice()).build())
                .collect(Collectors.toList());

        return makeInfo(reviewResponseDTO, reservationDTos, member, (int) reviews.getTotalElements());
    }
}
