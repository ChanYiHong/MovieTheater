package ChanuE.MovieTheater.service.review;

import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.domain.Review;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.review.ReviewDTO;
import ChanuE.MovieTheater.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Long register(ReviewDTO reviewDTO) {
        Review review = dtoToEntity(reviewDTO);
        log.info("Save review : " + review);
        reviewRepository.save(review);
        return review.getId();
    }

    @Override
    public PageResponseDTO<Review, ReviewDTO> list(PageRequestDTO pageRequestDTO, Long movieId) {
        log.info("Get List of Reviews");
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());
        Movie movie = Movie.builder().id(movieId).build();
        Page<Review> result = reviewRepository.findByMovie(movie, pageable);
        Function<Review, ReviewDTO> fn = this::entityToDTO;
        return new PageResponseDTO<>(result, fn);
    }

    @Override
    @Transactional
    public void modify(ReviewDTO reviewDTO) {
        log.info("Modify Review : " + reviewDTO.getId());
        Optional<Review> result = reviewRepository.findById(reviewDTO.getId());
        if(result.isPresent()) {
            Review review = result.get();
            review.changeContent(reviewDTO.getContent());
        }
    }

    @Override
    @Transactional
    public void remove(Long id) {
        log.info("Remove Review : " + id);
        reviewRepository.deleteById(id);
    }
}
