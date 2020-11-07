package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Area;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.dto.area.AreaSaveRequestDto;
import ChanuE.MovieTheater.repository.AreaRepository;
import ChanuE.MovieTheater.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AreaService {

    private final AreaRepository areaRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public void saveArea(AreaSaveRequestDto requestDto, Long id){
        Area area = requestDto.toEntity();
        Movie movie = movieRepository.findOne(id);
        area.setMovie(movie);
        areaRepository.save(area);
    }

    public List<AreaResponseDto> findAllArea(){
        List<Area> areas = areaRepository.findAll();
        return AreaResponseDto.areaToAreaResponseDto(areas);
    }

    public List<AreaResponseDto> findAllAreaByMovieId(Long id){
        List<Area> areas = areaRepository.findAllByMovieId(id);
        return AreaResponseDto.areaToAreaResponseDto(areas);
    }

}
