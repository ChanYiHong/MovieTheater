package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Area;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.dto.area.AreaSaveRequestDto;
import ChanuE.MovieTheater.repository.area.AreaSearch;
import ChanuE.MovieTheater.repository.area.AreaRepository;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AreaService {

    private final AreaRepository areaRepository;
    private final MovieRepository movieRepository;

//    @Transactional
//    public void saveArea(AreaSaveRequestDto requestDto, Long id){
//        Area area = requestDto.toEntity();
//        checkDuplicateArea(area.getName());
//        Movie movie = movieRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
//        area.setMovie(movie);
//        areaRepository.save(area);
//    }

//    private void checkDuplicateArea(String name){
//        List<Area> areas = areaRepository.findAreaByAreaName(name);
//
//        if(!areas.isEmpty()){
//            throw new IllegalArgumentException("Duplicate area name!! Please try another name :)");
//        }
//    }

//    public List<AreaResponseDto> findAllAreaByMovieId(Long id){
//        List<Area> areas = areaRepository.findAllAreaByMovieId(id);
//        return areas.stream()
//                .map(AreaResponseDto::new)
//                .collect(Collectors.toList());
//    }

    public AreaResponseDto findOne(Long id){
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        return new AreaResponseDto(area);
    }

//    public List<AreaResponseDto> findAllByAreaSearch(AreaSearch areaSearch){
//        List<Area> result = areaRepository.findAllAreaBySearchCond(areaSearch);
//        return result.stream()
//                .map(AreaResponseDto::new)
//                .collect(Collectors.toList());
//    }

}
