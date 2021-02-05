package ChanuE.MovieTheater.repository.theater;

import ChanuE.MovieTheater.dto.theater.TheaterAreaApiDTO;
import ChanuE.MovieTheater.dto.theater.TheaterSpecAreaApiDTO;

import java.util.List;

public interface TheaterApiDTORepositoryCustom {

    List<TheaterAreaApiDTO> findAllArea(Long movieId);
    List<TheaterSpecAreaApiDTO> findAllSpecificAreaByArea(String area, Long movieId);

}
