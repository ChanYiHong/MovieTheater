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
        checkDuplicateArea(area.getName());
        Movie movie = movieRepository.findOne(id);
        area.setMovie(movie);
        areaRepository.save(area);
    }

    private void checkDuplicateArea(String name){
        List<Area> areas = areaRepository.findAllAreaByAreaName(name);

        if(!areas.isEmpty()){
            throw new IllegalArgumentException("Duplicate area name!! Please try another name :)");
        }
    }

    public List<AreaResponseDto> findAllArea(){
        List<Area> areas = areaRepository.findAll();
        return AreaResponseDto.areaToAreaResponseDto(areas);
    }

    public List<AreaResponseDto> findAllAreaByMovieId(Long id){
        //List<Area> areas = areaRepository.findAllAreaByMovieId(id);
        List<Area> areas = areaRepository.findAllAreaByMovieId(id);
        return AreaResponseDto.areaToAreaResponseDto(areas);
    }

    public AreaResponseDto findOne(Long id){
        Area area = areaRepository.findOne(id);
        return new AreaResponseDto(area);
    }

}
