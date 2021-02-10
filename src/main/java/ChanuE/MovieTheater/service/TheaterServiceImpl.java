package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Theater;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.theater.TheaterAreaApiDTO;
import ChanuE.MovieTheater.dto.theater.TheaterDTO;
import ChanuE.MovieTheater.dto.theater.TheaterSpecAreaApiDTO;
import ChanuE.MovieTheater.repository.theater.TheaterApiDTORepository;
import ChanuE.MovieTheater.repository.theater.TheaterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class TheaterServiceImpl implements TheaterService{

    private final TheaterRepository theaterRepository;
    private final TheaterApiDTORepository theaterApiDTORepository;

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

    // 예약 화면에 지역 이름 뿌리기 위한 API Controller 에 데이터 공급.
    // 모든 지역 다 보이게 가공..
    @Override
    public List<TheaterAreaApiDTO> getAllAreas(Long movieId) {
        List<TheaterAreaApiDTO> result = theaterApiDTORepository.findAllArea(movieId);

        for (TheaterAreaApiDTO theaterAreaApiDTO : result) {
            System.out.println(theaterAreaApiDTO);
        }

        List<TheaterAreaApiDTO> ret = new ArrayList<>();
        int resultIndex = 0;
        String[] areaArr = new String[]{"서울", "경기", "인천", "강원", "대전/충청", "대구", "부산/울산", "경상", "광주/전라/제주"};
        for(int i = 0; i < areaArr.length; i++){
            if(result.size() > resultIndex){
                if(result.get(resultIndex).getArea().equals(areaArr[i])) {
                    TheaterAreaApiDTO dto = new TheaterAreaApiDTO(areaArr[i], result.get(resultIndex).getSpecificCnt());
                    ret.add(dto);
                    resultIndex++;
                } else {
                    TheaterAreaApiDTO dto = new TheaterAreaApiDTO(areaArr[i], 0L);
                    ret.add(dto);
                }
            } else {
                TheaterAreaApiDTO dto = new TheaterAreaApiDTO(areaArr[i], 0L);
                ret.add(dto);
            }
        }
        return ret;
    }

    // 예약 화면에 구체적 지역 이름 뿌리기 위한 API Controller 에 데이터 공급.
    @Override
    public List<TheaterSpecAreaApiDTO> getAllSpecAreas(String area, Long movieId) {
        return theaterApiDTORepository.findAllSpecificAreaByArea(area, movieId);
    }
}
