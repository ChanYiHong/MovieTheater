package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Theater;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.theater.TheaterDTO;
import ChanuE.MovieTheater.repository.theater.TheaterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class TheaterServiceImpl implements TheaterService{

    private final TheaterRepository theaterRepository;

    @Override
    public PageResponseDTO<Theater, TheaterDTO> list(PageRequestDTO pageRequestDTO) {

        log.info("Get List From All Theater");

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());
        Page<Theater> result = theaterRepository.findAll(pageable);
        Function<Theater, TheaterDTO> fn = this::entityToDTO;
        return new PageResponseDTO<>(result, fn);

    }

    @Override
    public TheaterDTO get(Long id) {
        log.info("Get One Theater");

        Optional<Theater> result = theaterRepository.findById(id);
        if(!result.isPresent()){
            throw new IllegalStateException("There is no theater for this id " + id);
        }
        return entityToDTO(result.get());
    }
}
