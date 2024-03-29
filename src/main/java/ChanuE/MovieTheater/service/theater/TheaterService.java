package ChanuE.MovieTheater.service.theater;

import ChanuE.MovieTheater.domain.Theater;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.dto.theater.TheaterAreaApiDTO;
import ChanuE.MovieTheater.dto.theater.TheaterDTO;
import ChanuE.MovieTheater.dto.theater.TheaterSpecAreaApiDTO;

import java.util.List;

public interface TheaterService {

    PageResponseDTO<Theater, TheaterDTO> list(PageRequestDTO pageRequestDTO);

    TheaterDTO get(Long id);

    List<TheaterAreaApiDTO> getAllAreas(Long movieId);

    List<TheaterSpecAreaApiDTO> getAllSpecAreas(String area, Long movieId);

    default Theater dtoToEntity(TheaterDTO dto) {
        return Theater.builder()
                .id(dto.getId())
                .name(dto.getName())
                .area(dto.getArea())
                .specificArea(dto.getSpecificArea())
                .build();
    }

    default TheaterDTO entityToDTO(Theater theater) {
        return TheaterDTO.builder()
                .id(theater.getId())
                .name(theater.getName())
                .area(theater.getArea())
                .specificArea(theater.getSpecificArea())
                .build();
    }

}
